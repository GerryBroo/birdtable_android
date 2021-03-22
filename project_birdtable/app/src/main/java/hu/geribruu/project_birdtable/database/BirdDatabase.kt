package hu.geribruu.project_birdtable.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BirdDatabaseModel::class], version = 1)
abstract class BirdDatabase : RoomDatabase() {
    abstract fun birdDAO() : BirdDAO
}