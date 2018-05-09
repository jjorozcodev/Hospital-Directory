package com.uca.jj.apps.hospitaldirectory.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.adapters.CommentAdapter;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {

    private Button btnComment;
    private int idHospital;
    private RecyclerView rvComments;

    private TextView tvAviso;

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

        idHospital = Integer.parseInt(this.getArguments().getString("id"));

        rvComments = v.findViewById(R.id.rvComments);
        rvComments.setLayoutManager(new LinearLayoutManager(v.getContext()));
        btnComment = v.findViewById(R.id.btnComment);

        tvAviso = (TextView) v.findViewById(R.id.txtNoComments);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            createComment();
            }
        });

        getCommentsFromDB();

        return v;
    }

    private void createComment(){

    }

    private void getCommentsFromDB() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<CommentModel> query = realm.where(CommentModel.class);

        RealmResults<CommentModel> results = query.findAll();

        ArrayList<CommentModel> cData = new ArrayList<>();
        cData.addAll(realm.copyFromRealm(results));

        ArrayList<CommentModel> commentsHospital = new ArrayList<>();
        for (CommentModel commentModel : cData){
            if(commentModel.getHospitalId()==idHospital){
                commentsHospital.add(commentModel);
            }
        }

        CommentAdapter commentAdapter = new CommentAdapter(commentsHospital);
        rvComments.setAdapter(commentAdapter);

        if(commentsHospital.size()>0){
            tvAviso.setVisibility(View.INVISIBLE);
        }
        else
            tvAviso.setVisibility(View.VISIBLE);
    }

}
