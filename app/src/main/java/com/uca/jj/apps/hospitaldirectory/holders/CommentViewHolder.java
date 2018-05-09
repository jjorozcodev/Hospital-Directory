package com.uca.jj.apps.hospitaldirectory.holders;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.uca.jj.apps.hospitaldirectory.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    TextView name, text, date, email;

    ImageView imgEdit;

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public CommentViewHolder(final View itemView) {

        super(itemView);
        name = (TextView) itemView.findViewById(R.id.userComment);
        text = (TextView) itemView.findViewById(R.id.textComment);
        date = (TextView) itemView.findViewById(R.id.dateComment);
        email = (TextView) itemView.findViewById(R.id.emailComment);

        imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
