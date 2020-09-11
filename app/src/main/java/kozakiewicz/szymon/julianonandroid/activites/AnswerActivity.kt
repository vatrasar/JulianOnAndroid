package kozakiewicz.szymon.julianonandroid.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Question
import kozakiewicz.szymon.julianonandroid.room.Repository
import kozakiewicz.szymon.julianonandroid.room.Status


class AnswerActivity : AppCompatActivity() {
    lateinit  var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        repository= Repository(application)
        var repetitionId=intent.getIntExtra("repetitionId", 0)
        var questionId=intent.getIntExtra("questionId", 0)
        var questionNumber=intent.getIntExtra("questionsNumber", 0)
        var myAnswer=intent.getStringExtra("myAnswer")
        var repetition=repository.getRepetition(repetitionId)
        var question=repository.getQuestion(questionId)
        title=repetition.getFullName()
        setAnswerPage(question, questionNumber, myAnswer)


    }

    private fun setAnswerPage(
        question: Question,
        questionNumber: Int,
        myAnswer: String?
    ) {
        var labAnswer = findViewById<TextView>(R.id.labAnswer)
        var labMyAnswer = findViewById<TextView>(R.id.labYourAnswer)

        labAnswer.setText(question.answer)
        if(question.status== Status.KNOW_ONE_WAY)
            labAnswer.setText(question.question)
        else
            labAnswer.setText(question.answer)
        var labCount = findViewById<TextView>(R.id.labCountInfo)
        labCount.setText("${getString(R.string.remaining)} $questionNumber")

        labMyAnswer.setText(myAnswer)
    }

    fun onKnow(view: View) {

        val resultIntent = Intent()

        resultIntent.putExtra("status",1 )
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    fun onDontKnow(view: View) {
        val resultIntent = Intent()

        resultIntent.putExtra("status",0 )
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}