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

import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccueilEventsFragment extends Fragment {

    private final int REQUEST_CODE = 20;
    EvenementAdapter evenementAdapter;
    View view;
    HttpURLConnection connection;
    private RecyclerView recyclerView;
    private SortedSet<Evenement> evenements_trie_date;
    private FloatingActionButton fab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_accueil_events,container,false);
        this.evenements_trie_date = new TreeSet(new ComparateurParDate());

        new MyDownloadTask().execute();

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
                        try {
//                            String myurl= "http://adrien.pre-prod.space/Beraad/index.php?module=evenement&action=resultat_allEvents";
                            String myurl= "http://pageperso.iut.univ-paris8.fr/~alemaire/Beraad/index.php?module=evenement&action=resultat_allEvents";
                            URL url = new URL(myurl);
                            connection = (HttpURLConnection) url.openConnection();
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            String result = InputStreamOperations.InputStreamToString(inputStream);
                            // On récupère le tableau d'objets qui nous concerne
                            JSONArray array = new JSONArray(result);
                            evenements_trie_date.clear();
                            for (int i=0; i<array.length(); i++) {

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
                                        new Personne(obj.getString("idFacebook"),obj.getString("prenomUtilisateur"),obj.getString("nomUtilisateur")),
                                        false));
                            }
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
                            new Personne(obj.getString("idFacebook"),obj.getString("prenomUtilisateur"),obj.getString("nomUtilisateur")),
                            false));
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

}
