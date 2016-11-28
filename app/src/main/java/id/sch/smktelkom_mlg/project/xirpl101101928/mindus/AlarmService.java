package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 18/11/2016.
 */
public class AlarmService extends Service {

    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("LocalService", "Received start id" + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("alarm", state);

        assert state != null;
        switch (state) {
            case "on":
                startId = 1;
                break;
            case "off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (startId == 1) {
            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(Notification.CATEGORY_ALARM)
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("Hello MindUser") // title for notification
                    .setContentText("You Have Some Work to Do") // message for notification
                    .setAutoCancel(true); // clear notification after click

            PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, AlarmActivity.class), 0);
            mBuilder.setContentIntent(pi);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());


            media_song = MediaPlayer.create(this, R.raw.alarm);
            media_song.setLooping(true);
            media_song.start();

        } else if (startId == 0) {
            media_song.stop();
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT);
        media_song.stop();
    }


}