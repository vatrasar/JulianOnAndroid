package kozakiewicz.szymon.julianonandroid.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Repository

class AnswerActivity : AppCompatActivity() {
    lateinit  var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        repository= Repository(application)
        var repetitionId=intent.getIntExtra("repetitionId",0)
        var questionId=intent.getIntExtra("questionId",0)
        var questionNumber=intent.getIntExtra("questionsNumber",0)
        var myAnswer=intent.getStringExtra("myAnswer")
        var repetition=repository.getRepetition(repetitionId)
        var question=repository.getQuestion(repetitionId)
        title=repetition.getFullName()
        var labAnswer=findViewById<TextView>(R.id.labAnswer)
        var labMyAnswer=findViewById<TextView>(R.id.labYourAnswer)

        labAnswer.setText(question.answer)
        var labCount=findViewById<TextView>(R.id.labCountInfo)
        labCount.setText("${getString(R.string.remaining)} $questionNumber")

        labMyAnswer.setText(myAnswer)



    }
}