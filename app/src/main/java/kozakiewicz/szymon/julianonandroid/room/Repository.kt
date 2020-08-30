package kozakiewicz.szymon.julianonandroid.room

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*

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

    fun insertNewRepetion(newRepetition: Repetition)= CoroutineScope(Dispatchers.IO).launch {
        dao.insertRepetition(newRepetition)
    }

    fun getAllRepetitionsForToday(): LiveData<List<Repetition>> {
        var job=CoroutineScope(Dispatchers.IO).async {
            dao.getAllRepetitionsForToday()

        }

        return runBlocking {
            return@runBlocking job.await()
        }
    }
}