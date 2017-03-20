package com.iut.beraad.beraad;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by raphaelbretzner on 20/03/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        TextView tv1= (TextView) getActivity().findViewById(R.id.datePicker);
        tv1.setText("Le " + view.getDayOfMonth() + " " + traductionMois(view.getMonth()) + " " + view.getYear());
       // tv1.setText("Year: "+view.getYear()+" Month: "+view.getMonth()+" Day: "+view.getDayOfMonth());

    }

    public String traductionMois(int n) {
        String mois;
        switch (n) {
            case 0:
                mois = "Janvier";
                break;
            case 1:
                mois = "Février";
                break;
            case 2:
                mois = "Mars";
                break;
            case 3:
                mois = "Avril";
                break;
            case 4:
                mois = "Mai";
                break;
            case 5:
                mois = "Juin";
                break;
            case 6:
                mois = "Juillet";
                break;
            case 7:
                mois = "Août";
                break;
            case 8:
                mois = "Septembre";
                break;
            case 9:
                mois = "Octobre";
                break;
            case 10:
                mois = "Novembre";
                break;
            case 11:
                mois = "Décembre";
                break;
            default:
                mois = "";
                break;
        }
        return mois;
    }
}
