package ua.kpi.comsys.ip8410.core_ui

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.appcompat.app.AppCompatActivity
import ua.kpi.comsys.ip8410.core_ui.databinding.ActivityMainBinding

abstract class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun changeFragment(
        fragment: MainFragment,
        addToBackStack: Boolean = false,
        @AnimRes animEnter: Int = R.anim.slide_from_right,
        @AnimRes animExit: Int = R.anim.slide_to_left,
        @AnimRes popEnter: Int = R.anim.slide_from_left,
        @AnimRes popExit: Int = R.anim.slide_to_right,
    ) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(animEnter, animExit, popEnter, popExit)
            replace(R.id.fragment_container, fragment)
            if (addToBackStack) addToBackStack(fragment::class.simpleName)
        }.commit()
    }

    var onBackPressedListener: (() -> Unit)? = null

    override fun onBackPressed() {
        onBackPressedListener?.invoke() ?: finish()
    }
}
