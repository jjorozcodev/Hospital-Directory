package com.uca.jj.apps.hospitaldirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.uca.jj.apps.hospitaldirectory.holders.HospitalViewHolder;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalViewHolder> {
    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HospitalViewHolder holder, int position) {

        //Ya que el ViewHolder está en otra clase, se accede con el método get...
        holder.getNameHospital().setText("example");
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
