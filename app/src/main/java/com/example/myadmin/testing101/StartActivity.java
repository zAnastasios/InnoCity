package com.example.myadmin.testing101;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button usr_exp;
    private Button complete;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        usr_exp = (Button) findViewById(R.id.button);
        complete = (Button) findViewById(R.id.button2);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = StartActivity.this;
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        usr_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = StartActivity.this;
                Intent intent = new Intent(context, TestFormActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StartActivity.super.onBackPressed();
                    }
                }).create().show();


    }

}

