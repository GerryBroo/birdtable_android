package hu.geribruu.project_birdtable.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.ui.gallery.adapter.GalleryListAdapter
import kotlinx.android.synthetic.main.fragment_galery.*

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val galleryViewModel : GalleryViewModel by viewModels()
    private lateinit var adapter : GalleryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_galery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        view.findViewById<Button>(R.id.button_galery).setOnClickListener {
            findNavController().navigate(R.id.action_GaleryFragment_to_CameraFragment)
        }
    }

    private fun setupRecyclerView() {
        adapter = GalleryListAdapter()
        recyclerview_galery.adapter = adapter
        recyclerview_galery.layoutManager = LinearLayoutManager(context)

        activity?.let { activity ->
            galleryViewModel.birds.observe(activity, Observer { birds ->
                birds.let { adapter.submitList(it) }
            } )
        }
    }
}