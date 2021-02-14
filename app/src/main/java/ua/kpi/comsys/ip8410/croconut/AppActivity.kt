package ua.kpi.comsys.ip8410.croconut

import android.os.Bundle
import ua.kpi.comsys.ip8410.core_ui.MainActivity
import ua.kpi.comsys.ip8410.croconut.navigation_fragment.NavigationFragment

class AppActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragment(NavigationFragment(), animEnter = R.anim.no_anim)
    }
}
