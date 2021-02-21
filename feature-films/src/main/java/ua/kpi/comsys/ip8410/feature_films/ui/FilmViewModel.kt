package ua.kpi.comsys.ip8410.feature_films.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.core.domain.repository.FilmRepository

internal class FilmViewModel : ViewModel() {
    val state = MutableLiveData<State>(State.List)
    val loading = MutableLiveData(false)
    val films = MutableLiveData<Result<List<Film>, Exception>>()
    val loadedFilm = MutableLiveData<Result<Film, Exception>>()
    var repository: FilmRepository? = null

    sealed class State {
        object List: State()
        data class ShowFilm(val film: Film): State()
        data class AddFilm(val film: Film? = null): State()
    }

    fun loadFilms(request: String?) {
        loading.value = true

        if (request.isNullOrBlank()) {
            films.postValue(Err(Exception("No films found")))
            loading.value = false
            return
        }

        if (request.length < 3) {
            films.postValue(Err(Exception("No films found")))
            loading.value = false
            return
        }

        if (request.contains(Regex("[^A-Za-z0-9 -'`]"))) {
            films.postValue(Err(Exception("Incorrect input")))
            loading.value = false
            return
        }

        viewModelScope.launch {
            films.postValue(repository?.searchFilm(request))
            loading.value = false
        }
    }

    fun loadFilm(film: Film) {
        if (loading.value == true) return

        loading.postValue(true)

        viewModelScope.launch {
            loadedFilm.postValue(repository?.getFilm(film.imdbID))
            loading.postValue(false)
        }
    }
}
