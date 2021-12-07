package pt.isec.ans.teofragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

interface iFragment1 {
    fun incValue()
}

class Fragment1 : Fragment() {

    var actBase : iFragment1? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach1: ")
        actBase = context as? iFragment1
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate1: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView1: ")
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        view.findViewById<Button>(R.id.btnPress).setOnClickListener {
            actBase?.incValue()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "onActivityCreated1: ")
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView1: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy1: ")
    }
    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach1: ")
    }
}