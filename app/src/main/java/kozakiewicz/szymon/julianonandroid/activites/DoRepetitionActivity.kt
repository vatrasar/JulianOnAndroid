package kozakiewicz.szymon.julianonandroid.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Question
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.room.Repository
import kozakiewicz.szymon.julianonandroid.room.Status


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
        repetitionId=intent.getIntExtra("repetitionId", 0)
        var repetition:Repetition=repository.getRepetition(repetitionId)
        listOfQuestions = ArrayList(repository.getAllQuestionsOfRepetition(repetitionId))
        var labCountInfo=findViewById<TextView>(R.id.labCountInfo)
        labCountInfo.setText("${getString(R.string.remaining)} ${listOfQuestions.size}")
        title="${repetition.name} ${repetition.number}"
        if(listOfQuestions.size==0)
        {
            finish()
            return
        }


        currentQuestion=listOfQuestions[0]
        setQuestionView()

    }

    private fun setQuestionView() {

        var labQuestion = findViewById<TextView>(R.id.labQuestion)
        if(currentQuestion.status==Status.KNOW_ONE_WAY)
            labQuestion.setText(currentQuestion.answer)
        else
            labQuestion.setText(currentQuestion.question)

        var labCountInfo=findViewById<TextView>(R.id.labCountInfo)
        labCountInfo.setText("${getString(R.string.remaining)} ${listOfQuestions.size}")

    }

    fun onCheckAnswer(view: View) {
        val intent: Intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra("questionId", currentQuestion.id)
        var test=repetitionId
        intent.putExtra("repetitionId", repetitionId)
        intent.putExtra("questionsNumber", listOfQuestions.size)
        var txtMyAnswer=findViewById<EditText>(R.id.txtMyAnswer)
        intent.putExtra("myAnswer", txtMyAnswer.text.toString())
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var status=data?.getIntExtra("status",-1)
        when(status){
            -1-> throw Exception("know message return error")
            0->{
                var isRepetitionReverse=repository.getRepetition(currentQuestion.parentRepetitionId).isReverse
                currentQuestion.updateStatus(Status.DONT_KNOW,isRepetitionReverse)
                repository.updateQuestion(currentQuestion)
                listOfQuestions.removeAt(0)

            }
            1->{
                var isRepetitionReverse=repository.getRepetition(currentQuestion.parentRepetitionId).isReverse
                currentQuestion.updateStatus(Status.KNOW,isRepetitionReverse)
                repository.updateQuestion(currentQuestion)
                listOfQuestions.removeAt(0)
                if(currentQuestion.status==Status.KNOW_ONE_WAY)
                    listOfQuestions.add(currentQuestion)
            }
        }
        if(listOfQuestions.isEmpty()) {
            repository.finalizeRepetition(repetitionId)
            finish()
            return

        }
        currentQuestion=listOfQuestions[0]

        setQuestionView()


    }

    fun onKnow(view: View) {}


}