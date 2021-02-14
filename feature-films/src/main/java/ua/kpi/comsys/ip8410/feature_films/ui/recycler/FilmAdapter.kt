package ua.kpi.comsys.ip8410.feature_films.ui.recycler

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8410.feature_films.R
import ua.kpi.comsys.ip8410.feature_films.core.datasource.FilmDataSource
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film

internal class FilmAdapter(
    private val dataSource: FilmDataSource
) : RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    private var films = dataSource.getFilms()
    private var filmsToShow = films

    private var onFilmClickListener: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_film_item, parent, false)
        return FilmHolder(view)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = filmsToShow[position]
        val image = dataSource.getPoster(film)
        holder.bind(film, image) { onFilmClickListener?.invoke(film) }
    }

    override fun getItemCount(): Int = filmsToShow.size

    private fun List<Film>.startsWith(name: String): List<Film> = filter {
        it.title.startsWith(name, ignoreCase = true)
    }

    private fun List<Film>.onlyContains(name: String): List<Film> = filter {
        val contains = it.title.contains(name, ignoreCase = true)
        val starts = it.title.startsWith(name, ignoreCase = true)
        contains and !starts
    }

    fun search(name: String, onFound: (empty: Boolean) -> Unit) {
        filmsToShow = if (name.isNotBlank()) {
            films.startsWith(name) + films.onlyContains(name)
        } else {
            films
        }
        notifyDataSetChanged()

        onFound(filmsToShow.isEmpty())
    }

    fun setOnFilmClickListener(cb: (Film) -> Unit) {
        onFilmClickListener = cb
    }

    fun update() {
        films = dataSource.getFilms()
        filmsToShow = films
        notifyDataSetChanged()
    }

    inner class FilmHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val year = view.findViewById<TextView>(R.id.year)
        private val type = view.findViewById<TextView>(R.id.type)
        private val poster = view.findViewById<ImageView>(R.id.poster)

        fun bind(film: Film, image: Drawable? = null, onClick: () -> Unit) {
            title.text = film.title
            year.text = film.year
            type.text = film.type
            poster.setImageDrawable(
                image ?: ContextCompat.getDrawable(itemView.context, R.drawable.ic_film_no_photo)
            )
            itemView.setOnClickListener { onClick() }
        }
    }
}
