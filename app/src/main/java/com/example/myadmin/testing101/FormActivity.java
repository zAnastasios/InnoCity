package com.example.myadmin.testing101;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FormActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DrawerLayout mDrawerLayout;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Context mContext;

    //antikeimeno ths prostheths klashs GPSTracker
    GPSTracker gps ;

    TextView text;
    private Spinner spinner;
    private Button send;
    private EditText description;
    private String user_email;
    private ImageButton picture;
    private double longitude;
    private double latitude;
    private String url;
    private int count_down,id;
    private int count_up;
    MyViewModel model;
    private String username_to_profile;
    private String email_to_profile;


    public FormActivity() {

    }
    //synartisi pou trexei kata thn dimiourgia tou activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        mContext = this;


        username_to_profile=getIntent().getExtras().getString("username");
        email_to_profile=getIntent().getExtras().getString("email");


        //layouts kai toolbar
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar1);


        model = ViewModelProviders.of(this).get(MyViewModel.class);
        String extras2 = model.getUsername(); //getIntent().getExtras().getString("email");

        toolbar.setTitle("Δημιουργίας αιτήματος");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
       actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //diaxeirisi ton antikeimenon tou navbar
        NavigationView navigationView = findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //highlight to epilegmeno antikeimeno
                        menuItem.setChecked(true);
                        Intent intent =null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_camera:
                                intent = new Intent(mContext, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(mContext, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(mContext,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(mContext,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(mContext,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(mContext,ProfileManager.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username",username_to_profile);
                                bundle.putString("email", email_to_profile);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case R.id.nav_slideshow:
                                intent=new Intent(mContext,MainActivity.class);
                                startActivity(intent);
                                break;
                        }


                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });


        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FormActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Toast.makeText(mContext,"Location permission needed.",Toast.LENGTH_LONG).show();
            gps = new GPSTracker(mContext, FormActivity.this);
            //check if GPS is enabled
            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {
                //Erotisi sto xristi na anoiksi tin topothesia
                //gt den mporese na ton entopisi meso diktyo i gps
                gps.showSettingsAlert();
            }
        }

        picture = (ImageButton) findViewById(R.id.picture);
        //sundesi me vasi kai dimiourgia instance gia diaxeirisi
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("forms");
        //storage
        Intent intentThatStartedThisActivity = getIntent();
        Bundle extras = intentThatStartedThisActivity.getExtras();
        user_email = extras.getString("email");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("photos/"+user_email+"_"+latitude+"_"+longitude);
        //listener gia to koumpi tis fotografias
        picture.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                }
            }

        });

        //paralaveis syntetagmenes
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    //epilogi anitkeimenou apo spinner
    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new SpinnerItemSelected());
    }

    //sunartisi gia listener koumpiou apostolis anaforas
    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);
        send = (Button) findViewById(R.id.send_btn);
        description = (EditText) findViewById(R.id.description) ;
        //dimiourgia format imerominias
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        final String timestamp = dateFormat.format(date);
        //kata to patima apostoli , pernei suntentagmenes,dimiourgei ena tuxaio id, kai kalei contructor formas
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    gps = new GPSTracker(mContext, FormActivity.this);
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random generator = new Random();
                int randomLength = generator.nextInt(Integer.MAX_VALUE);
                id = randomLength + generator.nextInt(Integer.MAX_VALUE);

                Form form = new Form(id,String.valueOf(spinner.getSelectedItem()),description.getText().toString(),user_email,latitude,longitude,timestamp,url,0,0);
                mMessagesDatabaseReference.push().setValue(form);

                Toast.makeText(getApplicationContext(), "Your report has been sent", Toast.LENGTH_LONG).show();



            }

        });
    }
    //epistrefei to epilegmeno antikeimeno apo to menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //sunartisi gia anevasma fotografias sto firebase
    //pername apo to activity tis cameras tin eikona , kai tin metatrepoyme se bytes
    //tin kanoume push kai kratame to link gia na to pername stin forma
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data1 = baos.toByteArray();
            Task<Uri> uploadTask = storageReference.putBytes(data1).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        url = downloadUri.toString();
                        Toast.makeText(FormActivity.this, url, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    //aithsh permission apo to xrhsth
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    gps = new GPSTracker(mContext, FormActivity.this);
                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
                    }

                }
                else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(mContext, "Location permission needed", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
