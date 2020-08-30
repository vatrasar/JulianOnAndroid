package kozakiewicz.szymon.julianonandroid.activites

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.room.Repository

class AddRepetitionActivity : AppCompatActivity() {

    lateinit  var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_repetition)
        repository= Repository(application)
    }


    fun onAddRepetition(view: View) {

        var txtName=findViewById<EditText>(R.id.txtRepetitionName)
        var txtTopicName=findViewById<EditText>(R.id.txtRepetitionTopic)
        if(txtName.text.isBlank())
            return
        var switchIsReverse=findViewById<Switch>(R.id.switchIsReverse)
        var newRepetition:Repetition=Repetition(txtName.text.toString(),txtTopicName.text.toString(),repository.getMaxNumberFromDatabase(txtName.text.toString()),switchIsReverse.isChecked)
        repository.insertNewRepetion(newRepetition)
        finish()
    }
}