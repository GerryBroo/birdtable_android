package hu.geribruu.project_birdtable.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BirdDAO {

    @Insert
    fun insert(bird : BirdDatabaseModel)

    @Query(value = "SELECT * FROM bird")
    fun getAll() : List<BirdDatabaseModel>
}