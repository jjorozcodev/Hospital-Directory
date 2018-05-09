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
import android.widget.Toast;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.adapters.CommentAdapter;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {

    private Button btnComment;
    private String idHospital;
    private RecyclerView rvComments;
    private final String MY_ACCOUNT = "my_account_author";
    private final String AUTHOR_ID = "author_id";


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
        rvComments.setLayoutManager(new LinearLayoutManager(v.getContext()));
        btnComment = v.findViewById(R.id.btnComment);


        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(hasUserAuthor())
                    Toast.makeText(view.getContext(), "Comentar para hospital con ID: " + idHospital, Toast.LENGTH_LONG).show();
                else
                    createAuthor();
            }
        });

        fetchComments();

        return v;
    }


    private boolean hasUserAuthor() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE);
        if(sharedPreferences.getInt(AUTHOR_ID, -1) > 0)
            return true;

        return false;
    }

    private void storeAccountAuthor() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AUTHOR_ID, 7);
        editor.commit();
    }


    private void createAuthor(){
        Toast.makeText(getContext(), "Solicitar datos del author", Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(), "Registrarlo", Toast.LENGTH_LONG).show();
    }

    private void fetchComments(){
        Call<ArrayList<CommentModel>> call = Rest.instance().commentsHospital(Integer.parseInt(idHospital));
        call.enqueue(new Callback<ArrayList<CommentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentModel>> call, Response<ArrayList<CommentModel>> response) {
                if(response.body() != null){
                    CommentAdapter commentAdapter = new CommentAdapter(response.body());
                    rvComments.setAdapter(commentAdapter);
                    Log.i("Comentarios: ", "|" + String.valueOf(commentAdapter.getItemCount()) + "|");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CommentModel>> call, Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });
    }
}
