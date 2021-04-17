package hu.geribruu.project_birdtable.camera.analyzer

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetector
import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.camera.CaptureManager
import hu.geribruu.project_birdtable.camera.PhotoCapture
import hu.geribruu.project_birdtable.databinding.FragmentCameraBinding
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import hu.geribruu.project_birdtable.ui.camera.CameraViewModel
import javax.inject.Inject
import javax.security.auth.callback.Callback

class ImageAnalyzer @Inject constructor(
        private val objectDetector : ObjectDetector,
        private val captureManager : CaptureManager
) : ImageAnalysis.Analyzer {

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

                        //binding.tvCameraFragment.text = name
                        captureManager.manageCapture(name)
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