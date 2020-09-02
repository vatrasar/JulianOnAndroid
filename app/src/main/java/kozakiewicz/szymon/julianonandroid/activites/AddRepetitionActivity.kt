package kozakiewicz.szymon.julianonandroid.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.room.Transaction
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

    @Transaction
    fun onAddRepetition(view: View) {

        var txtName=findViewById<EditText>(R.id.labRemainingQuestionCounter)
        var txtTopicName=findViewById<EditText>(R.id.txtRepetitionTopic)
        if(txtName.text.isBlank())
            return
        var switchIsReverse=findViewById<Switch>(R.id.switchIsReverse)
        var newRepetition:Repetition=Repetition(txtName.text.toString(),txtTopicName.text.toString(),repository.getMaxNumberFromDatabase(txtName.text.toString()),switchIsReverse.isChecked)
        var id=repository.insertNewRepetion(newRepetition)
        val myIntent: Intent = Intent(this, AddNewQuestionActivity::class.java)

        myIntent.putExtra("repetitionId",id)
        startActivity(myIntent)

    }
}