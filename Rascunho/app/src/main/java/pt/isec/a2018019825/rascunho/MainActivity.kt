package pt.isec.a2018019825.rascunho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

val tag = "Rascunho App"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun onFundoSolido(view: android.view.View){
        Log.i(tag,"onFundoSolido event")
        val intent : Intent = Intent(this,FundoSolidoActivity::class.java)
        startActivity(intent)
    }
    fun onImagem(view: android.view.View){
Toast.makeText(this,"Disponível brevemente",Toast.LENGTH_SHORT).show()
    }
    fun onCamera(view: android.view.View){
        Toast.makeText(this,"Disponível brevemente",Toast.LENGTH_SHORT).show()
    }
    fun onHistorico(view: android.view.View){
        Toast.makeText(this,"Disponível brevemente",Toast.LENGTH_SHORT).show()
    }
}