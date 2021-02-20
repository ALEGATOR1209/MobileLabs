package ua.kpi.comsys.ip8410.feature_films.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kpi.comsys.ip8410.feature_films.R
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film

internal class FilmAdapter(
    private var films: List<Film> = listOf()
) : RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    private var onFilmClickListener: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_film_item, parent, false)
        return FilmHolder(view)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = films[position]
        val image = films[position].poster
        holder.bind(film, image) { onFilmClickListener?.invoke(film) }
    }

    override fun getItemCount(): Int = films.size

    fun setOnFilmClickListener(cb: (Film) -> Unit) {
        onFilmClickListener = cb
    }

    fun update(newFilms: List<Film>) {
        films = newFilms
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        notifyItemRemoved(position)
    }

    inner class FilmHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val year = view.findViewById<TextView>(R.id.year)
        private val type = view.findViewById<TextView>(R.id.type)
        private val poster = view.findViewById<ImageView>(R.id.poster)

        fun bind(film: Film, image: String? = null, onClick: () -> Unit) {
            title.text = film.title
            year.text = film.year
            type.text = film.type
            if (image != null && image != Film.NO_POSTER) {
                Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.pic_loader)
                    .error(R.drawable.ic_film_no_photo)
                    .into(poster)
            } else {
                val noImageIcon = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_film_no_photo
                )
                poster.setImageDrawable(noImageIcon)
            }
            itemView.setOnClickListener { onClick() }
        }
    }
}
