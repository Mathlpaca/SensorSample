/*
 *This work is licensed under a Creative Commons Attribution 4.0 International License.
 *Author: Benedetto Marco Serinelli
 */
package ch.unige.sensorsample.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;


/*
 * This class defines the controller for gyroscope in order to implement a MVC patter. The follow class permit to manage
 * gyroscope sensor for logging sensor's values (See https://developer.android.com/reference/android/util/Log )
 */
public class GyroscopeSensorController implements SensorEventListener {
    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("Gyroscope Sensor", "Value. Rate of rotation around the x axis: " + event.values[0] + "rad/s");
        Log.d("Gyroscope Sensor", "Value. Rate of rotation around the y axis: " + event.values[1] + "rad/s");
        Log.d("Gyroscope Sensor", "Value. Rate of rotation around the z axis: " + event.values[2] + "rad/s");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
