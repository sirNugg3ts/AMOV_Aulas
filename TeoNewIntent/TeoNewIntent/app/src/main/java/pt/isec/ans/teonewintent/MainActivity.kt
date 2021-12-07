package pt.isec.ans.teonewintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

const val TAG = "TeoNewIntent"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "onCreate1: ")

        findViewById<Button>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            val novo_intent = Intent(this,MainActivity::class.java)
            novo_intent.putExtra("valor",1234)
            startActivity(novo_intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i(TAG, "onNewIntent1: ")
        //val inteiro = intent?.getIntExtra("valor",0)
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart1: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart1: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume1: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause1: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop1: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy1: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState1: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState1: ")
    }
}