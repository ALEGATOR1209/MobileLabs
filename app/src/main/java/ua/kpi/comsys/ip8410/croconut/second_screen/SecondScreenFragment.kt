package ua.kpi.comsys.ip8410.croconut.second_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.kpi.comsys.ip8410.croconut.MainFragment
import ua.kpi.comsys.ip8410.croconut.databinding.FragmentSecondScreenBinding

class SecondScreenFragment : MainFragment() {
    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
