package com.zerospeed8.exitstrategy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CallScreen extends Activity{

    ImageButton Awnser;
    ImageButton Snooze;
    ImageButton Done;
    public int wait;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_screen);

        blink();
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        int dot = 200;      // Length of a Morse Code "dot" in milliseconds
        int dash = 500;     // Length of a Morse Code "dash" in milliseconds
        int short_gap = 200;    // Length of Gap Between dots/dashes
        int medium_gap = 500;   // Length of Gap Between Letters
        int long_gap = 1000;    // Length of Gap Between Words
        long[] pattern = {
                0,  // Start immediately
                dot, short_gap, dot, short_gap, dot,    // s
                medium_gap,
                dash, short_gap, dash, short_gap, dash, // o
                medium_gap,
                dot, short_gap, dot, short_gap, dot,    // s
                long_gap};
        v.vibrate(pattern, 0);

        Awnser = (ImageButton) findViewById(R.id.awnserButton);
        Snooze = (ImageButton) findViewById(R.id.snoozeButton);
        Done = (ImageButton) findViewById(R.id.doneButton);

        Awnser.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                v.cancel();
                new VibratingToast(CallScreen.this, "Click Hang Up When Finished.", Toast.LENGTH_SHORT);

            }
        });

        Snooze.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                v.cancel();

                new VibratingToast(CallScreen.this, "Snoozed.", Toast.LENGTH_SHORT);
            }
        });

        Done.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                v.cancel();
                new VibratingToast(CallScreen.this, "You Are Free!", Toast.LENGTH_SHORT);

                Intent startHome = new Intent(Intent.ACTION_MAIN);
                startHome.addCategory(Intent.CATEGORY_HOME);
                startHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startHome);
            }
        });
    }

    private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 100;
                try{Thread.sleep(timeToBlink);}
                catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView txt = (TextView) findViewById(R.id.incomingCall);
                        if(txt.getVisibility() == View.VISIBLE){
                            txt.setVisibility(View.INVISIBLE);
                        }else{
                            txt.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }

}


