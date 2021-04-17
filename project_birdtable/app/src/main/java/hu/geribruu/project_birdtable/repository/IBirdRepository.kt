package hu.geribruu.project_birdtable.repository

import hu.geribruu.project_birdtable.database.model.Bird

interface IBirdRepository {
    suspend fun insert(bird: Bird)
}