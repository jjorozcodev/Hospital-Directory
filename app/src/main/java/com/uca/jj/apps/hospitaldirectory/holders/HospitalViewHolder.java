package com.uca.jj.apps.hospitaldirectory.holders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.activities.DetailHospital;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

public class HospitalViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView imgHospital;
    TextView nameHospital;
    TextView telephone;
    Button btnCall;

    public HospitalViewHolder(final View itemView, final HospitalModel hospitalModel) {
        super(itemView);

        imgHospital = (SimpleDraweeView) itemView.findViewById(R.id.imgHospital);
        telephone = (TextView) itemView.findViewById(R.id.telephone);

        nameHospital = (TextView) itemView.findViewById(R.id.nameHospital);
        btnCall = (Button) itemView.findViewById(R.id.btnCall);

        imgHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(itemView.getContext(), DetailHospital.class);

                i.putExtra("id", hospitalModel.getId());
                i.putExtra("name", hospitalModel.getName());
                i.putExtra("slogan", hospitalModel.getSlogan());
                i.putExtra("description", hospitalModel.getDescription());
                i.putExtra("telephone", hospitalModel.getTelephone());
                i.putExtra("address", hospitalModel.getAddress());
                i.putExtra("website", hospitalModel.getWebsite());
                i.putExtra("latitude", hospitalModel.getLatitude());
                i.putExtra("length", hospitalModel.getLength());
                i.putExtra("urlImgSmall", hospitalModel.getUrlImgSmall());
                i.putExtra("urlImgLarge", hospitalModel.getUrlImgLarge());

                itemView.getContext().startActivity(i);
            }
        });
        nameHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(itemView.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public SimpleDraweeView getImgHospital() {
        return imgHospital;
    }

    public void setImgHospital(SimpleDraweeView imgHospital) {
        this.imgHospital = imgHospital;
    }

    public TextView getNameHospital() {
        return nameHospital;
    }

    public void setNameHospital(TextView nameHospital) {
        this.nameHospital = nameHospital;
    }

    public TextView getTelephone() {
        return telephone;
    }

    public void setTelephone(TextView telephone) {
        this.telephone = telephone;
    }

}