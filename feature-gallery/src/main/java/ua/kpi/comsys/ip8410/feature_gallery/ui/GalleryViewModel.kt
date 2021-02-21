package ua.kpi.comsys.ip8410.feature_gallery.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Result
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.repository.ImageRepository

internal class GalleryViewModel : ViewModel() {
    var repository: ImageRepository? = null
    private val request = "fun+party"
    private val count = 30

    val photos = MutableLiveData<Result<ImageCollection, Exception>>()
    val loading = MutableLiveData(false)
    val picasso = Picasso.get()

    fun loadImages() {
        if (loading.value == true) return

        loading.value = true

        viewModelScope.launch {
            photos.postValue(repository?.getImages(request, count))
            loading.postValue(false)
        }
    }
}
