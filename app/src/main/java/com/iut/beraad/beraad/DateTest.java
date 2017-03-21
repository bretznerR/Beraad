package com.iut.beraad.beraad;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adrien on 15/03/2017.
 */

public class DateTest {

    private Date date;

    public DateTest(int annee, int mois, int jour) {
        Calendar c = Calendar.getInstance();
        c.set(annee,mois,jour);
        this.date = c.getTime();
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DateTest{" +
                "date=" + date.toString() +
                '}';
    }
}
