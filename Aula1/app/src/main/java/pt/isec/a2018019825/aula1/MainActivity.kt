package pt.isec.a2018019825.aula1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val TAG: String = ".MainActivity"

class MainActivity : AppCompatActivity() {

    val app: Application by lazy { application as Application }

    var x: Int = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"App started")
        var minhaApp: Application = Application()
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"PAUSA")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"RESUME")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"RESTART")

    }


}