package hu.geribruu.project_birdtable.di

import android.content.Context
import androidx.camera.core.ImageCapture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.geribruu.project_birdtable.camera.CaptureManager
import hu.geribruu.project_birdtable.camera.PhotoCapture
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
}