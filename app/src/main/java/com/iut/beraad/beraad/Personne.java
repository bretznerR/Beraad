package com.iut.beraad.beraad;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Adrien on 20/03/2017.
 */

public class Personne {

    private String nom;
    private String prenom;
    private String email;
    private String url_img;

    public String facebookID;
    public String gender;


    public Personne(String id, String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
        this.facebookID = id;
        this.url_img = "https://graph.facebook.com/" + facebookID + "/picture?type=large";
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl_img() {
        return url_img;
    }


    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
