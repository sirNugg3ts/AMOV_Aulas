package pt.isec.ans.teotoastsnackbarnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onToastSimples(view: View) {
        Toast.makeText(this, R.string.toast_msg1, Toast.LENGTH_LONG).show()
    }

    fun onToastCustomView(view: View) {
        val viewRoot = layoutInflater.inflate(R.layout.custom_toast, null)
        val tv = viewRoot.findViewById<TextView>(R.id.tvMsg)
        tv.text = getString(R.string.deis_isec)
        val toast = Toast(this).apply {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.CENTER, 0, 0)
            this.view = viewRoot
        }
        toast.show()
    }

    fun onSnackbarSimples(view: View) {
        val snack = Snackbar.make(view, R.string.snack_msg1, Snackbar.LENGTH_LONG)
        snack.show()
    }

    fun onSnackbarAction(view: View) {
        val snack = Snackbar.make(view, R.string.snack_msg1, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.snackoption) {
                Toast.makeText(this@MainActivity, R.string.toast_on_snack1, Toast.LENGTH_LONG)
                    .show()
            }
        }
        snack.show()
    }

    fun onNotification(view: View) {
        val notifMgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel_id = "amov_channel"
        val channel_name = getString(R.string.channel_name)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel_importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel_id, channel_name, channel_importance)
            notifMgr.createNotificationChannel(channel)
        }

        val notif_id = 1234
        val title = getString(R.string.not_title)
        val text = getString(R.string.not_text)
        val small_icon = android.R.drawable.ic_dialog_email
        val priority = NotificationCompat.PRIORITY_DEFAULT

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendindIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(small_icon)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(priority)
            .setContentIntent(pendindIntent)
            .setAutoCancel(true)
            .build()

        notifMgr.notify(notif_id, notification)
    }

    fun onExit(view: View) {
        finish()
    }
}