package com.example.myadmin.testing101;


import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {

    private static final String EMAIL = "email";
    private EditText username;
    private EditText pwd;
    private Button login;
    private Button register;
    private TextView gotoRegister;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private FirebaseAuth firebaseAuth;
    MyViewModel model;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.emaillgn);
        pwd = (EditText) findViewById(R.id.pwdlgn);
        login = (Button) findViewById(R.id.login);
        //register = (Button) findViewById(R.id.MainRegister);
        gotoRegister=(TextView) findViewById(R.id.reg_text);

          model  = ViewModelProviders.of(this).get(MyViewModel.class);


        gotoRegister.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {

                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                return true;
            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        //listener gia sundesi
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        boolean flag1 = false;
                        //anazitisi user stin vasi dedomenon
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            User user = data.getValue(User.class);
                            String email = user.getEmail();
                            String password = user.getPassword();
                            if (email.equals(username.getText().toString()) && password.equals(pwd.getText().toString())) {
                                flag1 = true;

                                model.setUsername(username.getText().toString());
                                model.setEmail(email);
                                Context context = MainActivity.this;
                                CharSequence message = "Welcome " + user.getUsername();
                                Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                                toast.show();
                                Intent intent = new Intent(context, FormActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString(EMAIL, user.getUsername());
                                bundle.putString("username",username.getText().toString());
                                //kratame data kai ta pername se allo activity
                                if (bundle != null) {
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    break;
                                } else {
                                    startActivity(intent);
                                    break;
                                }
                            }
                        }

                        if (flag1 == false) {
                            CharSequence message = "Invalid credits";
                            Context context = MainActivity.this;
                            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                            toast.show();
                            username.setText("");
                            pwd.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
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
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();


    }

}
