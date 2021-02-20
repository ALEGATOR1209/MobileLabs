package ua.kpi.comsys.ip8410.feature_gallery.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kpi.comsys.ip8410.feature_gallery.R

internal class PhotoAdapter(
    private val photos: MutableList<Uri>
) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_gallery_item, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    fun addPhoto(uri: Uri) {
        photos += uri
        notifyDataSetChanged()
    }

    fun update() {
        notifyDataSetChanged()
    }

    class PhotoHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(uri: Uri) {
            val imageView = itemView.findViewById<ImageView>(R.id.image)
            Picasso.get().load(uri).into(imageView)
        }
    }
}