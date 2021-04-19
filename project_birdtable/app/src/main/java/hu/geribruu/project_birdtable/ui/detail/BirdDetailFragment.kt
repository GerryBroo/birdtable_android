package hu.geribruu.project_birdtable.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.R
import kotlinx.android.synthetic.main.fragment_bird_detail.*

@AndroidEntryPoint
class BirdDetailFragment : Fragment() {

    //val ss:String = activity?.intent?.getStringExtra("samplename").toString()

    companion object {
        fun newInstance() = BirdDetailFragment()
    }

    private val viewModelBird: BirdDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModelBird.showTextDataNotifier.observe(viewLifecycleOwner, { data ->
            message.text = data
        })
        Log.d("ASD", viewModelBird.showTextDataNotifier.value.toString())



        return inflater.inflate(R.layout.fragment_bird_detail, container, false)
    }
}