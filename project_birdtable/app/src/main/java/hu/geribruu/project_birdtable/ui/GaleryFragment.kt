package hu.geribruu.project_birdtable.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.database.BirdDatabase
import hu.geribruu.project_birdtable.galery.adapter.GaleryRecyclerViewAdapter
import hu.geribruu.project_birdtable.galery.model.Bird
import kotlinx.android.synthetic.main.fragment_galery.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GaleryFragment : Fragment() {

    private var birds = mutableListOf<Bird>()
    private var galeryRecyclerViewAdapter : GaleryRecyclerViewAdapter  = GaleryRecyclerViewAdapter()

    private lateinit var birdDatabase : BirdDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        birdDatabase = Room.databaseBuilder<BirdDatabase>(
            requireActivity().applicationContext,
            BirdDatabase::class.java,
            "bird"
        ).build()

        loadAllBirds()

        return inflater.inflate(R.layout.fragment_galery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_galery).setOnClickListener {
            findNavController().navigate(R.id.action_GaleryFragment_to_CameraFragment)
        }

        setupRecyclerView()
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

    private fun loadAllBirds() {
        val dbThread = Thread {
            birdDatabase.birdDAO().getAll().forEach() {
                val bird = Bird("", "", "")
                bird.name = it.name
                bird.captureDate = it.captureDate
                bird.imageUrl = it.imageUrl

                birds.add(bird)
            }

            /*requireActivity().runOnUiThread {
                setupRecyclerView()
            }*/
        }
        dbThread.start()
    }

    private fun setupRecyclerView() {
        if(galeryRecyclerViewAdapter.itemCount == 0) {
            galeryRecyclerViewAdapter.addAll(birds)
        }

        recyclerview_galery.layoutManager = LinearLayoutManager(context)
        recyclerview_galery.adapter = galeryRecyclerViewAdapter
    }
}