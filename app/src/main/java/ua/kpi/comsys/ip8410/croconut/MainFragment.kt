package ua.kpi.comsys.ip8410.croconut

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class MainFragment : Fragment() {
    val mainActivity: MainActivity
        get() = activity as MainActivity

    protected fun shortToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
