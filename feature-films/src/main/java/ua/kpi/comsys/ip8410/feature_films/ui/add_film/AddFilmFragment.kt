package ua.kpi.comsys.ip8410.feature_films.ui.add_film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8410.core_ui.MainFragment
import ua.kpi.comsys.ip8410.feature_films.R
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.databinding.FragmentAddFilmBinding
import ua.kpi.comsys.ip8410.feature_films.ui.FilmViewModel

class AddFilmFragment : MainFragment() {
    private var _binding: FragmentAddFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var filmsViewModel: FilmViewModel
    private lateinit var addFilmViewModel: AddFilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        filmsViewModel = ViewModelProvider(requireActivity()).get(FilmViewModel::class.java)
        addFilmViewModel = ViewModelProvider(requireActivity()).get(AddFilmViewModel::class.java)
        _binding = FragmentAddFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.addTextChangedListener {
            addFilmViewModel.title = it.toString()
        }
        binding.year.addTextChangedListener {
            addFilmViewModel.year = it.toString()
        }
        binding.type.addTextChangedListener {
            addFilmViewModel.type = it.toString()
        }
        binding.addButton.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val title = addFilmViewModel.title
            val year = addFilmViewModel.year
            val type = addFilmViewModel.type
            onBackPressed()
            filmsViewModel.state.value = FilmViewModel.State.AddFilm(
                Film(
                    title,
                    year,
                    imdbID = "noid",
                    type = type,
                    poster = "no_poster",
                )
            )
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (binding.title.text.isNullOrBlank()) {
            binding.title.error = getString(R.string.error_empty_input)
            isValid = false
        }
        if (binding.year.text.isNullOrBlank()) {
            binding.year.error = getString(R.string.error_empty_input)
            isValid = false
        }
        if (binding.type.text.isNullOrBlank()) {
            binding.type.error = getString(R.string.error_empty_input)
            isValid = false
        }

        return isValid
    }

    override fun onBackPressed() {
        filmsViewModel.state.value = FilmViewModel.State.List
        addFilmViewModel.clear()
        super.onBackPressed()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.title.setText(addFilmViewModel.title)
        binding.year.setText(addFilmViewModel.year)
        binding.type.setText(addFilmViewModel.type)
    }
}
