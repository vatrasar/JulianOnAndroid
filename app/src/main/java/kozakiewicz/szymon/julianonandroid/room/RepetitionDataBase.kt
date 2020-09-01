package kozakiewicz.szymon.julianonandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Repetition::class,Question::class],version = 4,exportSchema = false)
abstract class RepetitionDataBase:RoomDatabase()
{
    abstract fun DragDao():JulianDao
    companion object{
        var instance:RepetitionDataBase?=null
        fun getInstance(context:Context):RepetitionDataBase?
        {
            if(instance==null) {
                instance = Room.databaseBuilder(context, RepetitionDataBase::class.java, "repetitionDatabase").fallbackToDestructiveMigration().build()
            }
            return instance
        }

    }

}