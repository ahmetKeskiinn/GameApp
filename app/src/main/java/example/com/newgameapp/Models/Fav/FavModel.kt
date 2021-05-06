package example.com.newgameapp.Models.Fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class FavModel(
    val name: String,
    val released: String,
    val methacritic: String?,
    val rating : String?,
    val game_image: String,
    @PrimaryKey val game_id: String
)