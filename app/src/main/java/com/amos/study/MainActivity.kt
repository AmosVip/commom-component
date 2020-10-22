package com.amos.study


import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_grid.layoutManager = GridLayoutManager(this, 3)
        rv_grid.addItemDecoration(CommonItemDecoration())
        rv_grid.adapter = GridAdapter(this)
        testClass()
        findViewById<Button>(R.id.btn_jump_to_web_activity).setOnClickListener {
            NestedScrollWebViewActivity.launch(this)
        }

        findViewById<Button>(R.id.btn_nested_scroll).setOnClickListener {
            NestedScrollActivity.launch(this)
        }
        formatTime()
    }

    private fun testClass() {
        val user = User(1, "Kotlin")

    }

    private fun formatTime() {
        val currentTime: Long = System.currentTimeMillis()
        val tempTime: Long = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse("2020-09-22 18:25:35")
            .time

        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = currentTime

        val tempCalendar = Calendar.getInstance()
        tempCalendar.timeInMillis = tempTime

        val offsetMinute = currentCalendar.get(Calendar.MINUTE) - tempCalendar.get(Calendar.MINUTE)
        Log.e("formatTime", "offsetMinute = $offsetMinute")
    }


    private val musicChannelId: String = "music_channel_id"
    private val musicChannelName: String = "music_channel_name"
    private val notificationImportance: Int = NotificationManager.IMPORTANCE_HIGH
    private val notificationId: Int = 1024

    //测试发送通知
    fun onSendNotification(view: View) {
        //onSendNotificationMethod()
        //onSendFullScreenNotification()
        onSendMediaPlayNotification()
    }

    //音频播放通知
    private fun onSendMediaPlayNotification() {

        onCreateNotificationChannel()
        val mediaSession = MediaSessionCompat(this, "ssss")
        val notification = NotificationCompat.Builder(this, musicChannelId)
            .setContentTitle("媒体播放通知")
            .setContentText("媒体播放内容")
            .setSubText("媒体播放subtext")
            .setSmallIcon(R.mipmap.btn_nice)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.copy_wx_top
                )
            )
            .setContentIntent(onCreateNotificationIntent())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .addAction(R.mipmap.ic_add_self, "", onCreateMediaBtnIntent())
            .addAction(R.mipmap.main_tab_circle_selected, "", onCreateMediaBtnIntent())
            .addAction(R.mipmap.ic_already_add, "", onCreateMediaBtnIntent())
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0)
            )
            .build()
        NotificationManagerCompat.from(this).notify(notificationId, notification)
    }

    private fun onCreateMediaBtnIntent(): PendingIntent {
        val snoozeIntent = Intent(this, MyBroadcastReceiver::class.java)
        snoozeIntent.action = "ACTION_SNOOZE"
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0)
        return PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)
    }

    private fun onSendNotificationMethod() {
        onCreateNotificationChannel()
        //构造函数要求您提供渠道 ID。这是兼容 Android 8.0（API 级别 26）及更高版本所必需的，但会被较旧版本忽略。
        var notification = NotificationCompat.Builder(this, musicChannelId)
            .setSmallIcon(R.mipmap.ic_launcher) //小图标：必须提供
            .setContentTitle("通知title") //标题
            .setContentText("通知内容") //文本内容
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            //默认情况下，通知的文本内容会被截断以放在一行。如果您想要更长的通知，可以使用 setStyle() 添加样式模板来启用可展开的通知
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle("测试bigContentTitle")
                    .setSummaryText("测试SummaryText")
                    .bigText("bigTextbigTextbigTextbigTextbigText")
            )
            .setContentIntent(onCreateNotificationIntent())
            //.setAutoCancel(false) //它会在用户点按通知后自动移除通知
            //.addAction(R.mipmap.ic_launcher,"测试按钮",) //添加操作按钮，
            .build()

        NotificationManagerCompat.from(this).notify(notificationId, notification)
    }

    private fun onSendFullScreenNotification() {
        val fullScreenIntent = Intent(this, MainActivity::class.java)
        val fullScreenPendingIntent = PendingIntent.getActivity(
            this, 0,
            fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification =
            NotificationCompat.Builder(this, musicChannelId)
                .setSmallIcon(com.amos.study.R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .build()
        NotificationManagerCompat.from(this).notify(notificationId, notification)

    }


    //android 8.0 及以上需要创建渠道
    private fun onCreateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(musicChannelId, musicChannelName, notificationImportance)
            notificationChannel.description = "渠道描述"

            // val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(notificationChannel)
        }
    }

    //创建通知点击跳转的intent
    private fun onCreateNotificationIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    //创建通知上的按钮点击响应的intent
    private fun onCreateNotificationActionIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(this, 0, intent, 0)
    }

    private val resultKey = "key_text_reply"

    //如需创建支持直接回复的通知操作，请按照如下所述操作：
    //
    //创建一个可添加到通知操作的 RemoteInput.Builder 实例。此类的构造函数接受系统用作文本输入键的字符串。之后，手持式设备应用使用该键检索输入的文本
    private fun onCreateRemoteInput(): RemoteInput {
        return RemoteInput.Builder(resultKey)
            .setLabel("input label")
            .build()
    }

    class GridAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var context: Context = context
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 11
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    class User {
        private val id: Int
        private val name: String

        init {
            // 初始化代码块，先于下面的构造器执行
            println("init init ")
        }

        constructor(id: Int, name: String) {
            this.id = id
            this.name = name
            println("constructor  constructor ")
        }
    }


    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            TODO("Not yet implemented")

        }

    }
}