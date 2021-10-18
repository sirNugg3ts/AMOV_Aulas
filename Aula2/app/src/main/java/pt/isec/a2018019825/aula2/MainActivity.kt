package pt.isec.a2018019825.aula2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.isec.a2018019825.aula2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var counter : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        binding.TextView1.text = "Arroz e batatas"


        // button handlers
        binding.Butao1.setOnClickListener {
            counter++;
            binding.TextView1.text = counter.toString() }
    }

    fun changeText(text: String,viewID: Int){
        val tv: TextView = findViewById(viewID)
        tv.text  = text

    }

    override fun onPause() {
        super.onPause()
        changeText("I was on pause",R.id.TextView1)
    }


    }
