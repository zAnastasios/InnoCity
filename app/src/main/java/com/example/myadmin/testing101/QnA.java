package com.example.myadmin.testing101;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QnA extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public QnA() {}

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.QnAListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                Intent toMoreInfo = new Intent(QnA.this,QnA_MoreInfo.class);
                toMoreInfo.putExtra("Category",listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition));
                startActivity(toMoreInfo);



                return false;
            }
        });



        mDrawerLayout= findViewById(R.id.drawer_layout_QnA);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("Συχνές Ερωτήσεις");
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
                                intent = new Intent(QnA.this, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(QnA.this, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(QnA.this,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(QnA.this,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(QnA.this,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(QnA.this,ProfileManager.class);
                                startActivity(intent);
                                break;

                            case R.id.nav_slideshow:
                                intent=new Intent(QnA.this,MainActivity.class);
                                startActivity(intent);
                                break;
                        }


                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });



}



    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Υπηρεσίες δημοτικής κατάστασης");
        listDataHeader.add("Υπηρεσίες ληξιαρχείου");
      //  listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> ListDhmotikhs = new ArrayList<String>();
        ListDhmotikhs.add("1. Πιστοποιητικό οικογενειακής κατάστασης");
        ListDhmotikhs.add("2. Πιστοποιητικό γέννησης");
        ListDhmotikhs.add("3. Πιστοποιητικό εντοπιότητας");
        ListDhmotikhs.add("4. Αίτηση μεταδημότευσης");
        ListDhmotikhs.add("5. Διαγραφή λόγω διαζυγίου");
        ListDhmotikhs.add("6. Αλλαγή εκλογικού διαμερίσματος για δημότες Χίου");


        List<String> listLixiarxeiou = new ArrayList<String>();
        listLixiarxeiou.add("1.  Ληξιαρχική πράξη γέννησης");
        listLixiarxeiou.add("2.  Ληξιαρχική πρήξη θανάτου");
        listLixiarxeiou.add("3.  Ληξιαρχική πράξη γάμου");
        listLixiarxeiou.add("4.  Ληξιαρχική πράξη υιοθεσίας");
        listLixiarxeiou.add("5.  Δήλωση πράξη ονοματοδοσίας");
        listLixiarxeiou.add("6.  Δήλωση γέννησης");
        listLixiarxeiou.add("7.  Δήλωση βάπτισης");
        listLixiarxeiou.add("8.  Δήλωση γάμου");
        listLixiarxeiou.add("9.  Δήλωση διαζυγίου");
        listLixiarxeiou.add("10. Δήλωση θανάτου");



        listDataChild.put(listDataHeader.get(0), ListDhmotikhs);
        listDataChild.put(listDataHeader.get(1), listLixiarxeiou);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return QnA.super.onOptionsItemSelected(item);
    }



}