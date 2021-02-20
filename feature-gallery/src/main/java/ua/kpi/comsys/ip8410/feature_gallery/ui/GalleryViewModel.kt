package ua.kpi.comsys.ip8410.feature_gallery.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Result
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8410.feature_gallery.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.remote.ImagesRemoteDataSource

internal class GalleryViewModel : ViewModel() {
    private val ds: ImagesDataSource = ImagesRemoteDataSource("19193969-87191e5db266905fe8936d565")
    private val request = "fun+party"
    private val count = 30

    val photos = MutableLiveData<Result<ImageCollection, Exception>>()
    val loading = MutableLiveData(false)
    val picasso = Picasso.get()

    fun loadImages() {
        if (loading.value == true) return

        loading.value = true

        viewModelScope.launch {
            photos.postValue(ds.getImages(request, count))
            loading.postValue(false)
        }
    }
}
