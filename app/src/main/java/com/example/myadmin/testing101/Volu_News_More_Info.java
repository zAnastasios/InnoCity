package com.example.myadmin.testing101;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Volu_News_More_Info extends AppCompatActivity {

    private TextView ShortDesc;
    private TextView Date;
    private TextView BigDesc;
    private ImageView Pic_Url;
    private CardView More_info_card_view;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info_news);
        More_info_card_view=(CardView) findViewById(R.id.more_info_card_view);
        ShortDesc=(TextView) findViewById(R.id.volu_short_desc_more_info);
        Date=(TextView) findViewById(R.id.volu_date_more_info);
        Pic_Url=(ImageView) findViewById(R.id.volu_image_more_info);
        BigDesc=(TextView) findViewById(R.id.big_desc_more_info);
        mDrawerLayout = findViewById(R.id.drawer_layout_more_info_news);


        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("Περισσότερες πληροφορίες"); //TITLOS TOOLBAR
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //parcing the data from the intent to the activity components
        Intent getInfo=getIntent();
        VoluntaryWork vol_work_clicked=(VoluntaryWork) getInfo.getParcelableExtra("Volu_work_clicked");
        ShortDesc.setText(vol_work_clicked.getShortDesc());
        Glide.with(Volu_News_More_Info.this.Pic_Url.getContext())
                .load(vol_work_clicked.getDate())
                .into(Pic_Url);
        BigDesc.setText(vol_work_clicked.getBigDesc());
        Date.setText(vol_work_clicked.getPic_Url());

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
                                intent = new Intent(Volu_News_More_Info.this, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(Volu_News_More_Info.this, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(Volu_News_More_Info.this,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(Volu_News_More_Info.this,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(Volu_News_More_Info.this,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(Volu_News_More_Info.this,ProfileManager.class);
                                startActivity(intent);
                                break;

                            case R.id.nav_slideshow:
                                intent=new Intent(Volu_News_More_Info.this,MainActivity.class);
                                startActivity(intent);
                                break;
                        }


                        mDrawerLayout.closeDrawers();
                        return true;
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
