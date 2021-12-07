package pt.isec.ans.teonewintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.i(TAG, "onCreate2: ")

        findViewById<Button>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            startActivity(Intent(this,ThirdActivity::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i(TAG, "onNewIntent2: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart2: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart2: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume2: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause2: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop2: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy2: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState2: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState2: ")
    }
}