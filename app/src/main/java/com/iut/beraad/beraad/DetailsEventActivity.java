package com.iut.beraad.beraad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URLEncoder;

/**
 * Created by Adrien on 16/03/2017.
 */

public class DetailsEventActivity extends AppCompatActivity {

    private TextView nom_event , adresse_event, date_event, heure_event, description_event, auteur_event;
    private ImageView image_event;
    private Toolbar toolbar;
    private Button button_event;
    private PersonneConnecte personneConnecte;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details_event);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        //personneConnecte = new PersonneConnecte();

        nom_event = (TextView) findViewById(R.id.nom_event);
        adresse_event = (TextView) findViewById(R.id.adresse_event);
        date_event = (TextView) findViewById(R.id.date_event);
        heure_event = (TextView) findViewById(R.id.heure_event);
        description_event = (TextView) findViewById(R.id.description_event);
        auteur_event = (TextView) findViewById(R.id.auteur_event);
        image_event = (ImageView) findViewById(R.id.image_event);
        button_event = (Button) findViewById(R.id.button);

        Intent myIntent = getIntent();
        viewOnMap();

        this.nom_event.setText(myIntent.getStringExtra("titre_event"));
        this.adresse_event.setText(myIntent.getStringExtra("adresse_event"));
        this.date_event.setText(myIntent.getStringExtra("date_event"));
        this.description_event.setText(myIntent.getStringExtra("description_event"));
        this.auteur_event.setText(myIntent.getStringExtra("auteur_event"));
        //this.image_event.setImageResource();
        Picasso.with(image_event.getContext()).load((myIntent.getStringExtra("image_event"))).centerCrop().fit().into(image_event);
        //Picasso.with(imageView.getContext()).load(evenement.getImageUrl()).centerCrop().fit().into(imageView);

        particperEvenement();
    }

    public void viewOnMap() {
        adresse_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(String.format("geo:0,0?q=%s",
                                URLEncoder.encode(adresse_event.getText().toString()))));
                startActivity(addressIntent);
            }
        });
    }

    public void particperEvenement() {
        button_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //personneConnecte.ajouterEvenement();
            }
        });

    }
}
