package com.uca.jj.apps.hospitaldirectory.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uca.jj.apps.hospitaldirectory.R;

public class InfoHospitalFragment extends Fragment {

    private TextView txtDir, txtTel, txtWeb;
    private ImageView imgLocation, imgWebsite, imgCalling;
    private String nameH, latitude, length;
    private final String NO_SITE = "No tiene sitio.";

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

        String webPage=this.getArguments().getString("website");
        if(webPage==null)
            webPage = NO_SITE;
        txtWeb.setText(webPage);

        nameH = this.getArguments().getString("name");

        latitude = this.getArguments().getString("latitude");
        length = this.getArguments().getString("length");

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
                String uriMap = "geo:" + latitude + "," + length;
                Uri gmmIntentUri = Uri.parse(uriMap);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriStr;
                if(txtWeb.getText().toString().equals(NO_SITE))
                    uriStr = "http://www.google.com/search?q=" + nameH;
                else
                    uriStr = "http://" + txtWeb.getText().toString();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriStr));
                startActivity(browserIntent);
            }
        });

        return v;
    }
}
