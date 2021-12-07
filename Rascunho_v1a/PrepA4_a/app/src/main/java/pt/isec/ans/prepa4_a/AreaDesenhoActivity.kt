package pt.isec.ans.prepa4_a

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class AreaDesenhoActivity : AppCompatActivity() {
    lateinit var titulo : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_desenho)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        titulo = intent.getStringExtra("titulo") ?: getString(R.string.str_no_name)
        supportActionBar?.title = getString(R.string.rascunho) + ": " + titulo

        val r = intent.getIntExtra("red",255);
        val g = intent.getIntExtra("green",255);
        val b = intent.getIntExtra("blue",255);

        findViewById<FrameLayout>(R.id.frAreaDesenho).setBackgroundColor(Color.rgb(r,g,b))
    }
}