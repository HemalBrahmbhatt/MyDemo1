package com.example.mydemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        createNotificationChannel()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btn = findViewById<Button>(R.id.btn_not1)
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val hereIntent=Intent(this,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val herePendingIntent = PendingIntent.getActivity(this,0,hereIntent,0)

        val notibuilder = NotificationCompat.Builder(this, "2")
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("My notification")
            .setContentText("Here is the notification...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.more_txt)))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .addAction(R.drawable.ic_home,"Home",herePendingIntent)


        btn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.notification)
            builder.setMessage(R.string.al_msg)
            builder.setCancelable(true)
            builder.setIcon(R.drawable.ic_notifications)
            builder.setPositiveButton("Yes"){ _, _ ->
                Toast.makeText(applicationContext,"Check Notification",Toast.LENGTH_LONG).show()
                with(NotificationManagerCompat.from(this)) {
                    notify(2, notibuilder.build())
                }

            }
            builder.setNegativeButton("No"){ _, _ ->
                Toast.makeText(applicationContext,"Ok Fine...",Toast.LENGTH_LONG).show()
            }

            val alertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.show()

        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification)
            val descriptionText = getString(R.string.al_msg)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("2", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}