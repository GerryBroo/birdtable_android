package hu.geribruu.birddetection_first

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import hu.geribruu.birddetection_first.databinding.ActivityMainBinding
import hu.geribruu.birddetection_first.utils.Draw

class ImageAnalyzer(
    private var context: Context,
    private var binding: ActivityMainBinding) : ImageAnalysis.Analyzer {

    private val localModel = LocalModel.Builder()
        .setAssetFilePath("bird_detection.tflite")
        .build()

    private val customObjectDetectorOption = CustomObjectDetectorOptions.Builder(localModel)
        .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
        .enableClassification()
        .setClassificationConfidenceThreshold(0.8f)
        .setMaxPerObjectLabelCount(1)
        .build()

    private val objectDetector = ObjectDetection.getClient(customObjectDetectorOption)

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        val rotationDegrees = imageProxy.imageInfo.rotationDegrees

        val mediaImage = imageProxy.image

        if(mediaImage != null) {
            val processImage = InputImage.fromMediaImage(mediaImage, rotationDegrees)

            objectDetector.process(processImage)
                .addOnSuccessListener { objects ->

                    for (detectedObject in objects) {

                        if(binding.parentLayout.childCount > 1)  {
                            binding.parentLayout.removeViewAt(1)
                        }

                        val element = Draw(context = context,
                            rect = detectedObject.boundingBox,
                            text = detectedObject.labels.firstOrNull()?.text ?: "Undefined")
                        binding.parentLayout.addView(element,1)
                    }
                }
                .addOnFailureListener {
                    Log.v("MainActivity", "Error - ${it.message}")
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}