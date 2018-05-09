package com.uca.jj.apps.hospitaldirectory.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.adapters.HospitalAdapter;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.models.AuthorModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;
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

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadData();
    }

    private void initViews (){
        rvHospitals = (RecyclerView) findViewById(R.id.rvHospitals);
        rvHospitals.setHasFixedSize(true);
        rvHospitals.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
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

    private void loadData(){
        fetchHospitals();
        fetchComments();
        fetchAuthors();
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
                getHospitalsFromDB();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void fetchComments(){
        Call<ArrayList<CommentModel>> call = Rest.instance().allComments();
        call.enqueue(new Callback<ArrayList<CommentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentModel>> call, Response<ArrayList<CommentModel>> response) {
                syncComments(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<CommentModel>> call, Throwable t) {
            }
        });
    }

    private void fetchAuthors(){
        Call<ArrayList<AuthorModel>> call = Rest.instance().allAuthors();
        call.enqueue(new Callback<ArrayList<AuthorModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AuthorModel>> call, Response<ArrayList<AuthorModel>> response) {
                syncAuthors(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<AuthorModel>> call, Throwable t) {

            }
        });
    }

    private void saveHospitals(ArrayList<HospitalModel> hospitals){
        for (HospitalModel hospitalModel : hospitals){
            storeHospital(hospitalModel);
        }
    }

    private void syncComments(ArrayList<CommentModel> comments){
        clearComments();
        for (CommentModel commentModel : comments){
            storeComment(commentModel);
        }
    }

    private void syncAuthors(ArrayList<AuthorModel> authors){
        clearAuthors();
        for(AuthorModel authorModel : authors){
            storeAuthor(authorModel);
        }
    }

    private void clearAuthors(){
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<AuthorModel> query = realm.where(AuthorModel.class);

        RealmResults<AuthorModel> results = query.findAll();

        for(int i=0; i<results.size(); i++){
            deleteAuthorFromDB(results.get(i));
        }
    }

    private void storeAuthor(AuthorModel authorModel){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        AuthorModel a = realm.createObject(AuthorModel.class);

        a.setId(authorModel.getId());
        a.setName(authorModel.getName());
        a.setLastname(authorModel.getLastname());
        a.setCellphone(authorModel.getCellphone());
        a.setEmail(authorModel.getEmail());

        realm.commitTransaction();
    }

    private void deleteAuthorFromDB(AuthorModel authorModel){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        authorModel.deleteFromRealm();
        realm.commitTransaction();
    }

    private void clearComments(){
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<CommentModel> query = realm.where(CommentModel.class);

        RealmResults<CommentModel> results = query.findAll();

        for(int i=0; i<results.size(); i++){
            deleteCommentFromDB(results.get(i));
        }
    }

    private void deleteCommentFromDB(CommentModel commentModel){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        commentModel.deleteFromRealm();
        realm.commitTransaction();
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

    private void storeComment(CommentModel commentModel){
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

    private void getHospitalsFromDB() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<HospitalModel> query = realm.where(HospitalModel.class);

        RealmResults<HospitalModel> results = query.findAll();

        ArrayList<HospitalModel> hData = new ArrayList<>();
        hData.addAll(realm.copyToRealm(results));
        HospitalAdapter hospitalAdapter = new HospitalAdapter(hData);
        rvHospitals.setAdapter(hospitalAdapter);
    }

}
