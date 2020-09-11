package kozakiewicz.szymon.julianonandroid.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Repetition(var name:String,var topic:String,var number:Int,var isReverse:Boolean){
    var numberOfDaysToNextRepetition:Int=1
    var creationTime:Long=Calendar.getInstance().timeInMillis
    var repetitionTime:Long=creationTime+numberOfDaysToNextRepetition*24*3600*1000

    companion object  {
        val levels= listOf<Int>(1,3,10,30,90,180,360)
    }
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
    fun getFullName():String
    {
        return "$name $number"
    }

    fun repetitionFinalize() {
        numberOfDaysToNextRepetition++
        repetitionTime=Calendar.getInstance().timeInMillis+numberOfDaysToNextRepetition*24*3600*1000

    }


}

