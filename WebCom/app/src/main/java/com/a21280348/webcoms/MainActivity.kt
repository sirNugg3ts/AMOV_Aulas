package com.a21280348.webcoms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.a21280348.webcoms.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainBinding
    val model: MyModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        model.webContent.observe(this) { strContent ->
            try {
                JSONObject(strContent!!).run {
                    procWeatherInfo(this)
                }
            } catch (e: Exception) {
                b.tvContent.text = "Error Parsing JSON:\n $strContent"
            }
        }

        if (!NetUtils.verifyNetworkStateV2(this)) {
            Toast.makeText(this, "No network available", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        model.updateWeatherInfo()


    }

    @Throws(Exception::class)
    private fun procWeatherInfo(jsonObject: JSONObject) {
        val sb = StringBuilder("Result:\n")
        jsonObject.getJSONObject("current").run {
            val temp = get("temp_c")
            sb.append("Current temperature: $temp\n")

            val cond = getJSONObject("condition")
            val icon = cond["icon"]

            Glide
                .with(this@MainActivity)
                .load("https:$icon")
                .into(b.imageView)
        }


        val forecastDayArray = jsonObject.getJSONObject("forecast").getJSONArray("forecastday")

        for (d in 0 until forecastDayArray.length()){
            val forecastday = forecastDayArray.getJSONObject(d)
            val date = forecastday["date"]
            sb.append("\nDay ${d+1} of ${forecastDayArray.length()}: $date\n")
            val forecastHourArray = forecastday.getJSONArray("hour")
            for (h in 0 until forecastHourArray.length()){
                val forecast = forecastHourArray.getJSONObject(h)
                val temp_h = forecast["temp_c"]
                val time = forecast["time"]
                sb.append("$time: $temp_h\n")
            }
        }

        b.tvContent.text = sb.toString()

    }
}