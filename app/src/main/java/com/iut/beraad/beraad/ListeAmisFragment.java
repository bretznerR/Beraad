package com.iut.beraad.beraad;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Adrien on 21/03/2017.
 */

public class ListeAmisFragment extends Fragment{

    private SortedSet amis_trie_alphabetique;
    private RecyclerView recyclerView;
    private EditText ami_cherche_ami;
    private LinearLayout ami_bloc_cherche;
    private FloatingActionButton fab;
    private boolean rechercheEstAffiche = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_liste_amis,container,false);

        this.amis_trie_alphabetique = new TreeSet(new ComparateurParAlphabetique());
        ajouterAmis();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_amis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new AmisAdapter(amis_trie_alphabetique));

        this.ami_cherche_ami = (EditText) view.findViewById(R.id.ami_cherche_ami);
        this.ami_bloc_cherche = (LinearLayout) view.findViewById(R.id.ami_bloc_cherche);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rechercheEstAffiche==false) {
                    ami_bloc_cherche.setVisibility(View.VISIBLE);
                    ami_cherche_ami.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ami_cherche_ami, InputMethodManager.SHOW_IMPLICIT);
                    fab.setImageResource(R.drawable.ic_clear);
                    rechercheEstAffiche=true;
                } else {
                    ami_bloc_cherche.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ami_cherche_ami.getWindowToken(),0);
                    fab.setImageResource(R.drawable.ic_person_add);
                    rechercheEstAffiche=false;
                }
            }
        });

        return view;
    }

    public void ajouterAmis() {
        this.amis_trie_alphabetique.add(new Personne("Adrien","Lemaire","alemaire@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Raphael","Bretzner","rbretzner@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Benjamin","Pasqualetto","bpasqualetto@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Jean-Michel","Dupont","jmdupont@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Saul","Goodman","sgoodmanr@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("White","Walter","wwalter@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Jesse","Pinkman","jpinkman@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Bruce","Wayne","bwayne@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Marco","Veratti","mveratti@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Daryl","Dickson","ddickson@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
        this.amis_trie_alphabetique.add(new Personne("Zlatan","Ibrahimovic","zibrahimovic@gmail.com","https://pbs.twimg.com/profile_images/627117609444581380/7YG7kxA4.png"));
    }
}
