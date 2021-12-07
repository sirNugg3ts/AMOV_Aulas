package pt.isec.ans.teofragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

interface iFragment2 {
    fun getValue() : Int
}

class Fragment2 : Fragment() {

    var actBase : iFragment2? = null
    lateinit var tvValue : TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach2: ")
        actBase = context as? iFragment2
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate2: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView2: ")
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        tvValue = view.findViewById<TextView>(R.id.tvValue)
        view.findViewById<ImageButton>(R.id.ibRefresh).apply {
            setOnClickListener {
                refresh()
            }
        }
        return view
    }

    fun refresh() {
        if (actBase == null)
            return
        val value = actBase!!.getValue()
        tvValue.text = "Value: $value"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "onActivityCreated2: ")
        refresh()
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView2: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy2: ")
    }
    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach2: ")
    }
}
