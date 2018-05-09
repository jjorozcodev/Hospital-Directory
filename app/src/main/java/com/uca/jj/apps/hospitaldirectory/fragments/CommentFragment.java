package com.uca.jj.apps.hospitaldirectory.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.adapters.CommentAdapter;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this.getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_edit_comment, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this.getContext());
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Comentar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        createCommentObject(userInputDialogEditText.getText().toString());
                    }
                })

                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    private void createCommentObject(String coment){
        CommentRequest c = new CommentRequest();

        c.setAuthorId(1);
        c.setHospitalId(idHospital);
        c.setMessage(coment);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date today = Calendar.getInstance().getTime();
        String dateComment = df.format(today);

        c.setCreatedDt(dateComment);

        requestComment(c);
    }

    private void requestComment(CommentRequest c){
        Call<CommentModel> call = Rest.instance().postComment(c);
        call.enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                saveCommentResponse(response.body());
                getCommentsFromDB();
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable t) {

            }
        });
    }

    private void saveCommentResponse(CommentModel commentModel){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CommentModel c = realm.createObject(CommentModel.class);

        c.setId(commentModel.getId());
        c.setAuthorId(commentModel.getAuthorId());
        c.setHospitalId(commentModel.getHospitalId());
        c.setCreatedDt(commentModel.getCreatedDt());
        c.setMessage(commentModel.getMessage());
        realm.commitTransaction();


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
