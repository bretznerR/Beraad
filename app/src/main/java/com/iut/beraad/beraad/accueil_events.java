package com.iut.beraad.beraad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.SortedSet;
import java.util.TreeSet;

public class accueil_events extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SortedSet evenements_trie_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //remplir les événements
        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
        ajouterEvenements();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview
        recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ajouterEvenements() {

        evenements_trie_date.add(new Evenement("Tournois de foot","http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg",31,37,new DateTest(2017,11,12).getDate()));
        evenements_trie_date.add(new Evenement("GROSSE SOIREE CHEZ RAPHAEL","http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg",120,120,new DateTest(2017,3,16).getDate()));
        evenements_trie_date.add(new Evenement("Nouvel an 2046","http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg",1002,1007,new DateTest(2017,05,1).getDate()));
        evenements_trie_date.add(new Evenement("Madame invite : Rezz, Loge21, Moonbase au Trabendo le 7 avril","http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg",761,765,new DateTest(2020,1,12).getDate()));
        evenements_trie_date.add(new Evenement("GROSSE SOIREE CHEZ RAPHAEL","http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg",12,12,new DateTest(2007,11,10).getDate()));
        evenements_trie_date.add(new Evenement("Nouvel an 2046","http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg",45,80,new DateTest(2017,3,15).getDate()));
    }


}
