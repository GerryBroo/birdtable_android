package hu.geribruu.project_birdtable

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import hu.geribruu.project_birdtable.database.BirdRepository
import hu.geribruu.project_birdtable.database.BirdRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class BirdApplication : Application() {

    /*val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { BirdRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BirdRepository(database.birdDAO()) }*/
}