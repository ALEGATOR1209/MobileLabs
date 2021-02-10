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
    private val films = dataSource.getFilms()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_film_item, parent, false)
        return FilmHolder(view)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val image = dataSource.getPoster(films[position])
        holder.bind(films[position], image)
    }

    override fun getItemCount(): Int = films.size

    inner class FilmHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val year = view.findViewById<TextView>(R.id.year)
        private val type = view.findViewById<TextView>(R.id.type)
        private val poster = view.findViewById<ImageView>(R.id.poster)

        fun bind(film: Film, image: Drawable? = null) {
            title.text = film.title
            year.text = film.year
            type.text = film.type
            poster.setImageDrawable(
                image ?: ContextCompat.getDrawable(itemView.context, R.drawable.ic_film_no_photo)
            )
        }
    }
}
