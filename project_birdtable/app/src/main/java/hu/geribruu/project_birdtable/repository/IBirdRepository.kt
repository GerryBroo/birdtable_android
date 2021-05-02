package hu.geribruu.project_birdtable.repository

import androidx.lifecycle.MutableLiveData
import hu.geribruu.project_birdtable.database.model.Bird
import kotlinx.coroutines.flow.Flow

interface IBirdRepository {
    fun getBird(id: Long) : Flow<Bird>
    suspend fun insert(bird: Bird)
}