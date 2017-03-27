package com.iut.beraad.beraad;

/**
 * Created by Adrien on 20/03/2017.
 */

public class PersonneConnecte extends Personne{

    public PersonneConnecte(String prenom, String nom, String email, String url_img) {
        super(prenom, nom, email, url_img);
    }

    public static Personne getPersonneConnecte() {
        return new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png");
    }
}