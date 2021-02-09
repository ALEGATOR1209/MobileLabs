package ua.kpi.comsys.ip8410.croconut.graph_screen

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8410.croconut.MainFragment
import ua.kpi.comsys.ip8410.croconut.R
import ua.kpi.comsys.ip8410.croconut.databinding.FragmentGraphScreenBinding
import ua.kpi.comsys.ip8410.croconut.graph_screen.views.ChartView
import ua.kpi.comsys.ip8410.croconut.graph_screen.views.Point

class GraphScreenFragment : MainFragment() {
    private var _binding: FragmentGraphScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GraphScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GraphScreenViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toggleButtonGroup) {
            addOnButtonCheckedListener { _, checkedId, isChecked ->
                when {
                    checkedId == R.id.buttonGraph && isChecked -> {
                        if (!isChecked && checkedButtonIds.isEmpty()) check(R.id.buttonGraph)
                        uncheck(R.id.buttonChart)
                        viewModel.state.value = GraphScreenViewModel.State.Graph
                    }
                    checkedId == R.id.buttonChart -> {
                        if (!isChecked && checkedButtonIds.isEmpty()) check(R.id.buttonChart)
                        uncheck(R.id.buttonGraph)
                        viewModel.state.value = GraphScreenViewModel.State.Chart
                    }
                }
            }
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                GraphScreenViewModel.State.Graph -> showGraph()
                GraphScreenViewModel.State.Chart -> showChart()
                else -> error("Not supported: $it")
            }
        })

        initGraph()
        initChart()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        when (viewModel.state.value) {
            GraphScreenViewModel.State.Graph -> binding.toggleButtonGroup.check(R.id.buttonGraph)
            GraphScreenViewModel.State.Chart -> {
                binding.toggleButtonGroup.check(R.id.buttonChart)
                viewModel.updateNumbers()
            }
        }
    }

    private fun initGraph() {
        viewModel.pointNum.value?.toFloat()?.let {
            binding.slider.value = (it - 3) / 2
        }

        binding.slider.addOnChangeListener { _, value, _ ->
            viewModel.pointNum.value = (value * 2 + 3).toInt()
        }

        binding.slider.setLabelFormatter { value: Float -> (value * 2 + 3).toString() }

        viewModel.pointNum.observe(viewLifecycleOwner, {
            binding.graph.points = generatePoints(it)
        })
    }

    private fun initChart() {
        viewModel.numberLiveData.observe(viewLifecycleOwner, { (green, yellow, red) ->
            binding.sliderGreen.value = green.toFloat()
            binding.sliderYellow.value = yellow.toFloat()
            binding.sliderRed.value = red.toFloat()
            binding.chartView.dataset = listOf(
                ChartView.Data(green, Color.GREEN),
                ChartView.Data(yellow, Color.YELLOW),
                ChartView.Data(red, Color.RED),
            )
        })

        binding.sliderGreen.addOnChangeListener { _, value, _ ->
            viewModel.green = value.toInt()
        }

        binding.sliderYellow.addOnChangeListener { _, value, _ ->
            viewModel.yellow = value.toInt()
        }

        binding.sliderRed.addOnChangeListener { _, value, _ ->
            viewModel.red = value.toInt()
        }
    }

    private fun showGraph() {
        binding.chartView.visibility = View.GONE
        binding.chartControlsBox.visibility = View.GONE
        binding.graph.visibility = View.VISIBLE
        binding.slider.visibility = View.VISIBLE
        binding.graph.points = generatePoints(viewModel.pointNum.value ?: 3)
    }

    private fun generatePoints(num: Int, min: Int = -5, max: Int = 5): List<Point> {
        val step = (max - min) / (num.toFloat() - 1)
        var prev = 0f
        val points = List(num - 1) { i ->
            val x = if (i % 2 == 0) {
                prev += step
                prev
            } else {
                -prev
            }
            x to x * x
        } + (0f to 0f)

        return points.sortedBy(Point::first)
    }

    private fun showChart() {
        binding.graph.visibility = View.GONE
        binding.slider.visibility = View.GONE
        binding.chartView.visibility = View.VISIBLE
        binding.chartControlsBox.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
