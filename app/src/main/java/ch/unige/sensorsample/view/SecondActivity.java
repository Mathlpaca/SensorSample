/*
 *This work is licensed under a Creative Commons Attribution 4.0 International License.
 *Author: Benedetto Marco Serinelli
 */
package ch.unige.sensorsample.view;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ch.unige.sensorsample.R;
import ch.unige.sensorsample.controller.ChangeActivityController;
import ch.unige.sensorsample.controller.LightSensorController;
import ch.unige.sensorsample.model.ActivityModel;

/*
 * The following class defines second activity.
 */
public class SecondActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private LightSensorController lightSensorController;
    private static AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //define a ImageButton
        ImageButton buttonToFirstActivity = findViewById(R.id.button2);
        ChangeActivityController changeActivityController = new ChangeActivityController(this.getBaseContext()); //define a controller
        changeActivityController.setNameOfActivity(ActivityModel.ACTIVITY_NAME_MAIN);
        buttonToFirstActivity.setOnClickListener(changeActivityController); //hook the listener to button, in order to capture the click (tap)
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE); //register sensor for detecting light
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightSensorController = new LightSensorController();
        activity = this;
    }

    public void printSensorValue(String value) {
        TextView txViewLightSensor = activity.findViewById(R.id.textViewLight);
        txViewLightSensor.setText(String.valueOf(value));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightSensorController, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightSensorController);
    }

}
