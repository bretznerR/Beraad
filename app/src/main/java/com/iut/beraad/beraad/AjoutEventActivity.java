package com.iut.beraad.beraad;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    AjoutEventAdapter ajoutEventAdapter;
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
                        String[] adr = getAdresse(details);
                        evenementCree(adr);
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

                Intent data = new Intent();
                data.putExtra("nomEvent", nomEvent.getText().toString());
                data.putExtra("descriptionEvent", descriptionEvent.getText().toString());
                data.putExtra("nbPlaceEvent", nbPlaceEvent.getText().toString());

                data.putExtra("numeroRue", adresse[0]);
                data.putExtra("rue", adresse[1]);
                data.putExtra("ville", adresse[2]);
                data.putExtra("codePostal", adresse[3]);

                data.putExtra("dateEvent", dateEvent.getText().toString());
                data.putExtra("heureEvent", heureEvent.getText().toString());
                data.putExtra("estPrive", Boolean.toString(estPrive.isChecked()));

                setResult(RESULT_OK, data);
                finish();

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
        return adresse;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }


}
