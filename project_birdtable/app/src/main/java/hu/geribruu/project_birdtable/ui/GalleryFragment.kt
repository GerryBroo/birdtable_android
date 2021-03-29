package hu.geribruu.project_birdtable.ui

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
import hu.geribruu.project_birdtable.BirdApplication
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.galery.adapter.GalleryListAdapter
import hu.geribruu.project_birdtable.ui.viewmodels.GalleryViewModel
import hu.geribruu.project_birdtable.ui.viewmodels.GalleryViewModelFactory
import kotlinx.android.synthetic.main.fragment_galery.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GalleryFragment : Fragment() {

    private val galleryViewModel : GalleryViewModel by viewModels {
        GalleryViewModelFactory((activity?.application as BirdApplication).repository)
    }

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

    /*  private fun initBirds() {
          val directory: File = File(MainActivity.outputFileUri)
          val files = directory.listFiles()
          Log.d("Files", "Size: " + files.size)
          for (i in files.indices) {
              Log.d("Files", "FileName:" + files[i].name)

              birdDatabase.birdDAO().insert(BirdDatabaseModel(galeryRecyclerViewAdapter.itemCount.toLong() + 1,"Sasmadár", files[i].name, files[i].absolutePath))
          }
      }*/
}