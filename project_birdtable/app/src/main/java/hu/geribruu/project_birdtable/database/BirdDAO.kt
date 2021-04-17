package hu.geribruu.project_birdtable.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hu.geribruu.project_birdtable.database.model.Bird
import kotlinx.coroutines.flow.Flow

@Dao
interface BirdDAO {

    @Query(value = "SELECT * FROM bird")
    fun getAll() : Flow<List<Bird>>

    @Insert
    suspend fun insert(bird : Bird)

    @Query("DELETE FROM bird")
    suspend fun deleteAll()


}