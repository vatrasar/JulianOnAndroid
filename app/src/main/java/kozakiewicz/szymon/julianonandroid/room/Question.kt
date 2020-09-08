package kozakiewicz.szymon.julianonandroid.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter


@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Repetition::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("parentRepetitionId"),
    onDelete = ForeignKey.CASCADE
    )
))
data class Question(var question: String, var answer:String, var parentRepetitionId:Int,var status:Status)
{




    @PrimaryKey(autoGenerate = true)
    var id:Int=0
    fun updateStatus(newStatus: Status, isRepetitionReverse:Boolean)
    {
        if(newStatus==Status.DONT_KNOW)
            status=Status.DONT_KNOW
        else if(isRepetitionReverse)//status==know
        {
            if(status==Status.KNOW_ONE_WAY)
            {
                status=Status.KNOW
            }
            else
                status=Status.KNOW_ONE_WAY


        }
        else
        {
            status=Status.KNOW
        }
    }



}

enum class Status{
    KNOW,DONT_KNOW,KNOW_ONE_WAY,UNCHECKED
}

