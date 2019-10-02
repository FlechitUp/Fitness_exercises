package com.example.exercises;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class StepCou extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    TextView tv_steps;
    private CountDownTimer countDownTimer;
    private TextView  timestep;
    //ProgressBar progressBar;
    private int counter = 0;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step);
        tv_steps = (TextView) findViewById(R.id.total_steps);
        timestep = (TextView)  findViewById(R.id.timestep);
        countDownTimer = new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                timestep.setText(" " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timestep.setText("DONE");
                running = false;
            }
        }.start();
        //progressBar = (ProgressBar) findViewById(R.id.circle_bar);
        //progressBar.setMax(100);

        ///progressBar.setMin(1);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        running = false;

        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            //counter ++;
            //progressBar.setProgress(counter);
            tv_steps.setText(" "+String.valueOf((int) event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
