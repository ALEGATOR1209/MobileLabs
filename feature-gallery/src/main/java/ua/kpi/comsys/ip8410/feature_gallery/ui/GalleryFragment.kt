package ua.kpi.comsys.ip8410.feature_gallery.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8410.core_ui.MainFragment
import ua.kpi.comsys.ip8410.feature_gallery.databinding.FragmentGalleryBinding
import ua.kpi.comsys.ip8410.feature_gallery.ui.adapter.GalleryLayoutManager
import ua.kpi.comsys.ip8410.feature_gallery.ui.adapter.PhotoAdapter

class GalleryFragment : MainFragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GalleryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAdapter = PhotoAdapter(viewModel.photos)

        with(binding.recycler) {
            adapter = photoAdapter
            layoutManager = GalleryLayoutManager()
        }

        binding.fab.setOnClickListener {
            pickPhoto()
        }
    }

    private fun pickPhoto() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != PICK_IMAGE || resultCode != Activity.RESULT_OK) return
        data?.data?.let(this::addPhoto)
    }

    private fun addPhoto(uri: Uri) {
        viewModel.photos += uri
        photoAdapter.update()
    }

    companion object {
        const val PICK_IMAGE = 100
    }
}
