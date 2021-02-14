package ua.kpi.comsys.ip8410.core_ui

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class MainFragment : Fragment() {
    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity.onBackPressedListener = this::onBackPressed
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity.onBackPressedListener = null
    }

    protected fun shortToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    protected open fun onBackPressed() {
        val manager = mainActivity.supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            manager.popBackStack()
        } else {
            mainActivity.finish()
        }
    }
}
