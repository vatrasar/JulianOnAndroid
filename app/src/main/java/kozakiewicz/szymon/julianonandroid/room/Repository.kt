package kozakiewicz.szymon.julianonandroid.room

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class Repository(aplication: Application){
    private var dao:JulianDao

    init {
        val dataBase:RepetitionDataBase?= RepetitionDataBase.getInstance(aplication.applicationContext)
        dao=dataBase!!.DragDao();
    }

    fun getMaxNumberFromDatabase(repetitionName:String):Int {
        var job=CoroutineScope(Dispatchers.IO).async {
            dao.getMaxNumberFromDatabase(repetitionName)

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }

    fun insertNewRepetion(newRepetition: Repetition):Long
    {
        var job=CoroutineScope(Dispatchers.IO).async {
            dao.insertRepetition(newRepetition)

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }

    fun getAllRepetitionsForToday(): LiveData<List<Repetition>> {
        var job=CoroutineScope(Dispatchers.IO).async {
            dao.getAllRepetitionsForToday()

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }

    fun insertNewQuestion(question: Question)= CoroutineScope(Dispatchers.IO).launch {
        dao.insertQuestion(question)
    }

    fun getAllQuestionsOfRepetition(repetitionId:Int): List<Question> {
        var job=CoroutineScope(Dispatchers.IO).async {
            dao.getAllQuestionsOfRepetition(repetitionId)

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }

    fun getRepetition(repetitionId: Int): Repetition {

        var job=CoroutineScope(Dispatchers.IO).async {
            dao.getRepetition(repetitionId)

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }

    fun getQuestion(questionId: Int): Question {

        var job=CoroutineScope(Dispatchers.IO).async {
            dao.getQuestion(questionId)


        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }
}