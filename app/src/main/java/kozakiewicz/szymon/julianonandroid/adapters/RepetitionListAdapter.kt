package kozakiewicz.szymon.julianonandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.activites.RepeatListActivity
import kozakiewicz.szymon.julianonandroid.room.Repetition

class AdapterRepetitionList(
    private val listOfRepetitions: List<Repetition>,
    var startRepetitionEvent: ClickListener,
    val repeatListActivity: RepeatListActivity
) :
    RecyclerView.Adapter<RepetitionViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepetitionViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val repetitionRow: View =layoutInflater.inflate(R.layout.repetition_row, parent, false)
        return RepetitionViewHolder(repetitionRow,startRepetitionEvent,repeatListActivity,listOfRepetitions)
    }

    override fun getItemCount(): Int {
        val test:Int=listOfRepetitions.size
        return listOfRepetitions.size
    }

    override fun onBindViewHolder(holder: RepetitionViewHolder, position: Int) {

        var labRepeteName:TextView=holder.view.findViewById<TextView>(R.id.labRemainingQuestionCounter)

        labRepeteName.text=listOfRepetitions[position].name
    }

}

class RepetitionViewHolder(
    val view: View,
    val startRepetitionEvent: ClickListener,
    activity: AppCompatActivity?,
    val listOfRepetitions: List<Repetition>
): RecyclerView.ViewHolder(view), View.OnClickListener
{

    init {
        var btnStartRepetition=view.findViewById<Button>(R.id.btnDoRepetition)
        btnStartRepetition.setOnClickListener{startRepetitionEvent.onClick(view, listOfRepetitions[adapterPosition].id,activity)}

    }
    override fun onClick(p0: View?) {

    }

}

interface ClickListener {
    fun onClick(view: View?, position: Int, activity: AppCompatActivity? = null)
}