package com.iut.beraad.beraad;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Adrien on 18/03/2017.
 */

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private TextView navUsername;
    private TextView navEmail;
    private CircleImageView navImg;
    private View headerView;

    public Personne personne;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.raphaelbretzner.facebookconnect",  // replace with your unique package name
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                System.out.println("KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("ERRRRRROR");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


////        Fragment fragment=null;
////        Class fragmentClass = AccueilEventsFragment.class;
////
////        try {
////         fragment = (Fragment) fragmentClass.newInstance();
////        } catch (InstantiationException e) {
////            e.printStackTrace();
////        } catch (IllegalAccessException e) {
////            e.printStackTrace();
////        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        mDrawer.addDrawerListener(drawerToggle);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.tv_prenom_nom);
        navUsername.setText(PersonneConnecte.getPersonneConnecte().getPrenom()+" "+PersonneConnecte.getPersonneConnecte().getNom());
        navEmail = (TextView) headerView.findViewById(R.id.tv_email);
        navEmail.setText(PersonneConnecte.getPersonneConnecte().getEmail());

        navImg = (CircleImageView) headerView.findViewById(R.id.tv_image);
        Picasso.with(getApplicationContext()).load(PersonneConnecte.getPersonneConnecte().getUrl_img()).centerCrop().fit().into(navImg);

        RelativeLayout relativeLayout = (RelativeLayout) headerView.findViewById(R.id.bloc_header);

        relativeLayout.setBackgroundResource(R.drawable.parisguidetower);
//        Blurry.with(MainActivity.this).radius(25).sampling(2).onto((ViewGroup) headerView.findViewById(R.id.bloc_header));

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass = ProfilFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//                Ici on décoche l'item selectionné dans la navigation
                int size = nvDrawer.getMenu().size();
                for (int i = 0; i < size; i++) {
                    nvDrawer.getMenu().getItem(i).setChecked(false);
                }

                mDrawer.closeDrawers();
            }
        });
        //setActual_user();
//        this.prenom_nom_actual_user = (TextView)findViewById(R.id.ho);

        //this.prenom_nom_actual_user.setText(this.actual_user.getPrenom()+" "+this.actual_user.getNom());
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = AccueilEventsFragment.class;
                break;
            case R.id.nav_participations:
                fragmentClass = AccueilEventsFragment.class;
                break;
            case R.id.nav_evenements:
                fragmentClass = AccueilEventsFragment.class;
                break;
            case R.id.nav_amis:
                fragmentClass = ListeAmisFragment.class;
                break;
            default:
                fragmentClass = AccueilEventsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

}