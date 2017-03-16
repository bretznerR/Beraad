package com.iut.beraad.beraad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Adrien on 16/03/2017.
 */

public class DetailsEventActivity extends AppCompatActivity {

    private TextView nom_event , adresse_event, date_event, heure_event, description_event, auteur_event;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details_event);
        

        nom_event = (TextView) findViewById(R.id.nom_event);
        adresse_event = (TextView) findViewById(R.id.adresse_event);
        date_event = (TextView) findViewById(R.id.date_event);
        heure_event = (TextView) findViewById(R.id.heure_event);
        description_event = (TextView) findViewById(R.id.description_event);
        auteur_event = (TextView) findViewById(R.id.auteur_event);

        Intent myIntent = getIntent();

        this.nom_event.setText(myIntent.getStringExtra("titre_event"));
        this.adresse_event.setText(myIntent.getStringExtra("adresse_event"));
        this.date_event.setText(myIntent.getStringExtra("date_event"));
        this.description_event.setText(myIntent.getStringExtra("description_event"));
        this.auteur_event.setText(myIntent.getStringExtra("auteur_event"));
    }





}
