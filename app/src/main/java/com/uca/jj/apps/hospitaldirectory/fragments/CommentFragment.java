package com.uca.jj.apps.hospitaldirectory.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.activities.CommentActivity;
import com.uca.jj.apps.hospitaldirectory.activities.DetailHospital;

public class CommentFragment extends Fragment {

    private Button btnComment;
    private String idHospital;
    private RecyclerView rvComments;


    public CommentFragment() {
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
        View v = inflater.inflate(R.layout.fragment_comment, container, false);

        idHospital = this.getArguments().getString("id");

        rvComments = v.findViewById(R.id.rvComments);
        btnComment = v.findViewById(R.id.btn_comment);


        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Comentar para hospital con ID: " + idHospital, Toast.LENGTH_LONG).show();
            }
        });



        return v;
    }
}
