package pt.isec.ans.prepa3_calc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tvDisplay : TextView

    var strDisplay = "0.0"
        set(value) {
            field = value
            tvDisplay.text = value
        }
    var newNumber = true
    val currentNumber : Double
        get() = strDisplay.toDoubleOrNull() ?: 0.0
    var op = 0
        set(value) {
            if (field != 0)
                findViewById<Button>(field).setTextColor(Color.BLACK)
            if (value != 0)
                findViewById<Button>(value).setTextColor(Color.RED)
            field = value
        }
    var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)
    }

    fun onDigit(view: View) {
        val btn = view as Button
        if (newNumber)
            strDisplay = btn.text.toString()
        else
            strDisplay += btn.text
        newNumber = false
    }

    fun onPoint(view: View) {
        if (newNumber)
            strDisplay = "0."
        else {
            if (strDisplay.contains('.'))
                return
            strDisplay += "."
        }
        newNumber = false
    }
    fun onAC(view: View) {
        strDisplay = "0.0"
        newNumber = true
    }
    fun onPM(view: View) {
        if (strDisplay[0] == '-')
            strDisplay = strDisplay.substring(1)
        else
            strDisplay = "-$strDisplay"
    }
    fun onPerc(view: View) {
        strDisplay = "" + currentNumber / 100.0
        newNumber=true
    }
    fun onOper(view: View) {
        if (op != 0 && !newNumber)
            onCalc(view)
        firstNumber = currentNumber
        newNumber = true
        op = view.id
    }
    fun onCalc(view: View) {
        var calc = 0.0

        when (op) {
            R.id.btnAdd -> calc = firstNumber + currentNumber
            R.id.btnSub -> calc = firstNumber - currentNumber
            R.id.btnMul -> calc = firstNumber * currentNumber
            R.id.btnDiv -> calc = if (currentNumber != 0.0) firstNumber / currentNumber else 0.0
            else -> return
        }
        newNumber = true
        op = 0
        firstNumber = calc
        strDisplay = "$calc"
    }
}