package pt.isec.andreiagraca.amov_tp.activities

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import pt.isec.andreiagraca.amov_tp.R
import pt.isec.andreiagraca.amov_tp.databinding.ActivityEscolhemodoBinding
import android.view.MotionEvent

import android.view.View.OnTouchListener

import android.R.attr.button
import android.R.attr.button







class EscolheModo :AppCompatActivity() , View.OnTouchListener{
    lateinit var b: ActivityEscolhemodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityEscolhemodoBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.modo1.setOnTouchListener(this)
    }


    fun onModo1(view: android.view.View) {
        //b.textomodo.text="Jogado por dois jogadores num único dispositivo num tabuleiro 8 x 8 com as\n" +
        //      "regras descritas, incluindo as peças especiais;"

        val intent = Intent(this, Modo1::class.java)
        startActivity(intent)
        //val snackbar = Snackbar.make(view, R.string.msg_brevemente,Snackbar.LENGTH_LONG)
        //snackbar.show()
    }

    fun onModo2(view: android.view.View) {
       // Toast.makeText(this,R.string.msg_brevemente,Toast.LENGTH_LONG).show()


        //val snackbar = Snackbar.make(view, R.string.msg_brevemente,Snackbar.LENGTH_LONG)
        //snackbar.show()
    }

    fun onModo3(view: android.view.View) {
        Toast.makeText(this,R.string.msg_brevemente,Toast.LENGTH_LONG).show()

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        b.modo1.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_HOVER_ENTER) {
                b.textomodo.text="Jogado por dois jogadores num único dispositivo num tabuleiro 8 x 8 com as\n" +
                     "regras descritas, incluindo as peças especiais;"
            }
            if (event.action == MotionEvent.ACTION_HOVER_EXIT) {
                b.modo1.invalidate()
            }
            false
        })
        return false
    }


}


