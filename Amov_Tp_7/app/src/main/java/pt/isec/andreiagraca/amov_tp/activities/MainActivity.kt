package pt.isec.andreiagraca.amov_tp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pt.isec.andreiagraca.amov_tp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onEscolheModo(view: android.view.View) {
        val intent = Intent(this, EscolheModo::class.java)
        startActivity(intent)
    }

    fun OnPerfil(view: android.view.View) {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }
}

