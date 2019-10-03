/*
 *This work is licensed under a Creative Commons Attribution 4.0 International License.
 *Author: Benedetto Marco Serinelli
 */
package ch.unige.sensorsample.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ch.unige.sensorsample.R;

/*
 * This class defines the controller for light sensor in order to implement a MVC patter. The follow class permit to manage
 * light sensor, written the values in textview
 */
public class LightSensorController implements SensorEventListener {
    private AppCompatActivity activity;

    public LightSensorController(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.d("LightSensorController", String.valueOf(event.values[0]));
            TextView txViewLightSensor = activity.findViewById(R.id.textViewLight);
            txViewLightSensor.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
