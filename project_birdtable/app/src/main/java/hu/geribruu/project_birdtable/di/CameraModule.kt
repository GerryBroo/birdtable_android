package hu.geribruu.project_birdtable.di

import android.app.Activity
import android.content.Context
import androidx.camera.core.ImageCapture
import com.google.mlkit.vision.objects.ObjectDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.geribruu.project_birdtable.camera.CaptureManager
import hu.geribruu.project_birdtable.camera.PhotoCapture
import hu.geribruu.project_birdtable.camera.analyzer.ImageAnalyzer
import hu.geribruu.project_birdtable.camera.objectdetector.CustomObjectDetector
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CameraModule {

    @Singleton
    @Provides
    fun providesImageCapture() : ImageCapture {
        return ImageCapture.Builder().build()
    }

    @Singleton
    @Provides
    fun providesPhotoCapture(@ApplicationContext appContext: Context, imageCapture : ImageCapture) : PhotoCapture {
        return PhotoCapture(appContext, imageCapture)
    }

    @Singleton
    @Provides
    fun providesCaptureManager(photoCapture: PhotoCapture, repositoryImpl: BirdRepositoryImpl) : CaptureManager {
        return CaptureManager(photoCapture, repositoryImpl)
    }

    @Singleton
    @Provides
    fun providesBirdObjectDetector() : ObjectDetector {
        return CustomObjectDetector("bird_detection.tflite").objectDetector
    }

    @Singleton
    @Provides
    fun providesImageAnalyzer(objectDetector: ObjectDetector,
                              captureManager: CaptureManager) : ImageAnalyzer {
        return ImageAnalyzer(objectDetector, captureManager)
    }
}