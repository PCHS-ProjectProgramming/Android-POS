package net.a6te.lazycoder.andropos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import net.a6te.lazycoder.andropos.fragments.DueDetailsFragment;
import net.a6te.lazycoder.andropos.fragments.DueFragment;
import net.a6te.lazycoder.andropos.fragments.InvoiceFragment;
import net.a6te.lazycoder.andropos.fragments.ReportFragment;
import net.a6te.lazycoder.andropos.fragments.SellsFragment;
import net.a6te.lazycoder.andropos.fragments.StockFragment;
import net.a6te.lazycoder.andropos.fragments.UpdatingStatusFragment;
import net.a6te.lazycoder.andropos.interfaces.DueLvInterface;

/*

* developer : syed ashraf ullah
* email : syedashrafullah15@gmail.com
* web : lazycoder.6te.net

*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener, DueLvInterface {

    public static final String TAG = "AndroPos";
    private int updateComplete = 0;
    private RelativeLayout stockRl;
    private RelativeLayout sellsRl;
    private RelativeLayout invoiceRl;
    private RelativeLayout dueRl;
    private RelativeLayout reportRl;
    private ImageButton updateBtn;

    private UpdateDatabase updateDatabase;

    private Fragment fragment;
    private FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initializeAll();
        fragmentTransaction.add(R.id.container,fragment);
        fragmentTransaction.commit();

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver
                ,new IntentFilter("completationMessage"));

        mobileAdsInitialize();

    }

    private void mobileAdsInitialize() {
        MobileAds.initialize(this,
                getResources().getString(R.string.app_add_id));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void initializeAll() {
        stockRl = (RelativeLayout) findViewById(R.id.navStock);
        sellsRl = (RelativeLayout) findViewById(R.id.navSells);
        invoiceRl = (RelativeLayout) findViewById(R.id.navInvoice);
        dueRl = (RelativeLayout) findViewById(R.id.navDue);
        reportRl = (RelativeLayout) findViewById(R.id.navReport);
        updateBtn = (ImageButton) findViewById(R.id.btnUpdate);

        stockRl.setOnClickListener(this);
        sellsRl.setOnClickListener(this);
        invoiceRl.setOnClickListener(this);
        dueRl.setOnClickListener(this);
        reportRl.setOnClickListener(this);
        updateBtn.setOnClickListener(this);

        fragment = new StockFragment();
        updateDatabase = new UpdateDatabase(MainActivity.this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navStock:
                fragment = new StockFragment();
                closeNavBar();
                break;
            case R.id.navSells:
                fragment = new SellsFragment();
                closeNavBar();
                break;
            case R.id.navInvoice:
                fragment = new InvoiceFragment();
                closeNavBar();
                break;
            case R.id.navDue:
                fragment = new DueFragment();
                closeNavBar();
                break;
            case R.id.navReport:
                fragment = new ReportFragment();
                closeNavBar();
                break;
            case R.id.btnUpdate:
                updateData();
                break;
        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();//need to re initialize it otherwise it will show already commited
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

    public void closeNavBar(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Are you sure you want exit?");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            dialog.setNegativeButton("No",null);

            dialog.show();
        }
    }

    @Override
    public void dueDetails(String invoiceNumber) {
        fragment = new DueDetailsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("dueId",invoiceNumber);
        fragment.setArguments(bundle);
        transaction.replace(R.id.container,fragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void updateData(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        updateBtn.setClickable(false);
        fragment = new UpdatingStatusFragment();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();

        updateDatabase.updateData();
    }

    Thread thread;
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            boolean status = intent.getBooleanExtra("status",true);
//            if (status){
//                updateBtn.setClickable(true);
//                fragment = new StockFragment();
//                fragmentTransaction.replace(R.id.container,fragment);
//                fragmentTransaction.commit();
//
//                return;
//            }

            int percent = intent.getIntExtra("percentage",0);
            updateComplete += percent;


            try {
                if (updateComplete == 100 || updateComplete > 100){

//                    thread = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//
//                            }
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    updateBtn.setClickable(true);
//                                    fragment = new StockFragment();
//                                    fragmentTransaction.replace(R.id.container,fragment);
//                                    fragmentTransaction.commit();
//                                    thread.interrupt();
//                                }
//                            });
//                        }
//                    });
//
//
//                    thread.start();

                    updateBtn.setClickable(true);
                    fragment = new StockFragment();
                    fragmentTransaction.replace(R.id.container,fragment);
                    fragmentTransaction.commit();
                    thread.interrupt();

                }
            }catch (Exception e){

            }

        }
    };


}
