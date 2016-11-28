package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by user on 18/11/2016.
 */
public class alarmreceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.e("Receiver", "OKAY!");

        String my_string = intent.getExtras().getString("extra");

        Log.e("Receiver", my_string);

        Intent service_intent = new Intent(context, AlarmService.class);
        service_intent.putExtra("extra", my_string);
        context.startService(service_intent);

    }
}