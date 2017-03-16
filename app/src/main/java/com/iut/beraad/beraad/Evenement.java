package com.iut.beraad.beraad;

import java.util.Date;

/**
 * Created by Adrien on 13/03/2017.
 */

public class Evenement {

    private String imageUrl;
    private String titre;
    private Date date;
    // A rajouter : Heure
    private String description;
    private int nbPlaceMAX;
    private int nbParticipants;
    private boolean placeEstLimite;
    private boolean estPrivee;
    // A rajouter : Géolocalisation Google
    private String adresse;
    private String heure;
    private String auteur;

    public Evenement(String titre, String url, int nbParticipants, int nbPlaceMAX, Date date, String description,String adresse, String auteur) {
        this.titre = titre;
        this.imageUrl = url;
        this.nbParticipants = nbParticipants;
        this.nbPlaceMAX = nbPlaceMAX;
        this.date = date;
        this.description = description;
        this.heure = "13:10";
        this.adresse = adresse;
        this.auteur = auteur;
    }

    public boolean isPlacesLibres() {
        return nbPlaceMAX-nbParticipants > 0 ;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getNbPlaceMAX() {
        return nbPlaceMAX;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public boolean isPlaceEstLimite() {
        return placeEstLimite;
    }

    public boolean isEstPrivee() {
        return estPrivee;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormate() {
        System.out.println(getDate());
        String[] DateDecoupe = this.getDate().toString().split(" ");
        return DateDecoupe[2]+" "+DateDecoupe[1]+" "+DateDecoupe[5];
    }

    public String getAdresse() {
        return adresse;
    }

    public String getHeure() {
        return heure;
    }

    public String getAuteur() {
        return auteur;
    }
}
