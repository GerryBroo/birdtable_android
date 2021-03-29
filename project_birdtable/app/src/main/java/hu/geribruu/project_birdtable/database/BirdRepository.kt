package hu.geribruu.project_birdtable.database

import androidx.annotation.WorkerThread
import hu.geribruu.project_birdtable.database.model.BirdDatabaseModel
import kotlinx.coroutines.flow.Flow

class BirdRepository(private val birdDAO: BirdDAO) {

    val birds : Flow<List<BirdDatabaseModel>> = birdDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(birdDatabaseModel: BirdDatabaseModel) {
        birdDAO.insert(birdDatabaseModel)
    }

}