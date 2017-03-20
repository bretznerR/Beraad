package com.iut.beraad.beraad;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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

    @InjectView(R.id.places_autocomplete)
    PlacesAutocompleteTextView mAutocomplete;

    private Toolbar toolbar;

    Adresse adresse;

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
                        getAdresse(details);
                    }

                    @Override
                    public void onFailure(final Throwable failure) {
                        System.out.println("Test -> failure" + failure);
                        Log.d("test", "failure " + failure);
                    }
                });
            }
        });
    }

    public Adresse getAdresse(PlaceDetails details) {
        adresse = new Adresse(details.address_components.get(0).long_name,
                details.address_components.get(1).long_name,
                details.address_components.get(2).long_name,
                details.address_components.get(6).short_name,
                details.address_components.get(3).long_name,
                details.address_components.get(4).long_name,
                details.address_components.get(5).long_name
        );
        System.out.println(adresse.toString());
        return adresse;
    }

    public Evenement creerEvenement(String titre, String url, int nbParticipants, int nbPlaceMAX, Date date, String description, Adresse adresse, Personne auteur) {
        return new Evenement(titre, url, nbParticipants, nbPlaceMAX, date, description, adresse, auteur);
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
