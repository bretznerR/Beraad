package com.iut.beraad.beraad;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by raphaelbretzner on 20/03/2017.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private TimePicker tp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        tp = view;
        TextView tv1=(TextView) getActivity().findViewById(R.id.timePicker);
        tv1.setText("À " + tp.getCurrentHour() + ":" + tp.getCurrentMinute());
        //tv1.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());
    }

    @Override
    public String toString() {
        return tp.getCurrentHour()+":"+tp.getCurrentMinute()+":00";
    }
}