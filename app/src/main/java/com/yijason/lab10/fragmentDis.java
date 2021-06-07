package com.yijason.lab10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

public class fragmentDis extends Fragment {

    ViewPager2 viewPager2;
    Button mButton;
    int mPosition;
    JSONObject tacos;
    TextView mName;
    TextView mRecipe;
    int i = 0;
    static String[] names = {"shell", "seasoning", "mixin", "base_layer", "condiment"};
    public static fragmentDis newInstance(JSONObject tacos) throws JSONException {
        fragmentDis fragment = new fragmentDis();
        if(tacos == null)
            fragment.tacos = null;
        else
            fragment.tacos = tacos;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentdis, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //add to tablayout
        //assign response to button onclick
        mButton = view.findViewById(R.id.tee);
        mName = view.findViewById(R.id.name);
        mRecipe = view.findViewById(R.id.recipe);

        update();
        //set text to the current fragment's position number
        mButton.setText("See Other Components");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i >= 5)
                    i = 0;
                update();
            }
        });
    }

    public void update(){
        if(tacos == null){
            mName.setText("PRESS BUTTON");
            mRecipe.setText("PRESS BUTTON");
        }

        else {
            try {
                mName.setText(tacos.getJSONObject(names[i]).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                mRecipe.setText(tacos.getJSONObject(names[i]).getString("recipe"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
