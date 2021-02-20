package ua.kpi.comsys.ip8410.feature_gallery.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8410.feature_gallery.R
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.Image
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection

internal class PhotoAdapter(
    private var photos: List<Image> = listOf(),
    private val picasso: Picasso
) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_gallery_item, parent, false)
        return PhotoHolder(view, picasso)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    fun addPhoto(image: Image) {
        photos += image
        notifyDataSetChanged()
    }

    fun update(newPhotos: ImageCollection) {
        photos = newPhotos.hits
        notifyDataSetChanged()
    }

    class PhotoHolder(view: View, private val picasso: Picasso) : RecyclerView.ViewHolder(view) {
        fun bind(image: Image) {
            picasso
                .load(image.largeImageURL)
                .error(R.drawable.pic_no_photo)
                .placeholder(R.drawable.pic_loader)
                .into(itemView.findViewById<ImageView>(R.id.image))
        }
    }
}
