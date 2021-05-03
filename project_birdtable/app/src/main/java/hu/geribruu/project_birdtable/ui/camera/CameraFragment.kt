package hu.geribruu.project_birdtable.ui.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.objects.ObjectDetector
import dagger.hilt.android.AndroidEntryPoint
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.camera.CaptureManager
import hu.geribruu.project_birdtable.camera.analyzer.ImageAnalyzer
import hu.geribruu.project_birdtable.databinding.FragmentCameraBinding
import javax.inject.Inject

@AndroidEntryPoint
class CameraFragment : Fragment(){

    /** Binding */
    private var _binding : FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CameraViewModel by viewModels()

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    @Inject lateinit var imageAnalyzer: ImageAnalyzer
    @Inject lateinit var imageCapture: ImageCapture

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /** Permissions Request */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(activity,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        activity?.let { fragmentActivity ->
            ContextCompat.checkSelfPermission(
                fragmentActivity.baseContext, it)
        } == PackageManager.PERMISSION_GRANTED
    }

    /** cameraX **/
    private fun startCamera() {
        Log.d("TAG", "START CAMERA")

        cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) } as ListenableFuture<ProcessCameraProvider>

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            bindPreview(cameraProvider = cameraProvider)

        }, ContextCompat.getMainExecutor(context))
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    private fun bindPreview(cameraProvider: ProcessCameraProvider) {

        val preview = Preview.Builder().build()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        preview.setSurfaceProvider(binding.previewView.surfaceProvider)

        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context), imageAnalyzer)

        cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview, imageCapture, imageAnalysis)
    }

    override fun onStop() {
        super.onStop()
        cameraProviderFuture.get().unbindAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}