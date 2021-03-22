package hu.geribruu.project_birdtable.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.galery.adapter.GaleryRecyclerViewAdapter
import hu.geribruu.project_birdtable.galery.model.Bird
import kotlinx.android.synthetic.main.fragment_galery.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GaleryFragment : Fragment() {

    private var birds = mutableListOf<Bird>()
    private lateinit var galeryRecyclerViewAdapter : GaleryRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        val b1 = Bird("Sas", "Most", "a telefonon")
        val b2 = Bird("Papagáj", "Most", "a telefonon")
        val b3 = Bird("Galamb", "Most", "a telefonon")
        birds.add(b1)
        birds.add(b2)
        birds.add(b3)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        galeryRecyclerViewAdapter = GaleryRecyclerViewAdapter()
        galeryRecyclerViewAdapter.addAll(birds)
        recyclerview_galery.layoutManager = LinearLayoutManager(context)
        recyclerview_galery.adapter = galeryRecyclerViewAdapter
    }
}