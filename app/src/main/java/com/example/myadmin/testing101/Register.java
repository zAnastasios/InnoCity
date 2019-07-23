package com.example.myadmin.testing101;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private Button registerbtn;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.pwd);
        registerbtn = (Button) findViewById(R.id.register);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("users");
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(username.getText().toString(),email.getText().toString(),password.getText().toString());
                mMessagesDatabaseReference.push().setValue(user);
                Context context = Register.this;
                CharSequence message = "Registered successfully";
                Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
