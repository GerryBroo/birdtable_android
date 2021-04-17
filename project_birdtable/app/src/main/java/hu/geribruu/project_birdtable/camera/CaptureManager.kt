package hu.geribruu.project_birdtable.camera

import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import hu.geribruu.project_birdtable.ui.camera.CameraViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CaptureManager @Inject constructor(
        private val photoCapture: PhotoCapture,
        private val repositoryImpl: BirdRepositoryImpl
) {

    private var timer : Timer = Timer()
    private lateinit var timerTask : TimerTask

    private var isCapture : Boolean = true

    init {
        initializeTimer()
        timer.schedule(timerTask, 0, 10000)
    }

    private fun initializeTimer() {
        timerTask = object : TimerTask() {
            override fun run() {
                isCapture = true
            }
        }
    }

    fun manageCapture(name: String) {

        if(isCapture && name != "Undefined" && name != "None") {

            val date = SimpleDateFormat(PhotoCapture.FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
            val url = photoCapture.takePhoto()

            GlobalScope.launch {
                repositoryImpl.insert(Bird(0, name, date, url))
            }

            isCapture = false
        }
    }
}