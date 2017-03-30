package com.iut.beraad.beraad;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by raphaelbretzner on 16/03/2017.
 */

public class AjoutEventActivity extends AppCompatActivity {

    @InjectView(R.id.nom_event)
    EditText nomEvent;

    @InjectView(R.id.description)
    EditText descriptionEvent;

    @InjectView(R.id.nbPlaceMAX)
    EditText nbPlaceEvent;

    @InjectView(R.id.places_autocomplete)
    PlacesAutocompleteTextView mAutocomplete;

    @InjectView(R.id.datePicker)
    TextView dateEvent;

    @InjectView(R.id.timePicker)
    TextView heureEvent;

    @InjectView(R.id.checkBox)
    CheckBox estPrive;

    @InjectView(R.id.creerEvent)
    Button creerEvent;

    private Toolbar toolbar;

    private String[] adresse;
    private final String USER_AGENT = "Mozilla/5.0";
    String urlParameters;
    DialogFragment newFragmentDate;
    DialogFragment newFragmentHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ButterKnife.inject(this);

        mAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                mAutocomplete.getDetailsFor(place, new DetailsCallback() {
                    @Override
                    public void onSuccess(final PlaceDetails details) {
                        System.out.println("Test -> success");
                        getAdresse(details);
                        System.out.println("HAAAAAAAAAAAA");
                        System.out.println(adresse[0]+adresse[1]+adresse[2]+adresse[3]);
                        System.out.println(dateEvent.getText().toString());
                        evenementCree(adresse);
                    }
                    @Override
                    public void onFailure(final Throwable failure) {
                        System.out.println("Test -> failure" + failure);
                    }
                });
            }
        });

    }

    public void evenementCree(final String[] adresse) {
        creerEvent.setOnClickListener(new View.OnClickListener() {
                        @Override
            public void onClick(View v) {
                urlParameters = "nomEvent="+nomEvent.getText().toString()
                        +"&capacite="+nbPlaceEvent.getText().toString()
                        +"&description="+descriptionEvent.getText().toString()
                        +"&dateEvent="+newFragmentDate.toString()
                        +"&heure="+newFragmentHeure.toString()
                        +"&image=http://cafoc-auvergne2.net/campus/pluginfile.php/1943/course/overviewfiles/bigstock-Test-word-on-white-keyboard-27134336.jpg"
                        +"&numero="+adresse[0]
                        +"&rue="+adresse[1]
                        +"&ville="+adresse[2]
                        +"&codePostal="+adresse[3];
                new MyDownloadTask().execute();

            }
        });
    }

    public String[] getAdresse(PlaceDetails details) {
        String[] adresse = new String[details.address_components.size()];
        adresse[0] = details.address_components.get(0).long_name;
        adresse[1] = details.address_components.get(1).long_name;
        adresse[2] = details.address_components.get(2).long_name;
        adresse[3] = details.address_components.get(6).short_name;
        adresse[4] = details.address_components.get(3).long_name;
        adresse[5] = details.address_components.get(4).long_name;
        adresse[6] = details.address_components.get(5).long_name;
        this.adresse = adresse;
        return adresse;
    }

    public void showDatePickerDialog(View v) {
        newFragmentDate = new DatePickerFragment();
        newFragmentDate.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        newFragmentHeure = new TimePickerFragment();
        newFragmentHeure.show(getFragmentManager(), "timePicker");
    }

    class MyDownloadTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                String myurl = "http://pageperso.iut.univ-paris8.fr/~alemaire/Beraad/index.php?module=evenement&action=post_event";
                URL url = new URL(myurl);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

                writer.write(urlParameters);
                writer.flush();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(AjoutEventActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }


}
