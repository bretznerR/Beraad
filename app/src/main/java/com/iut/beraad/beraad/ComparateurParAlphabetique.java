package com.iut.beraad.beraad;

import java.util.Comparator;

/**
 * Created by Adrien on 21/03/2017.
 */

public class ComparateurParAlphabetique implements Comparator<Personne> {

    @Override
    public int compare(Personne o1, Personne o2) {
        if(o1.getPrenom().compareTo(o2.getPrenom())==0) {
            if(o1.getNom().compareTo(o2.getNom())==0) {
                return o1.getEmail().compareTo(o2.getEmail());
            } else {
                return o1.getNom().compareTo(o2.getNom());
            }
        } else return o1.getPrenom().compareTo(o2.getPrenom());
    }

}
