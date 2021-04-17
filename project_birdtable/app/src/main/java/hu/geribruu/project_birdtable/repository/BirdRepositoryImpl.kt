package hu.geribruu.project_birdtable.repository

import androidx.annotation.WorkerThread
import hu.geribruu.project_birdtable.database.BirdDAO
import hu.geribruu.project_birdtable.database.model.Bird
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BirdRepositoryImpl @Inject constructor(private val birdDAO: BirdDAO) : IBirdRepository {

    val birds : Flow<List<Bird>> = birdDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(bird: Bird) {
        birdDAO.insert(bird)
    }
}