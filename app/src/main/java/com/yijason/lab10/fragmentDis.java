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
    static String[] names = {"shell", "seasoning", "mixin", "base_layer", "condiment"};
    public static fragmentDis newInstance(ViewPager2 mViewPager, int position, JSONObject tacos) throws JSONException {
        fragmentDis fragment = new fragmentDis();
        fragment.viewPager2 = mViewPager;
        fragment.mPosition = position + 1;
        fragment.tacos = tacos.getJSONObject(names[position]);
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
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText("I am  " + (position + 1))
        ).attach();
        //assign response to button onclick
        mButton = view.findViewById(R.id.tee);
        mName = view.findViewById(R.id.name);
        mRecipe = view.findViewById(R.id.recipe);

        try {
            mName.setText( tacos.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mRecipe.setText( tacos.getString("recipe"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //set text to the current fragment's position number
        mButton.setText("Press Me "+ mPosition);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"toasty" + viewPager2.getCurrentItem()
                        , Toast.LENGTH_LONG).show();
            }
        });
        System.out.println("Testing: I created a new fragment"+viewPager2.getCurrentItem());
    }
}
