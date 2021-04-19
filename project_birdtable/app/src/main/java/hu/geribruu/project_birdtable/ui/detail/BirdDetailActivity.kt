package hu.geribruu.project_birdtable.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.R

@AndroidEntryPoint
class BirdDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_detail)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BirdDetailFragment.newInstance())
                    .commitNow()
        }
    }
}