package hu.geribruu.project_birdtable.ui.detail

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.R
import kotlinx.android.synthetic.main.activity_bird_detail.*
import java.io.File

@AndroidEntryPoint
class BirdDetailActivity : AppCompatActivity() {

    private val viewModel: BirdDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_detail)

        viewModel.birdLiveData.observe(this, { bird ->
            textView_birdname_dy.text = bird.name
            textView_birdtime_dy.text = bird.captureDate
            val imgFile = File(bird.imageUrl)
            if (imgFile.exists()) {
                val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                imageView_bird.setImageBitmap(myBitmap)
            }
        })
    }
}