/*
 *This work is licensed under a Creative Commons Attribution 4.0 International License.
 *Author: Benedetto Marco Serinelli
 */
package ch.unige.sensorsample.view;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ch.unige.sensorsample.R;
import ch.unige.sensorsample.controller.ChangeActivityController;
import ch.unige.sensorsample.controller.GyroscopeSensorController;
import ch.unige.sensorsample.model.ActivityModel;

/*
 * The following class defines main activity.
 */
public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private GyroscopeSensorController gyroscopeSensorController;
    private Sensor sensorGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //rendering main activity, defined in XML configuration file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create button for moving in second activity
        Button buttonToSecondActivity = findViewById(R.id.button1);
        //using a controller, MVC pattern, in order to manage the change of activity
        ChangeActivityController gotoToSecond = new ChangeActivityController(this.getBaseContext());
        gotoToSecond.setNameOfActivity(ActivityModel.ACTIVITY_NAME_SECOND); //set the second activity, using a model
        buttonToSecondActivity.setOnClickListener(gotoToSecond); //add listener
        //create button for moving in third activity
        Button gotoThirdActivity = findViewById(R.id.mainActivityGoToThirdActivity);
        ChangeActivityController gotoToThird = new ChangeActivityController(this.getBaseContext());
        gotoToThird.setNameOfActivity(ActivityModel.ACTIVITY_NAME_THIRD);//set the third activity, using a model
        gotoThirdActivity.setOnClickListener(gotoToThird);//add listener
        //Declare a sensor manager in order to access sensor values
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        //using a controller patter to manage a sensor
        gyroscopeSensorController = new GyroscopeSensorController(this); //declare gyroscope controller
        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    //when resume application, it is necessary to register the sensor listener
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeSensorController, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //when application is in pause, background,  it is necessary to remove the sensor listener in order to avoid high consumption of device resource
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeSensorController);
    }

}
