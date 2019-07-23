package com.example.myadmin.testing101;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class QnA_MoreInfo extends AppCompatActivity {

    private TextView InfoView;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_answers);
        //mContext = this;
        //layouts kai toolbar
        mDrawerLayout = findViewById(R.id.drawer_layout_QnAMoreInfo);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        String extras2 = getIntent().getExtras().getString("Category");
        toolbar.setTitle(extras2);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //diaxeirisi ton antikeimenon tou navbar
        InfoView=findViewById(R.id.information);
        InfoView.setText(TextSetter(extras2));
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
                                intent = new Intent(QnA_MoreInfo.this, Map_reports.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_gallery:
                                intent = new Intent(QnA_MoreInfo.this, ListForm.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_QnA:
                                intent=new Intent(QnA_MoreInfo.this,QnA.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_News:
                                intent=new Intent(QnA_MoreInfo.this,Voluntary_News.class);
                                intent.putExtra("category", "news");
                                startActivity(intent);
                                break;
                            case R.id.nav_Volu:
                                intent=new Intent(QnA_MoreInfo.this,Voluntary_News.class);
                                intent.putExtra("category", "volu");
                                startActivity(intent);
                                break;
                            case R.id.nav_profile:
                                intent=new Intent(QnA_MoreInfo.this,ProfileManager.class);
                                startActivity(intent);
                                break;

                            case R.id.nav_slideshow:
                                intent=new Intent(QnA_MoreInfo.this,MainActivity.class);
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

    public String TextSetter (String MenuChoice)
    {
        switch (MenuChoice) {
            case "1. Πιστοποιητικό οικογενειακής κατάστασης":
                return "Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                        "Αίτηση χορήγησης πιστοποιητικού οικογενειακής κατάστασης  (pdf, doc)\n";
            case "2. Πιστοποιητικό γέννησης":
                return "Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                        "Αίτηση του ενδιαφερόμενου (pdf, doc)\n";
            case  "3. Πιστοποιητικό εντοπιότητας" :
                return "Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                        "Αίτηση του ενδιαφερόμενου (pdf, doc) \n" +
                        "Πρόσφατο Αποδεικτικό Κατοικίας (Λογαριασμός ΔΕΗ, Τηλεφωνίας κ.λπ.) ή Υπεύθυνη Δήλωση\n";
            case "4. Αίτηση μεταδημότευσης":
                return "Λόγω Γάμου:\n" +
                            "   Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                            "   Αίτηση (pdf,doc)\n" +
                            "   Αντίγραφο Ληξιαρχικής Πράξης Γάμου\n" +
                            "   Αντίγραφο Ληξιαρχικής Πράξης Γέννησης\n" +
                            "   Πιστοποιητικό Οικογενειακής Κατάστασης για Μεταδημότευση στο Δήμο Χίου\n" +
                        "   Λόγω Διετούς Κατοικίας:\n" +
                            "   Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                            "   Αίτηση του ενδιαφερόμενου (pdf, doc)\n" +
                            "   Βεβαίωση Μόνιμης Κατοικίας (εκδίδεται από   το Πρωτόκολλο του Δήμου Χίου)\n" +
                        "Λόγω Αρχικής Δημοτικότητας:\n" +
                            "   Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                            "   Αίτηση του ενδιαφερόμενου (pdf, doc)\n" +
                            "   Πιστοποιητικό Οικογενειακής Κατάστασης για Μεταδημότευση στο Δήμο Χίου\n" +
                            "   Υπεύθυνη Δήλωση\n";
            case "5. Διαγραφή λόγω διαζυγίου":
                return "    Φωτοαντίγραφο Αστυνομικής Ταυτότητας\n" +
                            "Αίτηση (pdf, doc)\n" +
                            "Ληξιαρχική πράξη γάμου με στοιχεία διαζυγίου\n" +
                            "Διαζευκτήριο\n";
            case "6. Αλλαγή εκλογικού διαμερίσματος για δημότες Χίου":
                return  "Φωτοαντίγραφο Αστυνομικής Ταυτότητας \n" +
                        "Αίτηση του ενδιαφερόμενου (pdf, doc)\n" +
                        "Βεβαίωση Μόνιμης Κατοικίας (εκδίδεται από το Πρωτόκολλο του Δήμου Χίου)\n";

            case "1.  Ληξιαρχική πράξη γέννησης":
                return "Αίτηση ενδιαφερόμενου";
            case "2.  Ληξιαρχική πρήξη θανάτου":
                return "Αίτηση για ληξιαρχική πράξη θανάτου";
            case "3.  Ληξιαρχική πράξη γάμου":
                return "Αίτηση για ληξιαρχική πράξη γάμου";
            case "4.  Ληξιαρχική πράξη υιοθεσίας" :
                return "Αίτηση (pdf, doc)\n" +
                        "Αίτηση (pdf, doc)\n" +
                        "Απόφαση δικαστηρίου με το πιστοποιητικό τελεσιδικίας αυτής.\n" +
                        "Η δήλωση υιοθεσίας γίνεται παρουσία και των δύο γονέων με τις ταυτότητες τους στο ληξιαρχείο του τόπου μονίμου κατοικίας των γονέων.\n";
            case "5.  Δήλωση πράξη ονοματοδοσίας":
                return "Αίτηση (pdf, doc)\n" +
                        "Η ονοματοδοσία γίνεται παρουσία υποχρεωτικά και των δύο γονέων με τις ταυτότητες τους:\n" +
                        "στο ληξιαρχείο που έχει δηλωθεί η γέννηση ή\n" +
                        "στο ληξιαρχείο που κατοικούν οι γονείς εάν το παιδί έχει δηλωθεί σε άλλο ληξιαρχείο προσκομίζοντας ληξιαρχική πράξη γέννησης του\n";

            case "6.  Δήλωση γέννησης" :
                return "Ιατρικό Πιστοποιητικό γέννησης (κλινικής ή νοσοκομείου)\n" +
                        "Αστυνομικές ταυτότητες των γονέων\n" +
                        "Ληξιαρχική πράξη γάμου με πράξη προσδιορισμού επωνύμου τέκνων.\n" +
                        "Η γέννηση δηλώνεται στο Ληξιαρχείο του Δήμου που ανήκει το Μαιευτήριο από τον πατέρα ή την μητέρα ή τον ιατρό ή την μαία που παρεβρέθει στον τοκετό εντός 10 ημερών από την ημερομηνία γέννησης.\n" +
                        "•\tΕάν έχουν παρέλθει 10 ημέρες έως 3 μήνες μετά την γέννηση πρέπει να προσκομιστεί και παράβολο (πρόστιμο) 4,5€.\n" +
                        "•\tΕάν έχουν παρέλθει πάνω από 3 μήνες μετά τη γέννηση πρέπει να προσκομιστεί και παράβολο (πρόστιμο) 13,5€.\n" +
                        "•\tΟι αλλοδαποί, χρειάζεται να προσκομίσουν δικαιολογητικά νόμιμης παραμονής\n";
            case "7.  Δήλωση βάπτισης":
                return " Η δήλωση τέλεσης βάπτισης του ιερέα, υπογεγραμμένη και από τους δύο γονείς.\n" +
                        "Η βάφτιση δηλώνεται στον τόπο δήλωσης της γεννήσεως του τέκνου ή στον τόπο τέλεσης του μυστηρίου εντός 90 ημερών από την ημερομηνία τέλεσης.\n" +
                        "•\tΕάν έχουν παρέλθει 90 ημέρες έως 6 μήνες μετά την τέλεση πρέπει να προσκομιστεί και παράβολο χαρτοσήμου 4,5€.\n" +
                        "•\tΕάν έχουν παρέλθει πάνω από 6 μήνες μετά τέλεση πρέπει να προσκομιστεί και παράβολο χαρτοσήμου 13,5€.\n" ;

            case "8.  Δήλωση γάμου":
                return "Δήλωση τέλεσης γάμου από ιερέα ή δήμαρχο (σε περίπτωση πολιτικού γάμου)\n" +
                        "Πράξη προσδιορισμού επωνύμου τέκνου\n" +
                        "Αστυνομικές ταυτότητες του ζεύγους\n" +
                        "Αίτηση δήλωσης γάμου\n" +
                        "Ο γάμος δηλώνεται μόνο στον τόπο τέλεσης από τον ένα εκ των δύο συζύγων ή τρίτο πρόσωπο (με ειδικό συμβολαιογραφικό πληρεξούσιο) εντός 40 ημερών από την ημερομηνία τέλεσης.\n" +
                        "•\tΕάν έχουν παρέλθει 40 ημέρες έως 3 μήνες μετά την τέλεση πρέπει να προσκομιστεί και παράβολο (πρόστιμο) 4,5€.\n" +
                        "•\tΕάν έχουν παρέλθει πάνω από 3 μήνες μετά τέλεση πρέπει να προσκομιστεί και παράβολο (πρόστιμο) 13,5€. \n";
            case "9.  Δήλωση διαζυγίου":
                return "Απόφαση δικαστηρίου πιστοποιητικό τελεσιδικίας και το διαζευκτήριο της αρχιεπισκοπής επικυρωμένα (για θρησκευτικό γάμο)\n" +
                        "Απόφαση δικαστηρίου και πιστοποιητικό τελεσιδικίας πρωτοδικείου επικυρωμένα (για πολιτικό γάμο)\n" +
                        "Ληξιαρχική πράξη γάμου, (όταν ο γάμος δεν έχει δηλωθεί στο Ληξιαρχείο Χίου και η δικαστική απόφαση έχει εκδοθεί από το δικαστήριο Χίου)\n" +
                        "Αίτηση δήλωσης διαζυγίου\n" +
                        "Η δήλωση γίνεται από ένα εκ των δύο διαζευγμένων με την ταυτότητά του, εντός 30 ημερών από την ημερομηνία τελεσιδικίας της απόφασης.\n" +
                        "•\tΕάν έχουν παρέλθει 1-3 μήνες μετά την έκδοσης της απόφασης πρέπει να προσκομιστεί και παράβολο (πρόστιμο) 4,5€.\n" +
                        "•\tΕάν έχουν παρέλθει πάνω από 3 μήνες μετά την έκδοσης της απόφασης πρέπει να προσκομιστεί και παράβολο (πρόστιμο) 13,5€. \n";
            case "10. Δήλωση θανάτου":
                return "Το ιατρικό πιστοποιητικό θανάτου συμπληρωμένο από το νοσοκομείο ή ιδιώτη ιατρό\n" +
                        "Αστυνομική ταυτότητα του θανόντος\n" +
                        "Αστυνομική ταυτότητα δηλούντος\n" +
                        "Αίτηση για δήλωση θανάτου\n" +
                        "Για τη δήλωση και έκδοση άδειας ταφής υπάρχει προθεσμία 24 ωρών πριν την ταφή.\n" +
                        "Εάν έχουν παρέλθει 24 ώρες μετά την ταφή και δεν έχει δηλωθεί ο θάνατος στο ληξιαρχείο αυτός δηλώνεται μονάχα με παραγγελία εισαγγελέα.\n";
                default :
                    return "Sorry something went wrong. Please try again later.";

        }
            //NA TO ALLAXWWWWWWWWWWWWWWWWWWWWWWWWWWWW

    }



}
