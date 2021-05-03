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
import hu.geribruu.project_birdtable.navigator.AppNavigator
import hu.geribruu.project_birdtable.ui.gallery.adapter.GalleryListAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment : Fragment(), GalleryListAdapter.BirdClickListener {

    @Inject lateinit var navigator: AppNavigator
    private val galleryViewModel : GalleryViewModel by viewModels()
    private lateinit var adapter : GalleryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = GalleryListAdapter(this)
        recyclerview_gallery.adapter = adapter
        recyclerview_gallery.layoutManager = LinearLayoutManager(context)

        activity?.let { activity ->
            galleryViewModel.birds.observe(activity, Observer { birds ->
                birds.let { adapter.submitList(it) }
            } )
        }
    }


    override fun onClick(id: Long) {
        navigator.navigateToBirdDetail(id)
    }
}