package hu.geribruu.project_birdtable.camera.objectdetector

import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import javax.inject.Inject

class CustomObjectDetector(string : String) {

    private val localModel : LocalModel = LocalModel.Builder()
            .setAssetFilePath(string)
            .build()
        get() = field

    private val customObjectDetectorOption = CustomObjectDetectorOptions.Builder(localModel)
            .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
            .enableClassification()
            .setClassificationConfidenceThreshold(0.8f)
            .setMaxPerObjectLabelCount(1)
            .build()

   val objectDetector = ObjectDetection.getClient(customObjectDetectorOption)
}