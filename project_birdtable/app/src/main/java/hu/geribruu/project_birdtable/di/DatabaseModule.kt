package hu.geribruu.project_birdtable.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.geribruu.project_birdtable.database.BirdDAO
import hu.geribruu.project_birdtable.database.BirdRoomDatabase
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import hu.geribruu.project_birdtable.repository.IBirdRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    private val applicationScope = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): BirdRoomDatabase {
        return BirdRoomDatabase.getDatabase(appContext, applicationScope)
    }

    @Provides
    fun provideCharacterDao(database: BirdRoomDatabase): BirdDAO = database.birdDAO()


 /*   @Provides
    fun provideRepository(birdDao : BirdDAO): BirdRepositoryImpl {
        return BirdRepositoryImpl(birdDao)
    }*/
}