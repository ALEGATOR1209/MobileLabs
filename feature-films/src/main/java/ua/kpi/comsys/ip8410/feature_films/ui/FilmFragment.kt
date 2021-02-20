package ua.kpi.comsys.ip8410.feature_films.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8410.core_ui.MainFragment
import ua.kpi.comsys.ip8410.feature_films.R
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.databinding.FragmentFilmBinding

internal class FilmFragment : MainFragment() {
    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(FilmViewModel::class.java)
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmString = arguments?.getString(BUNDLE_FILM) ?: error("Film not provided")
        val film = Json.decodeFromString<Film>(filmString)
        val noData = getString(R.string.no_data)

        with(binding) {
            title.text = film.title

            Picasso.get()
                .load(film.poster)
                .placeholder(R.drawable.pic_loader)
                .error(R.drawable.ic_film_no_photo)
                .into(poster)

            actors.text = getString(R.string.actors, film.actors ?: noData)
            if (film.rated == null) {
                rated.isVisible = false
            } else {
                rated.text = film.rated
            }

            year.text = getString(R.string.year, film.year)
            genre.text = getString(R.string.genre, film.genre ?: noData)
            released.text = getString(R.string.released, film.released ?: noData)
            imdbId.text = getString(R.string.imdbid, film.imdbID)
            imdbIdRating.text = getString(R.string.imdbrating, film.imdbRating ?: noData)
            imdbIdVotes.text = getString(R.string.imdbvotes, film.imdbVotes ?: noData)
            type.text = getString(R.string.type, film.type)
            runtime.text = getString(R.string.runtime, film.runtime ?: noData)
            director.text = getString(R.string.director, film.director ?: noData)
            writer.text = getString(R.string.writer, film.writer ?: noData)
            actors.text = getString(R.string.actors, film.actors ?: noData)
            plot.text = getString(R.string.plot, film.plot ?: noData)
            language.text = getString(R.string.language, film.language ?: noData)
            country.text = getString(R.string.country, film.country ?: noData)
            awards.text = getString(R.string.awards, film.awards ?: noData)
            production.text = getString(R.string.production, film.production ?: noData)
        }
    }

    override fun onBackPressed() {
        viewModel.state.value = FilmViewModel.State.List
        super.onBackPressed()
    }

    companion object {
        const val BUNDLE_FILM = "BUNDLE_FILM"
    }
}