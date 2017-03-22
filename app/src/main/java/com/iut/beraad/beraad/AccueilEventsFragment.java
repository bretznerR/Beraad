package com.iut.beraad.beraad;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccueilEventsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SortedSet<Evenement> evenements_trie_date;
    private FloatingActionButton fab;

    private final int REQUEST_CODE = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_accueil_events,container,false);
        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
        ajouterEvenements();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date));

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AjoutEventActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        setHasOptionsMenu(true);

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_liste_events, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE est défini en attribut
        if (requestCode == REQUEST_CODE) {

            String nomEvent = data.getExtras().getString("nomEvent");
            String descriptionEvent = data.getExtras().getString("descriptionEvent");
            String nbPlaceEvent = data.getExtras().getString("nbPlaceEvent");
            String dateEvent = data.getExtras().getString("dateEvent");
            String heureEvent = data.getExtras().getString("heureEvent");
            String estPrive = data.getExtras().getString("estPrive");

            String numeroRue = data.getExtras().getString("numeroRue");
            String rue = data.getExtras().getString("rue");
            String ville = data.getExtras().getString("ville");
            String codePostal = data.getExtras().getString("codePostal");

            String jour = dateEvent.substring(3, 5);
            String mois = dateEvent.substring(6, 10);
            String annee = dateEvent.substring(11, dateEvent.length());

            Date date = new DateTest(Integer.parseInt(annee), moisToInt(mois), Integer.parseInt(jour)).getDate();
            Adresse adresse = new Adresse(numeroRue, rue, ville, codePostal);

            Personne p = new Personne("BRETZNER", "Raphaël", "rbretzner@gmail.com", "");
            Evenement evenement = new Evenement(nomEvent, "", 0,
                    Integer.valueOf(nbPlaceEvent), date,
                    descriptionEvent, adresse, p, Boolean.valueOf(estPrive));

            ajouterEvenement(evenement);
            for (Evenement e : evenements_trie_date) {
                System.out.println("bouble 2 des évènements " + e.toString());
            }
        }
    }

    public void ajouterEvenements() {

        evenements_trie_date.add(new Evenement("Tournois de foot", "http://img4.hostingpics.net/pics/6852211195325unscandaledevasionfiscaleeclabousselefootballitalienwebtete021648790152.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"A l'occasion du 7829eme anniversaire de XXXX, un tournoi de foot a été organisé pour tous les amoureux de foot... Venez nombreux, plein de cadeaux à gagner !!",
                new Adresse("17", "allée jean jacques rousseau", "Les Pavillons Sous Bois", "93320"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com", ""), false)
        );
        evenements_trie_date.add(new Evenement("GROSSE SOIREE CHEZ RAPHAEL", "http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"Ramener les bières et des gateaux, je m'occupe du reste ! :p",
                new Adresse("140", "rue de la nouvelle france", "Montreuil", "93100"),
                new Personne("Adrien", "Lemaire", "alemaire@gmail.com", ""), false)
        );
        evenements_trie_date.add(new Evenement("Nouvel an 2046", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 31, 37, new DateTest(2017, 03, 28).getDate(),"\"Fetons le nouvel an ensemble !!!!! ehehehehhe",
                new Adresse("489", "avenue Salvador Allende", "Ivry-Sur-Seine", "92500"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com", ""), false)
        );
        evenements_trie_date.add(new Evenement("Madame invite : Rezz, Loge21, Moonbase au Trabendo le 7 avril", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"Soirée au Trabendo, organisé par XXXX. Line up : REZZ , Loge21 & guests",
                new Adresse("56", "avenue de la forêt", "Paris", "75005"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com", ""), false)
        );
        evenements_trie_date.add(new Evenement("GROSSE SOIREE CHEZ RAPHAEL", "http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg", 31, 37, new DateTest(2017, 06, 12).getDate(),"Ramener les bières et des gateaux, je m'occupe du reste ! :p",
                new Adresse("140", "rue de la nouvelle france", "Montreuil", "93100"),
                new Personne("Adrien", "Lemaire", "alemaire@gmail.com", ""), false)
        );
        evenements_trie_date.add(new Evenement("Nouvel an 2046", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 31, 37, new DateTest(2017, 07, 12).getDate(),"\"Fetons le nouvel an ensemble !!!!! ehehehehhe",
                new Adresse("489", "avenue Salvador Allende", "Ivry-Sur-Seine", "92500"),
                new Personne("Raphaël", "Bretzner", "rbretzner@gmail.com", ""), false)
        );

    }

    public void ajouterEvenement(Evenement e) {
        evenements_trie_date.add(e);
        for (Evenement evenement : evenements_trie_date) {
            System.out.println("bouble des évènements " + evenement.toString());
        }
    }

    public int moisToInt(String s) {
        int mois;
        switch (s) {
            case "Janvier":
                mois = 0;
                break;
            case "Février":
                mois = 1;
                break;
            case "Mars":
                mois = 2;
                break;
            case "Avril":
                mois = 3;
                break;
            case "Mai":
                mois = 4;
                break;
            case "Juin":
                mois = 5;
                break;
            case "Juillet":
                mois = 6;
                break;
            case "Août":
                mois = 7;
                break;
            case "Septembre":
                mois = 8;
                break;
            case "Octobre":
                mois = 9;
                break;
            case "Novembre":
                mois = 10;
                break;
            case "Décembre":
                mois = 11;
                break;
            default:
                mois = -1;
                break;
        }
        return mois;
    }
}