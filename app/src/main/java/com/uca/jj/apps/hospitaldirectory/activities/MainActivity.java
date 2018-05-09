package com.uca.jj.apps.hospitaldirectory.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.adapters.HospitalAdapter;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.models.AuthorModel;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHospitals;
    private static final String APP_NAME = "Hospital_Directory";
    private static final String IS_FIRST_TIME = "is_first_time";
    private int authorId;

    private final String MY_ACCOUNT = "my_account_author";
    private final String AUTHOR_ID = "author_id";

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadMyAccount();
        fetchHospitals();
    }

    private void initViews (){
        rvHospitals = (RecyclerView) findViewById(R.id.rvHospitals);
        rvHospitals.setHasFixedSize(true);
        rvHospitals.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchHospitals();
            }
        });
    }

    private boolean isFirstTime() {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_TIME, true);
    }

    private void storeFirstTime() {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME, false);
        editor.commit();
    }

    private void fetchHospitals(){
        Call<ArrayList<HospitalModel>> call = Rest.instance().allHospitals();
        call.enqueue(new Callback<ArrayList<HospitalModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HospitalModel>> call, Response<ArrayList<HospitalModel>> response) {
                if(isFirstTime()){
                    saveHospitals(response.body());
                    storeFirstTime();
                }
                getHospitalsFromDB();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ArrayList<HospitalModel>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void saveHospitals(ArrayList<HospitalModel> hospitals){
        for (HospitalModel hospitalModel : hospitals){
            storeHospital(hospitalModel);
        }
    }

    private void storeHospital(HospitalModel hModel) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        HospitalModel h = realm.createObject(HospitalModel.class);

        h.setId(hModel.getId());
        h.setName(hModel.getName());
        h.setSlogan(hModel.getSlogan());
        h.setDescription(hModel.getDescription());
        h.setTelephone(hModel.getTelephone());
        h.setAddress(hModel.getAddress());
        h.setLatitude(hModel.getLatitude());
        h.setLength(hModel.getLength());
        h.setUrlImgLarge(hModel.getUrlImgLarge());
        h.setUrlImgSmall(hModel.getUrlImgSmall());
        h.setWebsite(hModel.getWebsite());

        realm.commitTransaction();
    }

    private void getHospitalsFromDB() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<HospitalModel> query = realm.where(HospitalModel.class);

        RealmResults<HospitalModel> results = query.findAll();

        ArrayList<HospitalModel> hData = new ArrayList<>();
        hData.addAll(realm.copyToRealm(results));
        HospitalAdapter hospitalAdapter = new HospitalAdapter(hData);
        rvHospitals.setAdapter(hospitalAdapter);
    }

    private void loadMyAccount(){
        if(hasUserAuthor()){
            //saveAccountAuthor();
            Log.i("AUTHHH", "cuenta contable");
        }
        Log.i("AUTHHH", "no hay cuenta activa");
        fetchAuthor();
    }

    private boolean hasUserAuthor() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE);
        authorId = sharedPreferences.getInt(AUTHOR_ID, -1);
        if(authorId > 0)
            return true;

        return false;
    }

    private void fetchAuthor(){
        Call<AuthorModel> call = Rest.instance().getAuthor(1);
        call.enqueue(new Callback<AuthorModel>() {
            @Override
            public void onResponse(Call<AuthorModel> call, Response<AuthorModel> response) {
                saveAccountAuthor(response.body());
            }

            @Override
            public void onFailure(Call<AuthorModel> call, Throwable t) {

            }
        });
    }

    private void saveAccountAuthor(AuthorModel authorModel) {
        Log.i("AUTHOR-ID", String.valueOf(authorId));
        Log.i("AUTHOR", authorModel.getName());
        Log.i("AUTHOR", authorModel.getLastname());
        Log.i("AUTHOR", String.valueOf(authorModel.getCellphone()));
        Log.i("AUTHOR", authorModel.getEmail());
    }


}
