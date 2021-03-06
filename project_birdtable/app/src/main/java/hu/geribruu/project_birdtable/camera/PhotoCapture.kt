package hu.geribruu.project_birdtable.camera

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import hu.geribruu.project_birdtable.MainActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PhotoCapture (
        private var context: Context?,
        private var imageCapture: ImageCapture?
) {

    private var outputDirectory: File = File(MainActivity.outputFileUri)

    companion object {
        private const val TAG = "CameraXBasic"
        const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }

    fun takePhoto() : String {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return ""

        // Create time-stamped output file to hold the image
        val photoFile = File(
                outputDirectory,
                SimpleDateFormat(PhotoCapture.FILENAME_FORMAT, Locale.US
                ).format(System.currentTimeMillis()) + ".jpg")


        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
                outputOptions, ContextCompat.getMainExecutor(context), object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
            }

            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                val msg = "Photo capture succeeded: $savedUri"
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                Log.d(TAG, msg)
            }
        })

        return photoFile.absolutePath
    }
}