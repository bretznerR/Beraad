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

    private SortedSet<Personne> listeAmis;
    private SortedSet<Evenement> listeEvenements;


    public Personne(String prenom, String nom, String email, String url_img) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.url_img = url_img;
        this.listeAmis = new TreeSet<>();
        this.listeEvenements = new TreeSet(new ComparateurParDate());
    }

    public SortedSet<Personne> getListeAmis() {
        return listeAmis;
    }

    public void setListeAmis(SortedSet<Personne> listeAmis) {
        this.listeAmis = listeAmis;
    }

    public void ajouterAmi(Personne p) {
        this.listeAmis.add(p);
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

    public SortedSet<Evenement> getListeEvenements() {
        return listeEvenements;
    }

    public void ajouterEvenement(Evenement e) {
        this.listeEvenements.add(e);
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " a " + listeAmis.size() + " amis";
    }
}
