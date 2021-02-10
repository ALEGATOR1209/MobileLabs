package ua.kpi.comsys.ip8410.croconut.navigation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ua.kpi.comsys.ip8410.croconut.MainFragment
import ua.kpi.comsys.ip8410.croconut.R
import ua.kpi.comsys.ip8410.croconut.databinding.FragmentNavigationBinding
import ua.kpi.comsys.ip8410.croconut.graph_screen.GraphScreenFragment
import ua.kpi.comsys.ip8410.croconut.student_info.StudentInfoFragment
import ua.kpi.comsys.ip8410.feature_films.ui.FilmListFragment

class NavigationFragment : MainFragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NavigationViewModel

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
            NavigationViewModel.State.Films -> R.id.films
            else -> error("Not supported")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment = when (position) {
                0 -> StudentInfoFragment()
                1 -> GraphScreenFragment()
                2 -> FilmListFragment()
                else -> error("Not supported")
            }
        }

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navigationView.selectedItemId = when (position) {
                    0 -> R.id.student_info
                    1 -> R.id.second_screen
                    2 -> R.id.films
                    else -> error("Not supported")
                }
            }
        })

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                NavigationViewModel.State.Info -> binding.pager.currentItem = 0
                NavigationViewModel.State.Graph -> binding.pager.currentItem = 1
                NavigationViewModel.State.Films -> binding.pager.currentItem = 2
                else -> error("Not supported: $it")
            }
        })

        binding.navigationView.setOnNavigationItemSelectedListener {
            viewModel.state.value = when (it.itemId) {
                R.id.student_info -> NavigationViewModel.State.Info
                R.id.second_screen -> NavigationViewModel.State.Graph
                R.id.films -> NavigationViewModel.State.Films
                else -> error("Not supported: ${it.itemId}")
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}