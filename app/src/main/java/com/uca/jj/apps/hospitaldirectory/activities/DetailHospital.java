package com.uca.jj.apps.hospitaldirectory.activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.fragments.AboutHospitalFragment;
import com.uca.jj.apps.hospitaldirectory.fragments.CommentFragment;
import com.uca.jj.apps.hospitaldirectory.fragments.InfoHospitalFragment;

public class DetailHospital extends AppCompatActivity implements InfoHospitalFragment.OnFragmentInteractionListener, AboutHospitalFragment.OnFragmentInteractionListener, CommentFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        InfoHospitalFragment infoHospitalFragment = new InfoHospitalFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, infoHospitalFragment);

        AboutHospitalFragment aboutHospitalFragment = new AboutHospitalFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, aboutHospitalFragment);

        CommentFragment commentFragment = new CommentFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, commentFragment);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.action_info:

                        InfoHospitalFragment infoHospitalFragment = new InfoHospitalFragment();
                        fragmentTransaction.replace(R.id.container, infoHospitalFragment);
                        fragmentTransaction.commit();

                        break;

                    case R.id.action_about:

                        AboutHospitalFragment aboutHospitalFragment = new AboutHospitalFragment();
                        fragmentTransaction.replace(R.id.container, aboutHospitalFragment);
                        fragmentTransaction.commit();

                        break;

                    case R.id.action_comment:

                        CommentFragment commentFragment = new CommentFragment();
                        fragmentTransaction.replace(R.id.container, commentFragment);
                        fragmentTransaction.commit();

                        break;
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
