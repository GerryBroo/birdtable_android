package hu.geribruu.project_birdtable.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import hu.geribruu.project_birdtable.database.BirdDAO
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import hu.geribruu.project_birdtable.repository.IBirdRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideBirdRepository(impl: BirdRepositoryImpl) : IBirdRepository
}