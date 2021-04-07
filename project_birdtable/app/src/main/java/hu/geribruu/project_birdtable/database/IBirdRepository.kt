package hu.geribruu.project_birdtable.database

import hu.geribruu.project_birdtable.database.model.BirdDatabaseModel

interface IBirdRepository {
    suspend fun insert(birdDatabaseModel: BirdDatabaseModel)
}