package pt.isec.ans.teo20211109

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.isec.ans.teo20211109.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    lateinit var b : Fragment1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = Fragment1Binding.inflate(inflater)
        b.btnBotao1.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment3)
        }

        return b.root
    }
}