package com.iut.beraad.beraad;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccueilEventsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SortedSet<Evenement> evenements_trie_date;
    private FloatingActionButton fab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    EvenementAdapter evenementAdapter;
    View view;
    private final int REQUEST_CODE = 20;
    private URL url;
    private HttpURLConnection connection;
    private String myurl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_accueil_events,container,false);
        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
        new MyDownloadTask().execute();

        System.out.println("test test aahahahhahah");
        System.out.println(evenements_trie_date);

        evenementAdapter = new EvenementAdapter(evenements_trie_date);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(evenementAdapter);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AjoutEventActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        setHasOptionsMenu(true);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                refreshItems();
            }

            private void refreshItems() {
                new Thread() {
                    @Override
                    public void run() {
                        System.out.println("hey?");
                        try {
//                            String myurl= "http://adrien.pre-prod.space/Beraad/index.php?module=evenement&action=resultat_allEvents";
                            myurl= "http://pageperso.iut.univ-paris8.fr/~alemaire/Beraad/index.php?module=evenement&action=resultat_allEvents";
                            url = new URL(myurl);
                            connection = (HttpURLConnection) url.openConnection();
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            String result = InputStreamOperations.InputStreamToString(inputStream);
                            // On récupère le tableau d'objets qui nous concerne
                            JSONArray array = new JSONArray(result);
                            evenements_trie_date.clear();
                            for (int i=0; i<array.length(); i++) {
                                System.out.println("ok"+i);
                                // On récupère un objet JSON du tableau
                                JSONObject obj = new JSONObject(array.getString(i));
                                // On fait le lien Personne - Objet JSON
                                evenements_trie_date.add(new Evenement(
                                        obj.getString("nomEvent"),
                                        obj.getString("image"),
                                        Integer.parseInt(obj.getString("nbParticipants")),
                                        Integer.parseInt(obj.getString("capacite")),
                                        DateTest.makeDateFromString(obj.getString("dateEvent")),
                                        obj.getString("description"),
                                        new Adresse(obj.getString("numero"),obj.getString("rue"),obj.getString("ville"),obj.getString("codePostal")),
                                        new Personne("Adrien","Lemaire","adrien@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"),
                                        false));
                            }
                            System.out.println("!!!!!!!!" + "OK");
                        } catch (Exception e) {
                            System.out.println("!!!!!!!!!!!!!!!!!" + "Exception lancé");
                            e.printStackTrace();
                        }
                    }
                }.start();
                recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date));
                onItemsLoadComplete();
            }

            private void onItemsLoadComplete() {
                Toast.makeText(getActivity(), "done",
                        Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });
        System.out.println(evenements_trie_date);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_liste_events, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

          /*  Personne p = new Personne("BRETZNER", "Raphaël", "rbretzner@gmail.com", "");
            Evenement evenement = new Evenement(nomEvent, "", 0,
                    Integer.valueOf(nbPlaceEvent), date,
                    descriptionEvent, adresse, p, Boolean.valueOf(estPrive));
          */

            try {
                url = new URL(myurl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                String result = InputStreamOperations.InputStreamToString(inputStream);
                // On récupère le tableau d'objets qui nous concerne
                JSONArray array = new JSONArray(result);

                /**
                 *  Il me faut l'action pour ajouter un évènement dans la BD
                 *  Quelqu'un peut donner la liste des actions possibles sur le serveur
                 *  À part action=resultat_allEvents
                 */

            } catch (Exception e) {

            }
        }
    }

    class MyDownloadTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String myurl= "http://pageperso.iut.univ-paris8.fr/~alemaire/Beraad/index.php?module=evenement&action=resultat_allEvents";
                URL url = new URL(myurl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                String result = InputStreamOperations.InputStreamToString(inputStream);
                // On récupère le tableau d'objets qui nous concerne
                JSONArray array = new JSONArray(result);
                evenements_trie_date.clear();
                for (int i=0; i<array.length(); i++) {
                    System.out.println("ok"+i);
                    // On récupère un objet JSON du tableau
                    JSONObject obj = new JSONObject(array.getString(i));
                    // On fait le lien Personne - Objet JSON
                    evenements_trie_date.add(new Evenement(
                            obj.getString("nomEvent"),
                            obj.getString("image"),
                            Integer.parseInt(obj.getString("nbParticipants")),
                            Integer.parseInt(obj.getString("capacite")),
                            DateTest.makeDateFromString(obj.getString("dateEvent")),
                            obj.getString("description"),
                            new Adresse(obj.getString("numero"),obj.getString("rue"),obj.getString("ville"),obj.getString("codePostal")),
                            new Personne("Adrien","Lemaire","adrien@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"),
                            false));
                    System.out.println("HELLO HELLO HELLO HELLO HELLO HELLO ");
                }
            } catch (Exception e) {
                Log.d("!!!!!!!!!!!!!!!!!","Exception lancé");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date));
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
    }

}
