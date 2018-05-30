package com.kingl.zxs.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice=(Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.send_notice:
                Intent intent=new Intent(this,NotificationActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification =  new NotificationCompat.Builder(this)
                        .setContentTitle("标题内容")
                        .setContentText("对于设备中每一个安装的 App")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)//延迟执行Intent 点击执行的动作
                        //.setAutoCancel(true)  //点击后通知消掉
                        .setSound(Uri.fromFile(new File("/mnt/sdcard/Music/123.mp3")))//通知声音
                        .setVibrate(new long[]{0,1000,1000,1000,2000,3000,1000,4000})//设置震动
                        .setLights(Color.YELLOW,2000,1000)//设置闪灯
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("对于设备中每一个安装的 App，系统都会在内部存储空间的 data/data 目录下以应用包名为名字自动创建与之对应的文件夹。这个文件夹用于 App 中的 WebView 缓存页面信息，SharedPreferences 和 SQLiteDatabase 持久化应用相关数据等。"))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                (BitmapFactory.decodeResource(getResources(),R.drawable.badd11))
                        ))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1,notification);//显示通知
                break;
            default:
                break;
        }
    }
}
