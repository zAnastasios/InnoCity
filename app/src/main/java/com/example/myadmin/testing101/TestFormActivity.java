package com.example.myadmin.testing101;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestFormActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private Spinner spinner;
    private Button send;
    private EditText description;
    private int id;
    private double longitude;
    private double latitude;
    private double start;
    private double runTime;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_form);
        mContext = this;
        start = System.currentTimeMillis();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("t_forms");
        Intent intentThatStartedThisActivity = getIntent();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new SpinnerItemSelected());

    }
    public void addListenerOnButton() {
        spinner = (Spinner) findViewById(R.id.spinner);
        send = (Button) findViewById(R.id.send_btn);
        description = (EditText) findViewById(R.id.description);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        final String timestamp = dateFormat.format(date);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Random random = new Random();
                latitude = (180 * random.nextDouble()) - 90;
                longitude = (360 * random.nextDouble()) - 180;

                Random generator = new Random();
                int randomLength = generator.nextInt(Integer.MAX_VALUE);
                id = randomLength + generator.nextInt(Integer.MAX_VALUE);
                runTime = System.currentTimeMillis() - start;
                Form form = new Form( id, String.valueOf(spinner.getSelectedItem()), latitude, longitude,description.getText().toString(),  timestamp, runTime/1000.0);
                mMessagesDatabaseReference.push().setValue(form);

                Toast.makeText(getApplicationContext(), "Your report has been sent", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }

        });
    }

}
