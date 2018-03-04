package com.uca.jj.apps.hospitaldirectory.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uca.jj.apps.hospitaldirectory.R;

public class HospitalViewHolder extends RecyclerView.ViewHolder{

    ImageView imgItem;
    TextView nameHospital;
    TextView telephone;

    public HospitalViewHolder(View itemView) {
        super(itemView);

        imgItem = (ImageView) itemView.findViewById(R.id.imgItem);
        nameHospital = (TextView) itemView.findViewById(R.id.nameHospital);
        telephone = (TextView) itemView.findViewById(R.id.telephone);
    }

    public ImageView getImgItem() {
        return imgItem;
    }

    public void setImgItem(ImageView imgItem) {
        this.imgItem = imgItem;
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
