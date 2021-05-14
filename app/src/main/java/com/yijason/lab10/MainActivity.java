package com.yijason.lab10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//final app project idea:
//app that asks for your city then uses the golf api to find courses near you (https://golf-course-database.com/api-v1-0/)
//can also have option to send email asking for a tee time
//maybe integrate location services into this?

public class MainActivity extends AppCompatActivity {

    private Button getApiBtn, postApiBtn;
    private TextView resultTextView;
    RequestQueue requestQueue;
    String apiURL = "https://jsonplaceholder.typicode.com/todos/1";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ft.add(R.id.fragmentArea, fragmentDis.newInstance(),"FragmentName");
        //or ft.replace(R.id.fragment_container, new FragmentB());

        // Complete the changes added above
        ft.commit();

        resultTextView = (TextView) findViewById(R.id.results);
        getApiBtn = (Button) findViewById(R.id.getBtn);
        postApiBtn = (Button)findViewById(R.id.postBtn);

        // RequestQueue For Handle Network Request
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Click Listner for GET JSONObject
        getApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        //Click Listner for POST JSONObject
        postApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }








    // Post Request For JSONObject
    public void postData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("parameter","value");
            object.put("parameter","value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = apiURL;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error getting response");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    // Get Request For JSONObject
    public void getData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            String url = apiURL;
            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    resultTextView.setText("Resposne : " + response.toString());
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}