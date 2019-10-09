/*
 *This work is licensed under a Creative Commons Attribution 4.0 International License.
 *Author: Benedetto Marco Serinelli
 */
package ch.unige.sensorsample.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import ch.unige.sensorsample.model.ActivityModel;
import ch.unige.sensorsample.view.MainActivity;
import ch.unige.sensorsample.view.SecondActivity;
import ch.unige.sensorsample.view.ThirdActivity;

/*
 *The class permits to manage a change activity. It acs as controller for generic context in order to
 * moving among appcompact activities, defined in manifest and Java class.
 */

public class ChangeActivityController implements View.OnClickListener {
    private Context context;
    private String nameOfActivity;

    //constructor. It initializes the context attribute, whihc permits to build a Intent object
    public ChangeActivityController(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        //simply switch that permits to change activity
        switch (nameOfActivity) {
            case ActivityModel.ACTIVITY_NAME_MAIN: {
                Log.d("Debug", "Switch activity from main to second");
                intent = new Intent(context, MainActivity.class); //define intent in order to show new activity
                break;
            }
            case ActivityModel.ACTIVITY_NAME_SECOND: {
                Log.d("Debug", "Switch activity from second to main");
                intent = new Intent(context, SecondActivity.class);
                break;
            }
            case ActivityModel.ACTIVITY_NAME_THIRD: {
                intent = new Intent(context, ThirdActivity.class);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + nameOfActivity); //launch an exception if the activity name is not define in model
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void setNameOfActivity(String nameOfActivity) {
        this.nameOfActivity = nameOfActivity;
    }
}
