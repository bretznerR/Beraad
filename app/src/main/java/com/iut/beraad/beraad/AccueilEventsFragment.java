package com.iut.beraad.beraad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.SortedSet;
import java.util.TreeSet;

public class AccueilEventsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SortedSet evenements_trie_date;
    private FloatingActionButton fab;

    AjoutEventActivity ajoutEventActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_accueil_events,container,false);

        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
        ajoutEventActivity = new AjoutEventActivity();
        ajouterEvenements();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date));

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AjoutEventActivity.class);
                startActivity(intent);
            }
        });

        setHasOptionsMenu(true);

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_liste_events, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    public void ajouterEvenements() {
        evenements_trie_date.add(ajoutEventActivity.creerEvenement("Tournois de foot", "http://img4.hostingpics.net/pics/6852211195325unscandaledevasionfiscaleeclabousselefootballitalienwebtete021648790152.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"A l'occasion du 7829eme anniversaire de XXXX, un tournoi de foot a été organisé pour tous les amoureux de foot... Venez nombreux, plein de cadeaux à gagner !!",
                new Adresse("17", "allée jean jacques rousseau", "Les Pavillons Sous Bois", "93320", "Seine-Saint-Denis", "Ile-de-France", "France"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"))
        );
        evenements_trie_date.add(ajoutEventActivity.creerEvenement("GROSSE SOIREE CHEZ RAPHAEL", "http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"Ramener les bières et des gateaux, je m'occupe du reste ! :p",
                new Adresse("140", "rue de la nouvelle france", "Montreuil", "93100", "Seine-Saint-Denis", "Ile-de-France", "France"),
                new Personne("Adrien", "Lemaire", "alemaire@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"))
        );
        evenements_trie_date.add(ajoutEventActivity.creerEvenement("Nouvel an 2046", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"\"Fetons le nouvel an ensemble !!!!! ehehehehhe",
                new Adresse("489", "avenue Salvador Allende", "Ivry-Sur-Seine", "92500", "Seine-Saint-Denis", "Ile-de-France", "France"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"))
        );
        evenements_trie_date.add(ajoutEventActivity.creerEvenement("Madame invite : Rezz, Loge21, Moonbase au Trabendo le 7 avril", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"Soirée au Trabendo, organisé par XXXX. Line up : REZZ , Loge21 & guests",
                new Adresse("56", "avenue de la forêt", "Paris", "75005", "Seine-Saint-Denis", "Ile-de-France", "France"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"))
        );
        evenements_trie_date.add(ajoutEventActivity.creerEvenement("GROSSE SOIREE CHEZ RAPHAEL", "http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"Ramener les bières et des gateaux, je m'occupe du reste ! :p",
                new Adresse("140", "rue de la nouvelle france", "Montreuil", "93100", "Seine-Saint-Denis", "Ile-de-France", "France"),
                new Personne("Adrien", "Lemaire", "alemaire@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"))
        );
        evenements_trie_date.add(ajoutEventActivity.creerEvenement("Nouvel an 2046", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"\"Fetons le nouvel an ensemble !!!!! ehehehehhe",
                new Adresse("489", "avenue Salvador Allende", "Ivry-Sur-Seine", "92500", "Seine-Saint-Denis", "Ile-de-France", "France"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"))
        );

    }

}