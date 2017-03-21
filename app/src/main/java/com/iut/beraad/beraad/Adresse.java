package com.iut.beraad.beraad;

/**
 * Created by raphaelbretzner on 20/03/2017.
 */

public class Adresse {

    private String numero;
    private String rue;
    private String ville;
    private String codePostal;
    private String departement;
    private String region;
    private String pays;

    public Adresse(String numero, String rue, String ville, String codePostal, String departement, String region, String pays) {
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.departement = departement;
        this.region = region;
        this.pays = pays;
    }

    public String getNumero() {
        return numero;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getDepartement() {
        return departement;
    }

    public String getRegion() {
        return region;
    }

    public String getPays() {
        return pays;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return numero + " " + rue + ", " + ville + ", " + codePostal;
    }
}
