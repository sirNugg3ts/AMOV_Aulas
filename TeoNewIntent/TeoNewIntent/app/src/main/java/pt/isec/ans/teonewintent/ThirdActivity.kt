package pt.isec.ans.teonewintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        Log.i(TAG, "onCreate3: ")

        findViewById<Button>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i(TAG, "onNewIntent3: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart3: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart3: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume3: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause3: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop3: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy3: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState3: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState3: ")
    }
}