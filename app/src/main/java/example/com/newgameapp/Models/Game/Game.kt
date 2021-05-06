package example.com.newgameapp.Models.Game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    val name: String,
    val rating: String,
    val released: String,
    val methacritic: String?,
    val game_pk: String?,
    val game_image: String,
    @PrimaryKey val game_id: Int
)

/*val title: String,
val description: String,
@PrimaryKey val priority: Int*/