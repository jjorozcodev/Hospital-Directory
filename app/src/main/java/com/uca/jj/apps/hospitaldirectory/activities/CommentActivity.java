package com.uca.jj.apps.hospitaldirectory.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.uca.jj.apps.hospitaldirectory.R;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Button button = findViewById(R.id.btn_comment);

    }
}
