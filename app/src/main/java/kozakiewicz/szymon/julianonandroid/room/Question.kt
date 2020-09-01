package kozakiewicz.szymon.julianonandroid.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Repetition::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("parentRepetitionId"),
    onDelete = ForeignKey.CASCADE
    )
))
data class Question(var question: String,var answer:String,var parentRepetitionId:Long)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

}