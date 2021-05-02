package hu.geribruu.project_birdtable.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import hu.geribruu.project_birdtable.database.BirdDAO
import hu.geribruu.project_birdtable.database.model.Bird
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BirdRepositoryImpl @Inject constructor(private val birdDAO: BirdDAO) : IBirdRepository {

    val birds : Flow<List<Bird>> = birdDAO.getAll()

    override fun getBird(id: Long) : Flow<Bird> {
        return birdDAO.getBird(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(bird: Bird) {
        birdDAO.insert(bird)
    }
}