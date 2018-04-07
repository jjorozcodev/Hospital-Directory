package com.uca.jj.apps.hospitaldirectory.activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.fragments.AboutHospitalFragment;
import com.uca.jj.apps.hospitaldirectory.fragments.CommentFragment;
import com.uca.jj.apps.hospitaldirectory.fragments.InfoHospitalFragment;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import org.w3c.dom.Text;

public class DetailHospital extends AppCompatActivity {

    private SimpleDraweeView logoHospital;
    private TextView txtNameHospital;

    private HospitalModel hModel = new HospitalModel();
    private Bundle bundleArgs;

    private BottomNavigationView btnNavView;

    private InfoHospitalFragment infoHospitalFragment = new InfoHospitalFragment();
    private AboutHospitalFragment aboutHospitalFragment = new AboutHospitalFragment();
    private CommentFragment commentFragment = new CommentFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        final Bundle bundle = getIntent().getExtras();

        if(bundle.getString("name") != null)
        {

            hModel.setId(bundle.getInt("id"));
            hModel.setName(bundle.getString("name"));
            hModel.setSlogan(bundle.getString("slogan"));
            hModel.setTelephone(bundle.getInt("telephone"));
            hModel.setDescription(bundle.getString("description"));
            hModel.setAddress(bundle.getString("address"));
            hModel.setWebsite(bundle.getString("website"));
            hModel.setUrlImg(bundle.getString("urlImg"));

        }

        logoHospital = (SimpleDraweeView) findViewById(R.id.logoHospital);
        txtNameHospital = (TextView) findViewById(R.id.txtNameHospital);

        logoHospital.setImageURI(Uri.parse(hModel.getUrlImg()));
        txtNameHospital.setText(hModel.getName());

        btnNavView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        btnNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.action_info:
                        bundleArgs = new Bundle();
                        bundleArgs.putString("address", hModel.getAddress());
                        bundleArgs.putString("telephone", String.valueOf(hModel.getTelephone()));
                        bundleArgs.putString("website", hModel.getWebsite());
                        infoHospitalFragment.setArguments(bundleArgs);
                        setFragment(infoHospitalFragment);
                        break;

                    case R.id.action_about:
                        bundleArgs = new Bundle();
                        bundleArgs.putString("description", hModel.getDescription());
                        aboutHospitalFragment.setArguments(bundleArgs);
                        setFragment(aboutHospitalFragment);
                        break;

                    case R.id.action_comment:
                        bundleArgs = new Bundle();
                        bundleArgs.putString("id", String.valueOf(hModel.getId()));
                        commentFragment.setArguments(bundleArgs);
                        setFragment(commentFragment);
                        break;
                }
                return false;
            }
        });

        //Por defecto muestra el fragment de info
        bundleArgs = new Bundle();
        bundleArgs.putString("address", hModel.getAddress());
        bundleArgs.putString("telephone", String.valueOf(hModel.getTelephone()));
        bundleArgs.putString("website", hModel.getWebsite());
        infoHospitalFragment.setArguments(bundleArgs);
        setFragment(infoHospitalFragment);
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}
