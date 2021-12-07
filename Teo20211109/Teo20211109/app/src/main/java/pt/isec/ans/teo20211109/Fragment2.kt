package pt.isec.ans.teo20211109

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import pt.isec.ans.teo20211109.databinding.Fragment1Binding
import pt.isec.ans.teo20211109.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    lateinit var b : Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = Fragment2Binding.inflate(inflater)
        b.btnBotao1.setOnClickListener {
            val dlg = MyDialog()
            dlg.show(parentFragmentManager,"bb05bb52bb61")
        }
        return b.root
    }

    class MyDialog : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog.Builder(activity)
                .setTitle("Titulo da DlgFrag")
                .setSingleChoiceItems(R.array.items,0,{ d, i ->
                    Toast.makeText(activity,"askjdgaskdfj $i",Toast.LENGTH_SHORT).show()
                })
                .setPositiveButton("Ok", { d,v -> activity?.finish()})
                .create()
        }
    }
}