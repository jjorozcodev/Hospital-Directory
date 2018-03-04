package com.uca.jj.apps.hospitaldirectory.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.holders.HospitalViewHolder;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalViewHolder> {

    ArrayList <HospitalModel> hospitals;

    public HospitalAdapter(ArrayList<HospitalModel> detailHospital) {
        this.hospitals = detailHospital;
    }

    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital_directory, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HospitalViewHolder holder, int position) {
        String url = hospitals.get(position).getUrlImg();
        Uri uri = Uri.parse(url);

        holder.getImgHospital().setImageURI(uri);
        holder.getNameHospital().setText(hospitals.get(position).getName());
        holder.getTelephone().setText(String.valueOf(hospitals.get(position).getTelephone()));
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }
}
