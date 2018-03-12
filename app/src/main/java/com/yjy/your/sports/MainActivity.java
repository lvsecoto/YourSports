package com.yjy.your.sports;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Formatter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = MainActivity.class.getName();

    private TextView mTextView;

    private SensorManager mSm;

    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv);

        mSm = (SensorManager) getSystemService(SENSOR_SERVICE);

        assert mSm != null;
        mSensor = mSm.getDefaultSensor(Sensor.TYPE_GRAVITY);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mSm.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private int mStatus = STATUS_READY;
    private static final int STATUS_READY = 0;
    private static final int STATUS_FINISH = 1;

    private int count = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float z = event.values[2];

        switch (mStatus) {
            case  STATUS_READY:
                if (z > 6) {
                    mStatus = STATUS_FINISH;
                }
                break;

            case STATUS_FINISH:
                if (z < 2) {
                    mStatus = STATUS_READY;
                    count++;
                    mTextView.setText("" + count);
                }

                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}