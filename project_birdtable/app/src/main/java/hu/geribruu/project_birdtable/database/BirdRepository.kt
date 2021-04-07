package hu.geribruu.project_birdtable.database

import androidx.annotation.WorkerThread
import hu.geribruu.project_birdtable.database.model.BirdDatabaseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BirdRepository @Inject constructor(private val birdDAO: BirdDAO) : IBirdRepository {

    val birds : Flow<List<BirdDatabaseModel>> = birdDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(birdDatabaseModel: BirdDatabaseModel) {
        birdDAO.insert(birdDatabaseModel)
    }

}