package pt.isec.a2018019825.rascunho

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SeekBar
import pt.isec.a2018019825.rascunho.databinding.ActivityFundoSolidoBinding


class FundoSolidoActivity : AppCompatActivity() {

    lateinit var binding : ActivityFundoSolidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFundoSolidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.seekRed.apply { max = 255
        progress = 255
        setOnSeekBarChangeListener(progressSeekBar)}

        binding.seekBlue.apply { max = 255
            progress = 255
            setOnSeekBarChangeListener(progressSeekBar)}

        binding.seekGreen.apply { max = 255
            progress = 255
            setOnSeekBarChangeListener(progressSeekBar)}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_criar,menu)
        return true
    }

    val progressSeekBar = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            updatePreview()
        }

        private fun updatePreview() {
            val cor = Color.rgb(binding.seekRed.progress,binding.seekGreen.progress,binding.seekBlue.progress)
            binding.frPreview.setBackgroundColor(cor)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            updatePreview()
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            updatePreview()
        }

    }


}