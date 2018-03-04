package com.uca.jj.apps.hospitaldirectory.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.uca.jj.apps.hospitaldirectory.R;

public class HospitalViewHolder extends RecyclerView.ViewHolder{

    SimpleDraweeView imgHospital;
    TextView nameHospital;
    TextView telephone;

    ImageView imgCall;

    public HospitalViewHolder(final View itemView) {
        super(itemView);

        imgHospital = (SimpleDraweeView) itemView.findViewById(R.id.imgHospital);
        telephone = (TextView) itemView.findViewById(R.id.telephone);

        nameHospital = (TextView) itemView.findViewById(R.id.nameHospital);
        imgCall = (ImageView) itemView.findViewById(R.id.imgCall);

        nameHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Mostrar detalle de "+nameHospital.getText(), Toast.LENGTH_LONG).show();
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Llamando a "+telephone.getText(), Toast.LENGTH_LONG).show();
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
