package ua.kpi.comsys.ip8410.feature_films.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8410.core_ui.MainFragment
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.data.repository.repository
import ua.kpi.comsys.ip8410.feature_films.databinding.FragmentFilmListBinding
import ua.kpi.comsys.ip8410.feature_films.ui.recycler.FilmAdapter
import java.lang.Exception

class FilmListFragment : MainFragment() {
    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(FilmViewModel::class.java)
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.repository == null) {
            viewModel.repository = repository("7e9fe69e", requireActivity().applicationContext)
        }

        val filmAdapter = FilmAdapter().apply {
            setOnFilmClickListener {
                viewModel.loadedFilm.value = null
                viewModel.loadedFilm.observe(viewLifecycleOwner, filmObserver)
                viewModel.loadFilm(it)
            }
        }

        with(binding.recycler) {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            ))
        }

        binding.searchRequest.addTextChangedListener {
            viewModel.loadFilms(it?.toString())
        }

        binding.progress.isIndeterminate = true
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.progress.isVisible = it
        })

        viewModel.films.observe(viewLifecycleOwner, {
            it.onSuccess { films ->
                binding.noFilms.isVisible = false
                binding.recycler.isVisible = true
                if (films.isEmpty()) {
                    binding.noFilms.isVisible = true
                    binding.recycler.isVisible = false
                    binding.noFilms.text = "Nothing to show"
                } else {
                    filmAdapter.update(films)
                }
            }.onFailure { error ->
                binding.noFilms.isVisible = true
                binding.recycler.isVisible = false
                binding.noFilms.text = error.message
            }
        })

        binding.addFilm.setOnClickListener {
            viewModel.state.value = FilmViewModel.State.AddFilm()
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is FilmViewModel.State.ShowFilm -> {
                    val fragment = FilmFragment().apply {
                        val json = Json.encodeToString(it.film)
                        arguments = bundleOf(FilmFragment.BUNDLE_FILM to json)
                    }
                    mainActivity.changeFragment(fragment, true)
                }
                is FilmViewModel.State.AddFilm -> {
                    shortToast("Not supported")
                }
            }
        })
    }

    private val filmObserver = Observer<Result<Film, Exception>> {
        if (it == null) return@Observer
        viewModel.loadedFilm.removeObservers(viewLifecycleOwner)
        it.onSuccess { film ->
            viewModel.state.postValue(FilmViewModel.State.ShowFilm(film))
        }.onFailure { error ->
            shortToast(error.message ?: "Film loading failed")
        }
    }
}
