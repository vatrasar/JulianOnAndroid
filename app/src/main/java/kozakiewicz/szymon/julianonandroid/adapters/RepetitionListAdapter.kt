package kozakiewicz.szymon.julianonandroid.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Repetition
import java.util.*

class AdapterRepetitionList(private val listOfRepetitions: List<Repetition>) :
    RecyclerView.Adapter<RepetitionViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepetitionViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val repetitionRow: View =layoutInflater.inflate(R.layout.repetition_row, parent, false)
        return RepetitionViewHolder(repetitionRow)
    }

    override fun getItemCount(): Int {
        val test:Int=listOfRepetitions.size
        return listOfRepetitions.size
    }

    override fun onBindViewHolder(holder: RepetitionViewHolder, position: Int) {

        var labRepeteName:TextView=holder.view.findViewById<TextView>(R.id.txtRepetitionName)
        labRepeteName.text=listOfRepetitions[position].name
    }

}

class RepetitionViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener
{

    init {


    }
    override fun onClick(p0: View?) {

    }

}
