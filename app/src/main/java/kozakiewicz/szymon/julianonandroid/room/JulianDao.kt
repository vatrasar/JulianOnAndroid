package kozakiewicz.szymon.julianonandroid.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JulianDao {

    @Insert
    fun insertRepetition(newRepetition: Repetition):Long

    @Insert
    fun insertQuestion(newQuestion: Question)

    @Delete
    fun delete(repetitionToDelete: Repetition)

    @Update
    fun update(repetitionToUpdate:Repetition)

    @Update
    fun updateQuestion(questionToUpdate:Question)

    @Query("SELECT MAX(number) FROM Repetition WHERE name=(:repetitionName)")
    fun getMaxNumberFromDatabase(repetitionName: String): Int

    @Query("SELECT * FROM Repetition")
    fun getAllRepetitionsForToday():LiveData<List<Repetition>>

    @Query("SELECT * FROM Question WHERE parentRepetitionId=(:repetitionId)")
    fun getAllQuestionsOfRepetition(repetitionId:Int):List<Question>

    @Query("SELECT * FROM Repetition WHERE id=(:repetitionId)")
    fun getRepetition(repetitionId: Int):Repetition

    @Query("SELECT * FROM Question WHERE id=(:questionId)")
    fun getQuestion(questionId: Int):Question

    @Query("SELECT Repetition.id FROM Repetition LEFT JOIN Question ON Question.parentRepetitionId=Repetition.id WHERE Question.id is NULL")
    fun getAllRepetionsWhichHasNoQuestions():List<Int>


}