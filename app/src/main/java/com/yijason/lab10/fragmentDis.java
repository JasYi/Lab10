package com.yijason.lab10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class fragmentDis extends Fragment {

    ViewPager2 viewPager2;
    int position;
    View mView;

    public static fragmentDis newInstance(ViewPager2 mViewPager, int position) {
        fragmentDis fragment = new fragmentDis();
        fragment.viewPager2 = mViewPager;
        fragment.position = position;
        return fragment;
    }

    //View inflation/instantiation
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentdis, container,false);
    }
    // Setup requiring access to View starts here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setup here
        mView = view;
        TextView name = view.findViewById(R.id.name);
        name.setText(getArguments().getString("name", ""));
        TextView website = view.findViewById(R.id.website);
        website.setText(getArguments().getString("recipe", ""));
        Button email = view.findViewById(R.id.tee);//set on click thingy
    }

    // Parent Activity has finished loading, access parent views here
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
