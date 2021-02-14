package ua.kpi.comsys.ip8410.feature_films.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ua.kpi.comsys.ip8410.core_ui.MainFragment
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.FilmsAssetsDataSource
import ua.kpi.comsys.ip8410.feature_films.databinding.FragmentFilmListBinding
import ua.kpi.comsys.ip8410.feature_films.ui.add_film.AddFilmFragment
import ua.kpi.comsys.ip8410.feature_films.ui.recycler.FilmAdapter

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

        if (viewModel.ds == null) {
            viewModel.ds = FilmsAssetsDataSource(requireActivity().assets)
        }
        val filmAdapter = FilmAdapter(viewModel.ds!!).apply {
            setOnFilmClickListener {
                viewModel.state.value = FilmViewModel.State.ShowFilm(it)
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
            filmAdapter.search(it.toString()) { empty -> binding.noFilms.isVisible = empty }
        }

        binding.addFilm.setOnClickListener {
            viewModel.state.value = FilmViewModel.State.AddFilm()
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is FilmViewModel.State.ShowFilm -> {
                    val fragment = FilmFragment().apply {
                        arguments = bundleOf(FilmFragment.BUNDLE_FILM_ID to it.film.imdbID)
                    }
                    mainActivity.changeFragment(fragment, true)
                }
                is FilmViewModel.State.AddFilm -> {
                    if (it.film == null) {
                        mainActivity.changeFragment(AddFilmFragment(), true)
                    } else {
                        viewModel.ds?.addFilm(it.film)
                        binding.searchRequest.text = null
                        filmAdapter.update()
                    }
                }
            }
        })
    }
}
