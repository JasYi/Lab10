package com.yijason.lab10;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private Button getApiBtn, postApiBtn;
    private TextView resultTextView;
    RequestQueue requestQueue;
    private static final String TAG = MainActivity.class.getSimpleName();
    String APIurl = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.results);
        getApiBtn = (Button) findViewById(R.id.getBtn);
        postApiBtn = (Button) findViewById(R.id.postBtn);

        //RequestQueue For Handle Network Request
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Click Listener for GET JSONObject
        getApiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getData();
            }
        });

        //Click Listner for POST JSONObject
        postApiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                postData();
            }
        });
    }

    //Post Request for JSONObject
    public void postData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try{
            //input your API parameters
            object.put("parameter", "value");
            object.put("parameter", "value");
        } catch(JSONException e){
            e.printStackTrace();
        }
        //Enter the correct url for your api service site
        String url = APIurl;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        resultTextView.setText("String Response: " + response.toString());
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                resultTextView.setText("Error getting response");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void getData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            String url = APIurl;
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