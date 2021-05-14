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

public class fragmentDis extends Fragment {

    public static fragmentDis newInstance(String name, String website, String email) {
        fragmentDis fragmentB = new fragmentDis();
        Bundle args = new Bundle();//package variables into Bundle
        args.putString("name", name);
        args.putString("website", website);
        args.putString("email", email);
        fragmentB.setArguments(args);//assign bundle to arguments
        return fragmentB;
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
        TextView name = view.findViewById(R.id.name);
        name.setText(getArguments().getString("name", ""));
        TextView website = view.findViewById(R.id.website);
        website.setText(getArguments().getString("website", ""));
        Button email = view.findViewById(R.id.tee);//set on click thingy
    }

    // Parent Activity has finished loading, access parent views here
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
