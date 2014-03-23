package com.zerospeed8.exitstrategy;

/**
 * Created by zerospeed8 on 3/20/14.
 */
import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;;

public class VibratingToast extends Toast {

    public VibratingToast(Context context, CharSequence text, int duration) {
        super(context);
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
        super.makeText(context, text, duration).show();
    }
}