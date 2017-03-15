package com.iut.beraad.beraad;

import java.util.Comparator;

/**
 * Created by Adrien on 15/03/2017.
 */

public class ComparateurParDate implements Comparator<Evenement> {

    public int compare(Evenement o1, Evenement o2) {
        if(o1.getDate().compareTo(o2.getDate())==0) {
            return o1.getTitre().compareTo(o2.getTitre());
        } else {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

}
