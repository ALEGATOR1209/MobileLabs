package ua.kpi.comsys.ip8410.croconut.navigation_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val state = MutableLiveData(State.Info)

    enum class State {
        Info, Graph, Films, Gallery
    }
}
