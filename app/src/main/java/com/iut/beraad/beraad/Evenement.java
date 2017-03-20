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
    private Adresse adresse;
    private String heure;
    private Personne auteur;

    public Evenement(String titre, String url, int nbParticipants, int nbPlaceMAX, Date date, String description,Adresse adresse, Personne auteur) {
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
        return DateDecoupe[2]+" "+traductionMois(DateDecoupe[1])+" "+DateDecoupe[5];
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getHeure() {
        return heure;
    }

    public Personne getAuteur() {
        return auteur;
    }

    public String traductionMois(String s) {
        String mois;
        switch (s) {
            case "Jan":
                mois = "Janvier";
            break;
            case "Feb":
                mois = "Février";
                break;
            case "Mar":
                mois = "Mars";
                break;
            case "Apr":
                mois = "Avril";
                break;
            case "May":
                mois = "Mai";
                break;
            case "Jun":
                mois = "Juin";
                break;
            case "Jul":
                mois = "Juillet";
                break;
            case "Aug":
                mois = "Août";
                break;
            case "Sep":
                mois = "Septembre";
                break;
            case "Oct":
                mois = "Octobre";
                break;
            case "Nov":
                mois = "Novembre";
                break;
            case "Dec":
                mois = "Décembre";
                break;
            default:
                mois = s;
            break;
        }
        return mois;
    }
}
