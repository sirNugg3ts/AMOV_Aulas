package pt.isec.ans.teofragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

const val TAG = "TeoFragments"

class MainActivity : AppCompatActivity(),iFragment1,iFragment2 {
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreateAa: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreateAz: ")
    }

    override fun incValue() {
        i++
    }

    override fun getValue(): Int = i

    override fun onStart() {
        Log.i(TAG, "onStartAa: ")
        super.onStart()
        Log.i(TAG, "onStartAz: ")
    }

    override fun onResume() {
        Log.i(TAG, "onResumeAa: ")
        super.onResume()
        Log.i(TAG, "onResumeAz: ")
    }

    override fun onPause() {
        Log.i(TAG, "onPauseAa: ")
        super.onPause()
        Log.i(TAG, "onPauseAz: ")
    }

    override fun onStop() {
        Log.i(TAG, "onStopAa: ")
        super.onStop()
        Log.i(TAG, "onStopAz: ")
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroyAa: ")
        super.onDestroy()
        Log.i(TAG, "onDestroyAz: ")
    }
}