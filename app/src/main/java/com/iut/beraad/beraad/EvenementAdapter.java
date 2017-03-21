package com.iut.beraad.beraad;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Adrien on 14/03/2017.
 */


public class EvenementAdapter extends RecyclerView.Adapter<EvenementViewHolder> {

    private List<Evenement> list;

    //ajouter un constructeur prenant en entrée une liste
    public EvenementAdapter(SortedSet<Evenement> list) {
        this.list = new ArrayList<Evenement>(list);
        enleverAnciensEvenements();
    }

    public void enleverAnciensEvenements() {
        Iterator<Evenement> i = list.iterator();
        while(i.hasNext()) {
            Evenement e = i.next();
            if (NbrJourEntre2Date.differenceDate(Calendar.getInstance().getTime(),e.getDate())<0) i.remove();
        }
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public EvenementViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_card_event,viewGroup,false);
        return new EvenementViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(EvenementViewHolder myViewHolder, int position) {
        Evenement myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}