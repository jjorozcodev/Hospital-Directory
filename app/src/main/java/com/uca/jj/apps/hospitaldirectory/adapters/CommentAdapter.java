package com.uca.jj.apps.hospitaldirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.holders.CommentViewHolder;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    ArrayList <CommentModel> comments;

    public CommentAdapter(ArrayList<CommentModel> listComments) {
        this.comments = listComments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Override method...
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        String usuario = (comments.get(position).getAuthorId()==1)?"Juan Orozco":"Daryin Francela";
        String email = (comments.get(position).getAuthorId()==1)?"juan@orozco.com.ni":"daryin@perez.es";

        holder.getName().setText(usuario);
        holder.getText().setText(comments.get(position).getMessage());
        holder.getDate().setText(comments.get(position).getCreatedDt());
        holder.getEmail().setText(email);
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return position;
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}

