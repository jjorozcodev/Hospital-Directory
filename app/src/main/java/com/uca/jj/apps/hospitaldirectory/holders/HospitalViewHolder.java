package com.uca.jj.apps.hospitaldirectory.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.uca.jj.apps.hospitaldirectory.R;

public class HospitalViewHolder extends RecyclerView.ViewHolder{

    SimpleDraweeView imgHospital;
    TextView nameHospital;
    TextView telephone;

    public HospitalViewHolder(View itemView) {
        super(itemView);

        imgHospital = (SimpleDraweeView) itemView.findViewById(R.id.imgHospital);
        nameHospital = (TextView) itemView.findViewById(R.id.nameHospital);
        telephone = (TextView) itemView.findViewById(R.id.telefono);
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
