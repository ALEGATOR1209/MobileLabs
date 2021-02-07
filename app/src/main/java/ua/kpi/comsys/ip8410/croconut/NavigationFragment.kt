package ua.kpi.comsys.ip8410.croconut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnimRes
import ua.kpi.comsys.ip8410.croconut.databinding.FragmentNavigationBinding
import ua.kpi.comsys.ip8410.croconut.second_screen.SecondScreenFragment
import ua.kpi.comsys.ip8410.croconut.student_info.StudentInfoFragment

class NavigationFragment : MainFragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    private val studentInfoFragment = StudentInfoFragment()
    private val secondScreenFragment = SecondScreenFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrentFragment(studentInfoFragment)

        binding.navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.student_info -> setCurrentFragment(
                    studentInfoFragment,
                    R.anim.slide_from_left,
                    R.anim.slide_to_right
                )
                R.id.second_screen -> setCurrentFragment(
                    secondScreenFragment,
                    R.anim.slide_from_right,
                    R.anim.slide_to_left
                )
                else -> error("Not supported: ${it.itemId}")
            }
            true
        }
    }

    private fun setCurrentFragment(
        fragment: MainFragment,
        @AnimRes animEnter: Int = R.anim.slide_from_right,
        @AnimRes animExit: Int = R.anim.slide_to_left
    ) {
        childFragmentManager.beginTransaction()
            .setCustomAnimations(animEnter, animExit)
            .replace(R.id.navigation_phase, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}