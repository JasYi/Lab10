package com.yijason.lab10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import org.w3c.dom.Text;

import java.lang.reflect.Array;

//final app project idea:
//https://stackoverflow.com/questions/55728719/get-current-fragment-with-viewpager2
//use that link to find the fragment from the view pager support manager
//after finding the fragment store the view as a global variable so you can acsess it later

public class MainActivity extends AppCompatActivity {

    private Button getApiBtn, postApiBtn;
    private TextView display;
    public String cityName;
    fragmentDis fragmentEdit;
    JSONObject recipes = null;
    FragmentManager myManager;
    String apiURL = "http://taco-randomizer.herokuapp.com/random/";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(recipes + "hiiiii :)");

        display = (TextView) findViewById(R.id.results);
        getApiBtn = (Button) findViewById(R.id.getBtn);

        getData();
        Log.i("FINDME", recipes + "");

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
        /*postApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });*/

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
                        display.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display.setText("Error getting response");
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
            Log.i("FINDME", "at start");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Begin the transaction
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    // Replace the contents of the container with the new fragment
                    try {
                        ft.replace(R.id.container, fragmentDis.newInstance(response),"FragmentName");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //or ft.replace(R.id.fragment_container, new FragmentB());

                    // Complete the changes added above
                    ft.commit();
                    Log.i("FINDME", recipes + "");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("FINDME", error + "");
                }
            });
            Log.i("FINDME", "at end");
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}