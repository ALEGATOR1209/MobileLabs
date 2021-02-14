package ua.kpi.comsys.ip8410.feature_films.ui.add_film

import androidx.lifecycle.ViewModel

class AddFilmViewModel : ViewModel() {
    var title: String = ""
    var year: String = ""
    var type: String = ""

    fun clear() {
        title = ""
        year = ""
        type = ""
    }
}
