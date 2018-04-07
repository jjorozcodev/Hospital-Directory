package com.uca.jj.apps.hospitaldirectory.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uca.jj.apps.hospitaldirectory.R;

public class AboutHospitalFragment extends Fragment {

    private TextView txtDesc;

    public AboutHospitalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_about_hospital, container, false);
        txtDesc = (TextView) v.findViewById(R.id.txtDescription);

        txtDesc.setText(this.getArguments().getString("description"));

        return v;
    }

}
