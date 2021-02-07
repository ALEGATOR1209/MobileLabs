package ua.kpi.comsys.ip8410.croconut

import androidx.fragment.app.Fragment

abstract class MainFragment : Fragment() {
    val mainActivity: MainActivity
        get() = activity as MainActivity
}
