package ua.kpi.comsys.ip8410.croconut.graph_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GraphScreenViewModel : ViewModel() {
    val pointNum = MutableLiveData(21)
    val state = MutableLiveData(State.Graph)

    private var _green = 35
    var green: Int
        get() = _green
        set(value) {
            val diff = value - _green
            _green = value
            _yellow -= diff
            if (_yellow < 0) _yellow = 0
            _red = 100 - _green - _yellow
            updateNumbers()
        }

    private var _yellow = 40
    var yellow: Int
        get() = _yellow
        set(value) {
            val diff = value - _yellow
            _yellow = value
            _green -= diff
            if (_green < 0) _green = 0
            _red = 100 - _green - _yellow
            updateNumbers()
        }

    private var _red = 25
    var red: Int
        get() = _red
        set(value) {
            val diff = value - _red
            _red = value
            _yellow -= diff
            if (_yellow < 0) _yellow = 0
            _green = 100 - _red - _yellow
            updateNumbers()
        }

    val numberLiveData = MutableLiveData(listOf(green, yellow, red))

    fun updateNumbers() {
        numberLiveData.postValue(listOf(green, yellow, red))
    }

    enum class State {
        Graph, Chart
    }
}
