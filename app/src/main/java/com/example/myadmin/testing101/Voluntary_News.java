package com.example.myadmin.testing101;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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
    private ArrayList<VoluntaryWork> arrayList = new ArrayList<VoluntaryWork>();
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
        //Setting toolbar and NavView
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
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

                    if(volwork.getDescription().equals(getIntent().getExtras().getString("category"))) {
                        arrayList.add(volwork);
                    }
                }
                recyclerView.setHasFixedSize(true);
                mAdapter = new VoluAdapter(arrayList);
                recyclerView.setAdapter(mAdapter);

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
