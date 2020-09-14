package kozakiewicz.szymon.julianonandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.room.Repository

class RepetitionListViewModel(application: Application): AndroidViewModel(application)
{
    private var repository=Repository(application)

    private var repetitionsList: LiveData<List<Repetition>> =repository.getAllRepetitionsForToday()
    fun getAllRepetitionslist(): LiveData<List<Repetition>>
    {

       return repetitionsList
    }

}