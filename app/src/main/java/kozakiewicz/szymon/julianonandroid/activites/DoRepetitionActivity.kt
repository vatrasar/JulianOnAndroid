package kozakiewicz.szymon.julianonandroid.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Question
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.room.Repository

class DoRepetitionActivity : AppCompatActivity() {
    lateinit  var repository: Repository
//    lateinit var currentQuestion:Question
    lateinit var currentQuestion:Question
    lateinit var listOfQuestions:ArrayList<Question>
    var repetitionId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_repetition)
        repository= Repository(application)
        repetitionId=intent.getIntExtra("repetitionId",0)
        var repetition:Repetition=repository.getRepetition(repetitionId)
        listOfQuestions = ArrayList(repository.getAllQuestionsOfRepetition(repetitionId))
        var labCountInfo=findViewById<TextView>(R.id.labCountInfo)
        labCountInfo.setText("${getString(R.string.remaining)} ${listOfQuestions.size}")
        title="${repetition.name} ${repetition.number}"
        if(listOfQuestions.size==0)
            finish()
        currentQuestion=listOfQuestions[0]
        var labQuestion=findViewById<TextView>(R.id.labQuestion)

        labQuestion.setText(currentQuestion.question)
        
    }

    fun onCheckAnswer(view: View) {
        val intent: Intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra("questionId",currentQuestion.id)
        var test=repetitionId
        intent.putExtra("repetitionId",repetitionId)
        intent.putExtra("questionsNumber",listOfQuestions.size)
        var txtMyAnswer=findViewById<EditText>(R.id.txtMyAnswer)
        intent.putExtra("myAnswer",txtMyAnswer.text.toString())
        startActivity(intent)
    }
}