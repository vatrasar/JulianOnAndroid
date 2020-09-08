package kozakiewicz.szymon.julianonandroid.activites

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Question
import kozakiewicz.szymon.julianonandroid.room.Repository
import kozakiewicz.szymon.julianonandroid.room.Status

class AddNewQuestionActivity : AppCompatActivity() {
    lateinit var repository: Repository
    var repetitionId:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_question)
        repository= Repository(application)
        repetitionId=intent.getIntExtra("repetitionId",-1)


    }

    fun onAddQuestion(view: View) {
        var txtQuestion=findViewById<EditText>(R.id.txtQuestion)
        var txtAnswer=findViewById<EditText>(R.id.txtAnswer)
        val intent = intent

        var question:Question= Question(
            txtQuestion.text.toString(),
            txtAnswer.text.toString(),
            repetitionId,Status.UNCHECKED
        )
        repository.insertNewQuestion(question)
        txtAnswer.text.clear()
        txtQuestion.text.clear()


    }
}