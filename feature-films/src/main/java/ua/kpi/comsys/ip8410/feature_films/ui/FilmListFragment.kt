package ua.kpi.comsys.ip8410.feature_films.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.FilmsAssetsDataSource
import ua.kpi.comsys.ip8410.feature_films.databinding.FragmentFilmListBinding
import ua.kpi.comsys.ip8410.feature_films.ui.recycler.FilmAdapter

class FilmListFragment : Fragment() {
    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ds = FilmsAssetsDataSource(requireActivity().assets, FILMS_FILE)
        with(binding.recycler) {
            adapter = FilmAdapter(ds)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            ))
        }
    }

    companion object {
        private const val FILMS_FILE = "MoviesList.txt"
    }
}