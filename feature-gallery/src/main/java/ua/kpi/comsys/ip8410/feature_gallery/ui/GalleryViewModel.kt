package ua.kpi.comsys.ip8410.feature_gallery.ui

import android.net.Uri
import androidx.lifecycle.ViewModel

internal class GalleryViewModel : ViewModel() {
    val photos = mutableListOf<Uri>()
}
