package hu.geribruu.project_birdtable.ui

import android.R.attr.path
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.geribruu.project_birdtable.MainActivity
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.galery.adapter.GaleryRecyclerViewAdapter
import hu.geribruu.project_birdtable.galery.model.Bird
import kotlinx.android.synthetic.main.fragment_galery.*
import java.io.File


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GaleryFragment : Fragment() {

    private var birds = mutableListOf<Bird>()
    private lateinit var galeryRecyclerViewAdapter : GaleryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_galery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_galery).setOnClickListener {
            findNavController().navigate(R.id.action_GaleryFragment_to_CameraFragment)
        }

        initBirds()
    }

    private fun initBirds() {

        val directory: File = File(MainActivity.outputFileUri)
        val files = directory.listFiles()
        Log.d("Files", "Size: " + files.size)
        for (i in files.indices) {
            Log.d("Files", "FileName:" + files[i].name)
            birds.add(Bird("Sasmadár", files[i].name, files[i].absolutePath))
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        galeryRecyclerViewAdapter = GaleryRecyclerViewAdapter()
        galeryRecyclerViewAdapter.addAll(birds)
        recyclerview_galery.layoutManager = LinearLayoutManager(context)
        recyclerview_galery.adapter = galeryRecyclerViewAdapter
    }
}