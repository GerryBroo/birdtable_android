package hu.geribruu.project_birdtable.camera.analyzer

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetector
import hu.geribruu.project_birdtable.camera.CaptureManager
import hu.geribruu.project_birdtable.camera.BirdInfoScreen
import javax.inject.Inject

class ImageAnalyzer @Inject constructor(
        private val objectDetector : ObjectDetector,
        private val captureManager : CaptureManager,
        private val infoScreen: BirdInfoScreen
) : ImageAnalysis.Analyzer {

    var isTakePhoto : Boolean = true

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

                        infoScreen.birdInfo(name)
                        if(isTakePhoto) {
                            captureManager.manageCapture(name)
                        }
                    }
                }
                .addOnFailureListener {
                    Log.v("ImageAnalyzer", "Error - ${it.message}")
                }
                .addOnCompleteListener {
                    imageProxy.close()
                    mediaImage.close()
                }
        }
    }
}