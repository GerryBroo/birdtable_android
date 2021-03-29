package hu.geribruu.project_birdtable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import hu.geribruu.project_birdtable.database.model.BirdDatabaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(BirdDatabaseModel::class), version = 1)
abstract class BirdRoomDatabase : RoomDatabase() {

    abstract fun birdDAO() : BirdDAO

    private class BirdDatabaseCallback (
        private val scope : CoroutineScope ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    var birdDao = database.birdDAO()

                    birdDao.deleteAll()
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE : BirdRoomDatabase? = null

        fun getDatabase(
            context : Context,
            scope : CoroutineScope
        ) : BirdRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        BirdRoomDatabase::class.java,
                        "bird"
                    )
                    .addCallback(BirdDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}