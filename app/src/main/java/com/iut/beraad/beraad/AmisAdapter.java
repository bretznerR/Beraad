package com.iut.beraad.beraad;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Adrien on 21/03/2017.
 */

public class AmisAdapter extends RecyclerView.Adapter<AmisViewHolder> {

    private List<Personne> list;

    //ajouter un constructeur prenant en entrée une liste
    public AmisAdapter(SortedSet<Personne> list) {
        this.list = new ArrayList<Personne>(list);
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public AmisViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_card_ami, viewGroup, false);
        return new AmisViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(AmisViewHolder myViewHolder, int position) {
        Personne myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}