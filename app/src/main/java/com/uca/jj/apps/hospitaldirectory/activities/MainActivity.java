package com.uca.jj.apps.hospitaldirectory.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.adapters.HospitalAdapter;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView HospitalRecyclerView;
    ///ProgressBar
    private ProgressBar mProgressBar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        LoadData();

    }

    private void LoadData(){
        initViews();
        configureRecyclerView();
        fetchHttpRequest();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100){
                    mProgressStatus++;
                    android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        HospitalRecyclerView.setVisibility(View.VISIBLE);

                    }
                });
            }
        }).start();
    }




    private void initViews (){
        HospitalRecyclerView = (RecyclerView) findViewById(R.id.rvHospitals);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    private void configureRecyclerView (){
        HospitalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void fetchHttpRequest(){
        Call<ArrayList<HospitalModel>> call = Rest.instance().allHospitals();
        call.enqueue(new Callback<ArrayList<HospitalModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HospitalModel>> call, Response<ArrayList<HospitalModel>> response) {
                if(response.body() != null){
                    HospitalAdapter hospitalAdapter = new HospitalAdapter(response.body());
                    HospitalRecyclerView.setAdapter(hospitalAdapter);
                }
                //Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArrayList<HospitalModel>> call, Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });
    }
}
