package pt.isec.ans.teo20211109

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.isec.ans.teo20211109.databinding.Fragment1Binding
import pt.isec.ans.teo20211109.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    lateinit var b : Fragment3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = Fragment3Binding.inflate(inflater)
        b.btnBotao1.setOnClickListener {
            val dialog = AlertDialog.Builder(activity)
                .setTitle("Titalu")
                .setMessage("Mensagem importante")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Confirmar") { dialog,v ->
                    findNavController().navigate(R.id.fragment2)
                }
                .setNegativeButton("Cancelar",{ d,v ->
                    activity?.finish()
                })
                .setCancelable(false)
                .create()
            dialog.show()
        }
        return b.root
    }
}