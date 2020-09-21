package com.example.materialtutorial.navigation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.Fragment
import com.example.materialtutorial.MainActivity
import com.example.materialtutorial.R
import kotlinx.android.synthetic.main.fragment_dialog.view.*

class DialogFragment : Fragment() {
    var builder : NotificationCompat.Builder? = null

    companion object{
        const val channelId : String = "one-channel"
        const val channelName : String = "My Channel One"
        const val channelDescription : String = "My Channel One Description"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_dialog, container, false)

        view.btn_notification.setOnClickListener {
            notification()
        }

        return view
    }

    private fun notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val mPendingIntent : PendingIntent = PendingIntent.getActivity(context, 0, Intent(activity, ListFragment::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

            channel.run {
                description = channelDescription
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300)
            }

            notificationManager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(activity!!, channelId).run {
                setSmallIcon(R.drawable.ic_baseline_notifications_24)
                setWhen(System.currentTimeMillis())
                setContentTitle("알림 타이틀 메세지")
                setContentText("알림 메세지 내용")
                setContentIntent(mPendingIntent)
                setDefaults(Notification.DEFAULT_SOUND and Notification.DEFAULT_VIBRATE)
                setAutoCancel(true)
            }

            val notification : Notification = builder!!.build()
            notificationManager.notify(1, notification)

        } else {
            builder = NotificationCompat.Builder(activity!!, null.toString())
        }

    }
}