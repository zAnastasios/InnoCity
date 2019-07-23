package com.example.myadmin.testing101;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Voluntary_News extends AppCompatActivity implements ClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
  //  private ChildEventListener childEventListener;
    private ArrayList<VoluntaryWork> arrayList = new ArrayList<VoluntaryWork>();
   // private static final int NUM_LIST_ITEMS = 100;
    private DrawerLayout mDrawerLayout;

    public Voluntary_News(){}



    ///Travame ta data apo thn db kai ta kanoume add se ena arraylist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_valu_and_news);
        recyclerView = (RecyclerView) findViewById(R.id.vw_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Events");
        mDrawerLayout = findViewById(R.id.drawer_layout_VW);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        if(getIntent().getExtras().getString("category").equals("news"))
        {
            toolbar.setTitle("Ανακοινώσεις από τον δήμο");
        }
        else
        {
            toolbar.setTitle("Εθελοντικές δράσεις");
        }
       // toolbar.setTitle("den xerw ti mou shmvenei"); //TITLOS TOOLBAR
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //diaxeirisi ton antikeimenon tou navbar
        NavigationView navigationView = findViewById(R.id.nav_view1);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //highlight to epilegmeno antikeimeno
                        menuItem.setChecked(true);
                        Intent intent =null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_camera:
                                intent = new Intent(Voluntary_News.this, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(Voluntary_News.this, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(Voluntary_News.this,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(Voluntary_News.this,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(Voluntary_News.this,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(Voluntary_News.this,ProfileManager.class);
                                startActivity(intent);
                                break;

                            case R.id.nav_slideshow:
                                intent=new Intent(Voluntary_News.this,MainActivity.class);
                                startActivity(intent);
                                break;
                        }
//                        if(menuItem.getItemId() == R.id.nav_camera){
//                             intent = new Intent(mContext, Map_reports.class);
//                        }else if (menuItem.getItemId() == R.id.nav_gallery){
//                             intent = new Intent(mContext, ListForm.class);
//                        }else{
//                            intent = new Intent(mContext, StartActivity.class);
//                            finish();
//                        }
                        //ekkinisi activity analoga tin epilogi kai
                        //klisimo toy drawer

                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });


        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren())
                {

                    VoluntaryWork volwork = data.getValue(VoluntaryWork.class);
                   // String s1="eee";
                    if(volwork.getDescription().equals(getIntent().getExtras().getString("category"))) {
                        arrayList.add(volwork);
                    }
                }
                recyclerView.setHasFixedSize(true);
                mAdapter = new VoluAdapter(arrayList);
                recyclerView.setAdapter(mAdapter);
              //  arrayList.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public  void itemClicked(View v, int position)
    {

        Intent intent = new Intent(Voluntary_News.this,Volu_News_More_Info.class);
        intent.putExtra("Volu_work_clicked",arrayList.get(position));
        startActivity(intent);

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
