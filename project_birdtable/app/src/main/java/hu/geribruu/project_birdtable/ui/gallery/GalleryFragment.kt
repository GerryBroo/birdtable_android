package hu.geribruu.project_birdtable.ui.gallery

import android.content.Intent
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
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.ui.detail.BirdDetailActivity
import hu.geribruu.project_birdtable.ui.detail.BirdDetailFragment
import hu.geribruu.project_birdtable.ui.gallery.adapter.GalleryListAdapter
import hu.geribruu.project_birdtable.ui.detail.BirdDetailViewModel.Companion.BIRD_ID
import kotlinx.android.synthetic.main.fragment_galery.*

@AndroidEntryPoint
class GalleryFragment : Fragment(), GalleryListAdapter.BirdClickListener {

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
        adapter = GalleryListAdapter(this)
        recyclerview_galery.adapter = adapter
        recyclerview_galery.layoutManager = LinearLayoutManager(context)

        activity?.let { activity ->
            galleryViewModel.birds.observe(activity, Observer { birds ->
                birds.let { adapter.submitList(it) }
            } )
        }
    }

    override fun onClick(bird: Bird, position: Int) {
       /* val intent = Intent(context, BirdDetailActivity()::class.java)
        intent.putExtra(Bird_ID, bird.id)
        context?.startActivity(intent)*/

        startActivity(
            Intent(context, BirdDetailActivity()::class.java).apply {
                putExtra(BIRD_ID, bird.name)
            }
        )
    }
}