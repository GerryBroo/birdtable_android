package hu.geribruu.project_birdtable.repository

import androidx.lifecycle.LiveData
import hu.geribruu.project_birdtable.database.model.Bird
import kotlinx.coroutines.flow.Flow

interface IBirdRepository {
    suspend fun insert(bird: Bird)
}