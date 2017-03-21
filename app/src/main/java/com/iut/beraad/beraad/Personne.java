package com.iut.beraad.beraad;

/**
 * Created by Adrien on 20/03/2017.
 */

public class Personne {

    private String nom;
    private String prenom;
    private String email;
    private String url_img;

    public Personne(String nom, String prenom, String email, String url_img) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.url_img = url_img;
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
