package com.zerospeed8.exitstrategy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    private PendingIntent pendingIntent;
    TimePicker EndTime;
    Button SetTime;
    Button CancelAll;
    NumberPicker Snooze;
    TextView time;
    public int hour;
    public int mins;
    public int wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EndTime = (TimePicker) findViewById(R.id.timePicker);
        SetTime = (Button) findViewById(R.id.setButton);
        CancelAll = (Button) findViewById(R.id.cancelButton);
        Snooze = (NumberPicker) findViewById(R.id.snoozePicker);
        time = (TextView) findViewById(R.id.time);



        Snooze.setMinValue(0);
        Snooze.setMaxValue(20);
        Snooze.setWrapSelectorWheel(false);


        Snooze.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                time.setText(String.valueOf(Snooze.getValue()));
                wait = Snooze.getValue();
            }
        });



        SetTime.setOnClickListener(new Button.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
            public void onClick(View arg0) {


            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, mins + wait);
            calendar.set(Calendar.SECOND, 0 );

            Intent Intent1 = new Intent(MainActivity.this, MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, Intent1, 0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            new VibratingToast(MainActivity.this, "Exit Strategy Set.", Toast.LENGTH_SHORT);

            Intent startHome = new Intent(Intent.ACTION_MAIN);
            startHome.addCategory(Intent.CATEGORY_HOME);
            startHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startHome);

             }
        });


        CancelAll.setOnClickListener(new Button.OnClickListener() {
        @Override
             public void onClick(View arg0) {

            AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
            new VibratingToast(MainActivity.this, "Exit Strategy Aborted.", Toast.LENGTH_SHORT);

            Intent startHome = new Intent(Intent.ACTION_MAIN);
            startHome.addCategory(Intent.CATEGORY_HOME);
            startHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startHome);
        }
             });
    }


}