package com.iut.beraad.beraad;

/**
 * Created by Adrien on 20/03/2017.
 */

public class Personne {

    private String nom;
    private String prenom;
    private String email;

    public Personne(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
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

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
