package com.example.myadmin.testing101;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProfileManager extends AppCompatActivity  {

    private DrawerLayout mDrawerLayout;
    private TextView username;
    private TextView Email;
    MyViewModel model;
    private TextView positive;
    private TextView negative;
    private TextView eligable;
    PopupWindow popUp;
    private RelativeLayout rel;
    private LinearLayout layout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        popUp = new PopupWindow(this);


        mDrawerLayout = findViewById(R.id.drawer_layout_PP);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        username=findViewById(R.id.username_textview_holder);
        Email=findViewById(R.id.email_real);
        model = ViewModelProviders.of(this).get(MyViewModel.class);
        positive=findViewById(R.id.positive_number);
        negative=findViewById(R.id.negative_number);
        rel=findViewById(R.id.main_relative_view);


            Email.setText(getIntent().getExtras().getString("username"));
            username.setText(getIntent().getExtras().getString("email"));

        toolbar.setTitle("Το προφίλ μου");
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
                                intent = new Intent(ProfileManager.this, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(ProfileManager.this, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(ProfileManager.this,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(ProfileManager.this,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(ProfileManager.this,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(ProfileManager.this,ProfileManager.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_slideshow:
                                intent=new Intent(ProfileManager.this,MainActivity.class);
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
