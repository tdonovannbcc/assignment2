package work.nbcc.rick_and_morty_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import work.nbcc.rick_and_morty_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = dataBinding.drawerLayout

        val navCon = this.findNavController(R.id.NavHostFragment)

        NavigationUI.setupWithNavController(dataBinding.NavigationView, navCon)
        NavigationUI.setupActionBarWithNavController(this, navCon, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navCon = this.findNavController(R.id.NavHostFragment)
        return NavigationUI.navigateUp(navCon, drawerLayout)
    }
}