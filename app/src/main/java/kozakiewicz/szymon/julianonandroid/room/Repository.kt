package kozakiewicz.szymon.julianonandroid.room

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*
import java.util.stream.Collectors
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

    fun insertNewRepetion(newRepetition: Repetition):Int
    {
        var job=CoroutineScope(Dispatchers.IO).async {
            dao.insertRepetition(newRepetition)

        }

        return runBlocking {
            return@runBlocking job.await().toInt()
        }
    }

    fun getAllRepetitionsForToday(): LiveData<List<Repetition>> {
        var job=CoroutineScope(Dispatchers.IO).async {
            var repetitionsWithNoQuestionsList=getAllRepetionsWhichHasNoQuestions()
            for(repetitionId in repetitionsWithNoQuestionsList)
            {

                dao.delete(getRepetition(repetitionId))
            }
            dao.getAllRepetitionsForToday()

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }

    private fun getAllRepetionsWhichHasNoQuestions(): List<Int> {
        var job=CoroutineScope(Dispatchers.IO).async {

            dao.getAllRepetionsWhichHasNoQuestions()

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

    fun updateQuestion(questionToUpdate: Question) = CoroutineScope(Dispatchers.IO).launch {
        dao.updateQuestion(questionToUpdate)
    }

    fun finalizeRepetition(repetitionId: Int) {
        var listOfQuestions=getAllQuestionsOfRepetition(repetitionId)
        var listOfQuestionForNewRepetition:List<Question> = listOfQuestions.filter({a->a.status==Status.DONT_KNOW})
        listOfQuestions.forEach({question -> question.status=Status.UNCHECKED })

        var repetition=getRepetition(repetitionId)


        repetition.repetitionFinalize()
        updateRepetition(repetition)
        var newRepetition:Repetition=Repetition(repetition.name,repetition.topic,getMaxNumberFromDatabase(repetition.name),repetition.isReverse)

        var newRepetitionId =insertNewRepetion(newRepetition)
        for(question in listOfQuestionForNewRepetition)
        {
            question.parentRepetitionId=newRepetitionId

            updateQuestion(question)

        }

    }

    private fun updateRepetition(repetition: Repetition) = CoroutineScope(Dispatchers.IO).launch {
        dao.update(repetition)
    }
}