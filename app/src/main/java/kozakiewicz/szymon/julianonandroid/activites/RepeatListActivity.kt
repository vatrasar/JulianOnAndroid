package kozakiewicz.szymon.julianonandroid.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.adapters.AdapterRepetitionList
import kozakiewicz.szymon.julianonandroid.adapters.ClickListener
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.viewModels.RepetitionListViewModel

class RepeatListActivity : AppCompatActivity() {

    lateinit var repetitionListViewModel:RepetitionListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var repetitionsListAdapter: AdapterRepetitionList
    lateinit var listOfRepetitions: LiveData<List<Repetition>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeat)
        repetitionListViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(RepetitionListViewModel::class.java)
        recyclerView=findViewById(R.id.repetitionsList)
        var manager= LinearLayoutManager(applicationContext)
        manager.orientation= LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=manager
        listOfRepetitions=repetitionListViewModel.getAllRepetitionslist()
        
        listOfRepetitions.observe(this,{
            if(it.isNotEmpty())
            {
                val startRepetitionEvent = object : ClickListener {
                    override fun onClick(view: View?, repetitionId: Int, activity: AppCompatActivity?) {


                        val intent = Intent(activity, DoRepetitionActivity::class.java)
                        intent.putExtra("repetitionId",repetitionId)

                        startActivity(intent)
                    }
                }
                repetitionsListAdapter=AdapterRepetitionList(it,startRepetitionEvent,this)
                recyclerView.adapter=repetitionsListAdapter


            }
        })

    }
}