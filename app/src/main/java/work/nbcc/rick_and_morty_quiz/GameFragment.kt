package work.nbcc.rick_and_morty_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import work.nbcc.rick_and_morty_quiz.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private lateinit var dataBinding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        dataBinding.gameViewModel = viewModel

        dataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished -> if(hasFinished) finished() })

        return dataBinding.root
    }

    private fun finished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.gameFragmentToGameOverFragment()
        action.score = viewModel.score.value?:""
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.finishComplete()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}