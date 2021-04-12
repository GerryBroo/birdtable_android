package hu.geribruu.project_birdtable.camera

import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.ui.viewmodels.CameraViewModel
import java.text.SimpleDateFormat
import java.util.*


class CaptureManager(
        private val photoCapture: PhotoCapture,
        private val viewModel: CameraViewModel
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

            viewModel.insert(Bird(0, name, date, url))

            isCapture = false
        }
    }
}