package com.iut.beraad.beraad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.SortedSet;
import java.util.TreeSet;

public class AccueilEventsActivity extends Fragment {

    private RecyclerView recyclerView;
    private SortedSet evenements_trie_date;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_accueil_events,container,false);

        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
        ajouterEvenements();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

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

        return view;


    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //remplir les événements
//        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
//        ajouterEvenements();
//        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
//
//        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date,this));
//
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_accueil_events);
////        toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////        initNavigationDrawer();
//
////        fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(AccueilEventsActivity.this,AjoutEventActivity.class);
////                startActivity(intent);
////            }
////        });
//        //remplir les événements
//        this.evenements_trie_date = new TreeSet(new ComparateurParDate());
//        ajouterEvenements();
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setAdapter(new EvenementAdapter(evenements_trie_date,this));
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_accueil_events, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void ajouterEvenements() {

        evenements_trie_date.add(new Evenement("Tournois de foot", "http://img4.hostingpics.net/pics/6852211195325unscandaledevasionfiscaleeclabousselefootballitalienwebtete021648790152.jpg", 31, 37, new DateTest(2017, 11, 12).getDate(),"A l'occasion du 7829eme anniversaire de XXXX, un tournoi de foot a été organisé pour tous les amoureux de foot... Venez nombreux, plein de cadeaux à gagner !!","12 rue de la Paix, Paris","Jean"));
        evenements_trie_date.add(new Evenement("GROSSE SOIREE CHEZ RAPHAEL", "http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg", 120, 120, new DateTest(2017, 3, 22).getDate(),"Ramener les bières et des gateaux, je m'occupe du reste ! :p","140 rue de la Nouvelle France, Montreuil","Pierre"));
        evenements_trie_date.add(new Evenement("Nouvel an 2046", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 1002, 1007, new DateTest(2017, 05, 1).getDate(),"Fetons le nouvel an ensemble !!!!! ehehehehhe","489 avenue Salvador Allende, Ivry-Sur-Seine","Raphaël"));
        evenements_trie_date.add(new Evenement("Madame invite : Rezz, Loge21, Moonbase au Trabendo le 7 avril", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 761, 765, new DateTest(2020, 1, 12).getDate(),"Soirée au Trabendo, organisé par XXXX. Line up : REZZ , Loge21 & guests","56 avenue de la forêt, Paris","Adrien"));
        evenements_trie_date.add(new Evenement("GROSSE SOIREE CHEZ RAPHAEL", "http://www.csgo.ca/wp-content/uploads/2016/09/party.jpg", 12, 12, new DateTest(2007, 11, 10).getDate(),"Comme dh'ab vous connaissez les bails à présent :D","31 rue du parc, Bobigny","Benjamin"));
        evenements_trie_date.add(new Evenement("Nouvel an 2046", "http://www.iedrs.com/wp-content/uploads/2017/02/4-paris-moyan-brenn-2.jpg", 45, 80, new DateTest(2017, 3, 23).getDate(),"On est ici dans le turfu : 2046 !!","87 rue du cheval, Fontenay-Sous-Bois", "Alain"));
    }

//    public void initNavigationDrawer() {
//
//        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//                int id = menuItem.getItemId();
//
//                switch (id){
//                    case R.id.home:
//                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawers();
//                        break;
//                    case R.id.settings:
//                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.trash:
//                        Toast.makeText(getApplicationContext(),"Trash",Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawers();
//                        break;
//                    case R.id.logout:
//                        finish();
//
//                }
//                return true;
//            }
//        });
//        View header = navigationView.getHeaderView(0);
//        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
//        tv_email.setText("adresse@email.com");
//        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
//
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
//
//            @Override
//            public void onDrawerClosed(View v){
//                super.onDrawerClosed(v);
//            }
//
//            @Override
//            public void onDrawerOpened(View v) {
//                super.onDrawerOpened(v);
//            }
//        };
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//    }


}