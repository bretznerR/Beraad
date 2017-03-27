package com.iut.beraad.beraad;

/**
 * Created by Adrien on 20/03/2017.
 */

public class PersonneConnecte extends Personne{

    public PersonneConnecte(String prenom, String nom, String email) {
        super(prenom, nom, email);
    }

    public static Personne getPersonneConnecte() {
        return new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com");
    }
}