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
    RequestQueue requestQueue;
    RecyclerView.Adapter mMyFragmentStateAdapter;
    ViewPager2 mViewPager;
    FragmentManager myManager;
    String apiURL = "http://taco-randomizer.herokuapp.com/random/";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.results);
        getApiBtn = (Button) findViewById(R.id.getBtn);


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

        //assign the instance of ViewPager
        mViewPager = findViewById(R.id.container);

        //create an adapter for the ViewPager
        mMyFragmentStateAdapter = new MyFragmentStateAdapter(this);

        //set the adapter for the ViewPager
        mViewPager.setAdapter(mMyFragmentStateAdapter);
    }
    public class MyFragmentStateAdapter extends FragmentStateAdapter{

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        @NonNull
        @Override
        public fragmentDis createFragment(int position) {
            //return a new instance of MainFragment
            return fragmentDis.newInstance(mViewPager, position);
        }
        @Override
        public int getItemCount() {
            return 5;//number of objects in ViewPager
        }
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
                        //resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //resultTextView.setText("Error getting response");
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
                    try {
                        display(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

    public void display(JSONObject j) throws JSONException {
        JSONObject[] recipe = new JSONObject[5];
        recipe[0] = j.getJSONObject("shell");
        recipe[1] = j.getJSONObject("seasoning");
        recipe[2] = j.getJSONObject("mixin");
        recipe[3] = j.getJSONObject("base-layer");
        recipe[4] = j.getJSONObject("condiment");

        /*for(int i = 0; i < recipe.length; i++) {
            var myFrag =
        }*/
    }

}