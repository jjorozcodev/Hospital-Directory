package com.uca.jj.apps.hospitaldirectory.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uca.jj.apps.hospitaldirectory.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    TextView name, text, date, email;

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
    }


}
