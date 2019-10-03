/*
 *This work is licensed under a Creative Commons Attribution 4.0 International License.
 *Author: Benedetto Marco Serinelli
 */
package ch.unige.sensorsample.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import ch.unige.sensorsample.R;
import ch.unige.sensorsample.controller.ChangeActivityController;
import ch.unige.sensorsample.model.ActivityModel;

/*
 * The following class defines third activity. It defines two button and performs a HTTP request in order to find public IP.
 * The button are defined with MVC patterns.
 * The HTTP GET request is implemented with loop library. It must be noticed that it is mandatory to add:
 * 1- permission in manifest for enablig internet permission
 * 2- import as "implementation" library in GRADLE Module:App file
 */
public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call constructor of
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button goToFirstActivity = findViewById(R.id.buttonThirdActToGoFirst);
        Button goToSecondActivity = findViewById(R.id.buttonThirdActToGoSecond);
        //define a controller, which implements the MVC for returning in MAIN activity
        ChangeActivityController goToMain = new ChangeActivityController(this.getBaseContext());
        goToMain.setNameOfActivity(ActivityModel.ACTIVITY_NAME_MAIN);
        goToFirstActivity.setOnClickListener(goToMain);
        //define a controller, which implements the MVC for returning in SECOND activity
        ChangeActivityController goToThird = new ChangeActivityController(this.getBaseContext());
        goToThird.setNameOfActivity(ActivityModel.ACTIVITY_NAME_SECOND);
        goToSecondActivity.setOnClickListener(goToThird);
        //example of GET Http request, using "loopj" library
        requestHttp();
    }

    private void requestHttp() {
        //for other infromation, go to https://loopj.com/android-async-http/
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://ip.jsontest.com/";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                // called when response HTTP status is "200 OK"
                String response = new String(responseBody);
                Log.d("Response", response);
                try {
                    JSONObject json = new JSONObject(response);
                    Log.d("IP", json.getString("ip"));
                } catch (JSONException e) {
                    Log.e("Error", "JSON Exception", e);
                }

            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}
