package hu.geribruu.project_birdtable.camera.objectdetector

import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions

class ObjectDetector(private val tflite : String) {

    private val localModel : LocalModel = LocalModel.Builder()
            .setAssetFilePath(tflite)
            .build()

    private val customObjectDetectorOption = CustomObjectDetectorOptions.Builder(localModel)
            .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
            .enableClassification()
            .setClassificationConfidenceThreshold(0.8f)
            .setMaxPerObjectLabelCount(1)
            .build()

    private var objectDetector = ObjectDetection.getClient(customObjectDetectorOption)
        get() = field

}