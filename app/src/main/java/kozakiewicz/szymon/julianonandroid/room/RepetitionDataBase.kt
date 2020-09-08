package kozakiewicz.szymon.julianonandroid.room

import android.content.Context
import androidx.room.*

@Database(entities = [Repetition::class,Question::class],version = 5,exportSchema = false)
@TypeConverters(Converters::class)
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

