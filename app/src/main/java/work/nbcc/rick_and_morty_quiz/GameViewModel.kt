package work.nbcc.rick_and_morty_quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Question (
        val questionID: Int,
        val answer: Boolean,
        var attempted: Boolean = false,
        var answered: Boolean = false
)
class GameViewModel : ViewModel() {
    private lateinit var questions: MutableList<Question>
    private var index = 0

    private val _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    private val _score = MutableLiveData<String>()
    val score: LiveData<String>
        get() = _score

    private val _attempted = MutableLiveData<Boolean>()
    val attempted: LiveData<Boolean>
        get() = _attempted

    private val _checkTrue = MutableLiveData<Boolean>()
    val checkTrue: LiveData<Boolean>
        get() = _checkTrue

    private val _checkFalse = MutableLiveData<Boolean>()
    val checkFalse: LiveData<Boolean>
        get() = _checkFalse

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect: LiveData<Boolean>
        get() = _isCorrect

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        questionBankReset()
        index = 0
        _eventGameFinish.value = false
        questionUpdate()
    }

    fun finish() {
        _eventGameFinish.value = true
    }

    fun finishComplete() {
        _eventGameFinish.value = false
    }

    fun onSelected(radioType: Boolean) {

        questions[index].attempted = true

        if(radioType) {
            questions[index].answered = true
        }

        questionUpdate()
    }

    fun next() {

        if(index == 0) {
            index = questions.size - 1
        } else {
            index--
        }

        questionUpdate()
    }

    fun previous() {

        if(index == questions.size - 1) {
            index = 0
        } else {
            index++
        }

        questionUpdate()
    }

    private fun questionBankReset() {
        questions = mutableListOf(
                Question(R.string.question_1, false),
                Question(R.string.question_2, true),
                Question(R.string.question_3, true),
                Question(R.string.question_4, false),
                Question(R.string.question_5, false),
                Question(R.string.question_6, true),
                Question(R.string.question_7, false),
                Question(R.string.question_8, true),
                Question(R.string.question_9, false),
                Question(R.string.question_10, false),
                Question(R.string.question_11, false),
                Question(R.string.question_12, true),
                Question(R.string.question_13, false),
                Question(R.string.question_14, true),
                Question(R.string.question_15, false),
                Question(R.string.question_16, false),
                Question(R.string.question_17, true),
                Question(R.string.question_18, false),
                Question(R.string.question_19, false),
                Question(R.string.question_20, true)
        )

        questions.shuffle()
    }

    private fun questionUpdate() {
        _question.value = questions[index].questionID
        _attempted.value = questions[index].attempted
        _isCorrect.value = questions[index].answer == questions[index].answered
        _checkFalse.value = questions[index].attempted and !questions[index].answered
        _checkTrue.value = questions[index].attempted and questions[index].answered
        _score.value = "Your score: ${questions.count{it.attempted and (it.answered == it.answer)}}/${questions.size}"

        if(attempted() == questions.size){
            finish()
        }
    }

    fun attempted() = questions.count {it.attempted}

}