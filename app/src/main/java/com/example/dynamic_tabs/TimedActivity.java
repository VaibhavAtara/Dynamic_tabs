package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimedActivity extends AppCompatActivity {
    TimePicker timePicker;
    Button setAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed);
        timePicker = (TimePicker)findViewById(R.id.timepicker);
            setAlarm = (Button)findViewById(R.id.setAlarm);

            setAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    if(Build.VERSION.SDK_INT >= 23) {
                        calendar.set(calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH),
                                timePicker.getHour(),
                                timePicker.getMinute(),
                                0);
                    }
                    else{
                        calendar.set(calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH),
                                timePicker.getCurrentHour(),
                                timePicker.getCurrentMinute(),
                                0);
                    }

                    SetAlarm(calendar.getTimeInMillis());

                }
            });
    }

    private void SetAlarm(long timeInMillis) {
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this,PublishMessage.class);
            //intent.putExtra("topic","random");
            //intent.putExtra("message","ON");

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);

            Toast.makeText(getApplicationContext(),"Alarm is Set",Toast.LENGTH_SHORT).show();
        }
}



