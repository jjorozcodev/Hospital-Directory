package com.uca.jj.apps.hospitaldirectory.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uca.jj.apps.hospitaldirectory.R;

public class InfoHospitalFragment extends Fragment {

    private TextView txtDir, txtTel, txtWeb;
    private ImageView imgLocation, imgWebsite, imgCalling;

    public InfoHospitalFragment() {
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

        View v = inflater.inflate(R.layout.fragment_info_hospital, container, false);

        imgCalling = (ImageView) v.findViewById(R.id.imgCall);
        imgLocation = (ImageView) v.findViewById(R.id.imgLocation);
        imgWebsite = (ImageView) v.findViewById(R.id.imgWeb);

        txtDir = (TextView) v.findViewById(R.id.txtAddress);
        txtTel = (TextView) v.findViewById(R.id.txtTelephone);
        txtWeb = (TextView) v.findViewById(R.id.txtWebsite);

        txtDir.setText(this.getArguments().getString("address"));
        txtTel.setText(this.getArguments().getString("telephone"));
        txtWeb.setText(this.getArguments().getString("website"));

        imgCalling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtTel.getText().toString()));
                startActivity(callIntent);
            }
        });

        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:12.1158,-86.2511");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + txtWeb.getText().toString()));
                startActivity(browserIntent);
            }
        });

        return v;
    }
}
