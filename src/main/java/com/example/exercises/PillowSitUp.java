package com.example.exercises;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PillowSitUp extends AppCompatActivity {
    private TextView  coumtPillows, timepillow;
    private SensorManager mySensorManager;
    private Sensor myProximitySensor;
    private CountDownTimer countDownTimer;
    private Integer coumt=0;
    private boolean working = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pillow);
        getSupportActionBar().hide();
        coumtPillows = (TextView) findViewById(R.id.coumtPillows);
        timepillow = (TextView) findViewById(R.id.timepillow);

        countDownTimer = new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                timepillow.setText(" " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timepillow.setText("DONE");
                working = false;
            }
        }.start();

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mySensorManager.registerListener(proximitySensorEventListener,
                myProximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0 && working) {
                    coumt ++;  ///near
                    coumtPillows.setText(coumt.toString());
                }
            }
        }

    };
}