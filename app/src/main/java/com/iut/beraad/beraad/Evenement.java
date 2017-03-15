package com.iut.beraad.beraad;

import java.util.Date;

/**
 * Created by Adrien on 13/03/2017.
 */

public class Evenement {

    private String imageUrl;
    private String titre;
    // A rajouter : Géolocalisation Google
    private Date date;
    // A rajouter : Heure
    private String Description;
    private int nbPlaceMAX;
    private int nbParticipants;
    private boolean placeEstLimite;
    private boolean estPrivee;

    public Evenement(String titre, String url, int nbParticipants, int nbPlaceMAX, Date date) {
        this.titre = titre;
        this.imageUrl = url;
        this.nbParticipants = nbParticipants;
        this.nbPlaceMAX = nbPlaceMAX;
        this.date = date;
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
        return Description;
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
}
