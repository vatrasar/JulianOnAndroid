package kozakiewicz.szymon.julianonandroid.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kozakiewicz.szymon.julianonandroid.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCreateRepetition(view: View) {


        val intent:Intent=Intent(this, AddRepetitionActivity::class.java)
        startActivity(intent)
    }
}