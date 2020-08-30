package kozakiewicz.szymon.julianonandroid.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JulianDao {

    @Insert
    fun insertRepetition(newRepetition: Repetition)

    @Delete
    fun delete(repetitionToDelete: Repetition)

    @Update
    fun update(repetitionToUpdate:Repetition)

    @Query("SELECT MAX(number) FROM Repetition WHERE name=(:repetitionName)")
    fun getMaxNumberFromDatabase(repetitionName: String): Int

    @Query("SELECT * FROM Repetition")
    fun getAllRepetitionsForToday():LiveData<List<Repetition>>


}