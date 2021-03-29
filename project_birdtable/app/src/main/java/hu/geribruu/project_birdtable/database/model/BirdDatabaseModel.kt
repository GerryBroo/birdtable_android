package hu.geribruu.project_birdtable.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bird")
data class BirdDatabaseModel(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id : Long,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "captureDate")
    var captureDate : String,

    @ColumnInfo(name = "url")
    var imageUrl : String,
)