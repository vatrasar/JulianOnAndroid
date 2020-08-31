package kozakiewicz.szymon.julianonandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.room.Repository

class RepetitionListViewModel(application: Application): AndroidViewModel(application)
{
    private var dragsRepository=Repository(application)

    private var repetitionsList: LiveData<List<Repetition>> =dragsRepository.getAllRepetitionsForToday()
    fun getAllRepetitionslist(): LiveData<List<Repetition>>
    {
       return repetitionsList
    }

}