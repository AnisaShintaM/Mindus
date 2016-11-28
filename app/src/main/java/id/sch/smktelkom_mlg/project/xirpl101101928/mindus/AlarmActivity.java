package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Created by user on 18/11/2016.
 */
public class AlarmActivity  extends AppCompatActivity {

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    private PendingIntent pending_intent;
    TimePicker alarmTimePicker;
    DatePicker alarmDatePicker;
    TextView alarmTextView;
    Button buttonon;
    Button buttonoff;
    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmnotification);

        final Intent myIntent = new Intent(this, alarmreceiver.class);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmDatePicker = (DatePicker) findViewById(R.id.alarmDatePicker);
        alarmTextView = (TextView) findViewById(R.id.textView);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar now = Calendar.getInstance();

        alarmDatePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        alarmTimePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        alarmTimePicker.setCurrentMinute(now.get(Calendar.MINUTE));

        buttonon = (Button)findViewById(R.id.buttonon);
        buttonoff = (Button) findViewById(R.id.buttonoff);
        buttonon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(alarmDatePicker.getYear(),
                        alarmDatePicker.getMonth(),
                        alarmDatePicker.getDayOfMonth(),
                        alarmTimePicker.getCurrentHour(),
                        alarmTimePicker.getCurrentMinute(),
                        00);

                if(cal.compareTo(current) <= 0){
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }else{
                    setAlarm(cal);
                }

            }});

        buttonoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);

                alarmManager.cancel(pending_intent);
            }
        });

    }

    private void setAlarm(Calendar targetCal){

        alarmTextView.setText("\n\n***\n"
                + "Alarm is set@ " + targetCal.getTime() + "\n"
                + "***\n");

        Intent intent = new Intent(getBaseContext(), alarmreceiver.class);
        intent.putExtra("extra", "on");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
}