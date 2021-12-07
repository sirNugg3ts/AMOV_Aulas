package pt.isec.ans.prepa4_a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onFundoSolido(view: android.view.View) {
        val intent = Intent(this,FundoSolidoActivity::class.java)
        startActivity(intent)
    }

    fun onImagem(view: android.view.View) {
        Toast.makeText(this,R.string.msg_brevemente,Toast.LENGTH_LONG).show()
    }
    fun onFotografia(view: android.view.View) {
        Toast.makeText(this,R.string.msg_brevemente,Toast.LENGTH_LONG).show()
    }

    fun onHistorico(view: android.view.View) {
        val snackbar = Snackbar.make(view,R.string.msg_brevemente,Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}