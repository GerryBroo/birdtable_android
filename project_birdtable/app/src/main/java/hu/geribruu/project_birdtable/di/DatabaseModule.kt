package hu.geribruu.project_birdtable.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.geribruu.project_birdtable.database.BirdDAO
import hu.geribruu.project_birdtable.database.BirdRoomDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BirdRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            BirdRoomDatabase::class.java,
            "bird.db"
        ).build()
    }

    @Provides
    fun provideCharacterDao(database: BirdRoomDatabase): BirdDAO {
        return database.birdDAO()
    }
}