package ua.kpi.comsys.ip8410.croconut.navigation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnimRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8410.croconut.MainFragment
import ua.kpi.comsys.ip8410.croconut.R
import ua.kpi.comsys.ip8410.croconut.databinding.FragmentNavigationBinding
import ua.kpi.comsys.ip8410.croconut.graph_screen.GraphScreenFragment
import ua.kpi.comsys.ip8410.croconut.graph_screen.GraphScreenViewModel
import ua.kpi.comsys.ip8410.croconut.student_info.StudentInfoFragment

class NavigationFragment : MainFragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NavigationViewModel

    private val studentInfoFragment = StudentInfoFragment()
    private val graphScreenFragment = GraphScreenFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.navigationView.selectedItemId = when (viewModel.state.value) {
            NavigationViewModel.State.Info -> R.id.student_info
            NavigationViewModel.State.Graph -> R.id.second_screen
            else -> error("Not supported")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                NavigationViewModel.State.Info -> setCurrentFragment(
                    studentInfoFragment,
                    R.anim.slide_from_left,
                    R.anim.slide_to_right
                )
                NavigationViewModel.State.Graph -> setCurrentFragment(
                    graphScreenFragment,
                    R.anim.slide_from_right,
                    R.anim.slide_to_left
                )
                else -> error("Not supported: $it")
            }
        })

        binding.navigationView.setOnNavigationItemSelectedListener {
            viewModel.state.value = when (it.itemId) {
                R.id.student_info -> NavigationViewModel.State.Info
                R.id.second_screen -> NavigationViewModel.State.Graph
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