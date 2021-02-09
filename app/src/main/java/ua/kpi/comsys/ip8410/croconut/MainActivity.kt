package ua.kpi.comsys.ip8410.croconut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.kpi.comsys.ip8410.croconut.databinding.ActivityMainBinding
import ua.kpi.comsys.ip8410.croconut.navigation_fragment.NavigationFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(NavigationFragment())
    }

    fun changeFragment(fragment: MainFragment, addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            if (addToBackStack) addToBackStack(fragment::class.simpleName)
        }.commit()
    }
}
