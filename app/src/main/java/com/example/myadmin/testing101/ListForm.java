package com.example.myadmin.testing101;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListForm extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener childEventListener;
    private ArrayList<Object> arrayList = new ArrayList<Object>();
    private static final int NUM_LIST_ITEMS = 100;
    private DrawerLayout mDrawerLayout;

    //ensomatosi recycle view sto activity
    //kai listas mesa se auto.
    ///Travame ta data apo thn db kai ta kanoume add se ena arraylist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_form);
        recyclerView = (RecyclerView) findViewById(R.id.rview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("forms");
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("Προβολή σε λίστα"); //TITLOS TOOLBAR
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
                                intent = new Intent(ListForm.this, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(ListForm.this, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(ListForm.this,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(ListForm.this,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(ListForm.this,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(ListForm.this,ProfileManager.class);
                                startActivity(intent);
                                break;

                            case R.id.nav_slideshow:
                                intent=new Intent(ListForm.this,MainActivity.class);
                                startActivity(intent);
                                break;
                        }


                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });






        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren())
                {
                    Form form = data.getValue(Form.class);
                    arrayList.add(form);
                }
                recyclerView.setHasFixedSize(true);
                mAdapter = new ListAdapter(arrayList);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
