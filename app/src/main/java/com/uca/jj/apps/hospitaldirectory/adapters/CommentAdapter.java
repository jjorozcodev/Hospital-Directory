package com.uca.jj.apps.hospitaldirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.uca.jj.apps.hospitaldirectory.R;
import com.uca.jj.apps.hospitaldirectory.api.Rest;
import com.uca.jj.apps.hospitaldirectory.holders.CommentViewHolder;
import com.uca.jj.apps.hospitaldirectory.models.AuthorModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.internal.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(CommentViewHolder holder, final int position) {
        AuthorModel aModel = getAuthor(comments.get(position).getAuthorId());

        holder.getName().setText(aModel.getName() + " " + aModel.getLastname());
        holder.getText().setText(comments.get(position).getMessage());
        holder.getDate().setText(comments.get(position).getCreatedDt());
        holder.getEmail().setText("<" + aModel.getEmail() + ">");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new MaterialDialog.Builder(view.getContext())
                        .content("¿Desea eliminar éste comentario?")
                        .positiveText("Eliminar")
                        .negativeText("Cancelar")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                removeComment(comments.get(position));
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    private void removeComment(CommentModel commentModel){
        deleteCommentFromAPI(commentModel.getId());
        deleteCommentFromDB(commentModel);
        comments.remove(commentModel);
        notifyDataSetChanged();
    }

    private void deleteCommentFromAPI(final int id){
        Call<CommentModel> call = Rest.instance().deleteComment(id);
        call.enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                Log.i("ELIMINADO", "Registro con id> " + String.valueOf(id));
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable t) {

            }
        });
    }

    private void deleteCommentFromDB(CommentModel commentModel){
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<CommentModel> query = realm.where(CommentModel.class);

        RealmResults<CommentModel> results = query.findAll();

        for(int i=0; i<results.size(); i++){
            if(results.get(i).getId()==commentModel.getId()){
                removeCommentFromDB(results.get(i));
            }

        }

    }

    private void removeCommentFromDB(CommentModel comment){

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        comment.deleteFromRealm();
        realm.commitTransaction();
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

