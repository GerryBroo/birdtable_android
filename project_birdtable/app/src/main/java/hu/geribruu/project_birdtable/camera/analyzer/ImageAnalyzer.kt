package hu.geribruu.project_birdtable.camera.analyzer

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetector
import hu.geribruu.project_birdtable.camera.PhotoCapture
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.databinding.FragmentCameraBinding
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import hu.geribruu.project_birdtable.ui.viewmodels.CameraViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ImageAnalyzer(
    private var binding: FragmentCameraBinding,
    private val objectDetector : ObjectDetector,
    private val photoCapture: PhotoCapture,
   /* private val cameraVM : CameraViewModel*/
) : ImageAnalysis.Analyzer {

    @Inject
    lateinit var repository: BirdRepositoryImpl

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        val rotationDegrees = imageProxy.imageInfo.rotationDegrees

        val mediaImage = imageProxy.image

        if(mediaImage != null) {
            val processImage = InputImage.fromMediaImage(mediaImage, rotationDegrees)

            objectDetector.process(processImage)
                .addOnSuccessListener { objects ->

                    for (detectedObject in objects) {

                        val name = detectedObject.labels.firstOrNull()?.text ?: "Undefined"
                        val date = SimpleDateFormat(PhotoCapture.FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
                        val url = photoCapture.takePhoto()

                        binding.tvCameraFragment.text = name

                       GlobalScope.launch {
                            repository.insert(Bird(0, name, date, url))
                        }

                        //cameraVM.insert(Bird(0, name, date, url))

                    }
                }
                .addOnFailureListener {
                    Log.v("ImageAnalyzer", "Error - ${it.message}")
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}