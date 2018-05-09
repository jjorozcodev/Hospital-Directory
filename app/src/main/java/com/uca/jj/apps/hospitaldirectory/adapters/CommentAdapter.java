package com.uca.jj.apps.hospitaldirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.holders.CommentViewHolder;
import com.uca.jj.apps.hospitaldirectory.models.AuthorModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
        AuthorModel aModel = getAuthor(comments.get(position).getAuthorId());

        holder.getName().setText(aModel.getName() + " " + aModel.getLastname());
        holder.getText().setText(comments.get(position).getMessage());
        holder.getDate().setText(comments.get(position).getCreatedDt());
        holder.getEmail().setText("<" + aModel.getEmail() + ">");
    }

    private AuthorModel getAuthor(int id){
        AuthorModel a = new AuthorModel();
        for (AuthorModel authorModel : getAuthorsFromDB()){
            if(authorModel.getId()==id){
                a = authorModel;
            }
        }

        return a;
    }

    private ArrayList<AuthorModel> getAuthorsFromDB() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<AuthorModel> query = realm.where(AuthorModel.class);

        RealmResults<AuthorModel> results = query.findAll();

        ArrayList<AuthorModel> cData = new ArrayList<>();
        cData.addAll(realm.copyFromRealm(results));

        return cData;

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

