package ua.kpi.comsys.ip8410.croconut.graph_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.kpi.comsys.ip8410.croconut.MainFragment
import ua.kpi.comsys.ip8410.croconut.R
import ua.kpi.comsys.ip8410.croconut.databinding.FragmentGraphScreenBinding

class GraphScreenFragment : MainFragment() {
    private var _binding: FragmentGraphScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toggleButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when {
                checkedId == R.id.buttonGraph && isChecked -> {
                    group.uncheck(R.id.buttonChart)
                    showGraph()
                }
                checkedId == R.id.buttonChart && isChecked -> {
                    group.uncheck(R.id.buttonGraph)
                    showChart()
                }
            }
        }
    }

    private fun showGraph() {
        shortToast("Graph!")
    }

    private fun showChart() {
        shortToast("Chart!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
