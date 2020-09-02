package kozakiewicz.szymon.julianonandroid.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kozakiewicz.szymon.julianonandroid.R
import kozakiewicz.szymon.julianonandroid.room.Question
import kozakiewicz.szymon.julianonandroid.room.Repetition
import kozakiewicz.szymon.julianonandroid.room.Repository

class DoRepetitionActivity : AppCompatActivity() {
    lateinit  var repository: Repository
//    lateinit var currentQuestion:Question
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_repetition)
        repository= Repository(application)
        var repetitionId=intent.getIntExtra("repetitionId",0)
        var repetition:Repetition=repository.getRepetition(repetitionId)
        var listOfQuestions:ArrayList<Question> = ArrayList(repository.getAllQuestionsOfRepetition(repetitionId))
        var labCountInfo=findViewById<TextView>(R.id.labCountInfo)
        labCountInfo.setText("${getString(R.string.remaining)} ${listOfQuestions.size}")
        title="${repetition.name} ${repetition.number}"
        if(listOfQuestions.size==0)
            finish()
        var currentQuestion=listOfQuestions[0]
        var labQuestion=findViewById<TextView>(R.id.labQuestion)

        labQuestion.setText(currentQuestion.question)
        
    }
}