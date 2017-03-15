package com.iut.beraad.beraad;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * Created by Adrien on 14/03/2017.
 */

public class EvenementViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewView;
    private ImageView imageView;
    private TextView nbParticipantsView;
    private ImageView icon_participants;
    private TextView joursRestants;
    private LinearLayout bloc_event;
    //itemView est la vue correspondante à 1 cellule
    public EvenementViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        nbParticipantsView = (TextView) itemView.findViewById(R.id.nbParticipant);
        icon_participants = (ImageView) itemView.findViewById(R.id.participant_icon);
        joursRestants = (TextView) itemView.findViewById(R.id.joursRestants);
        bloc_event = (LinearLayout) itemView.findViewById(R.id.bloc_event);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Evenement evenement){
        textViewView.setText(evenement.getTitre());
        Picasso.with(imageView.getContext()).load(evenement.getImageUrl()).centerCrop().fit().into(imageView);
        nbParticipantsView.setText(Integer.toString(evenement.getNbParticipants()));

        if(NbrJourEntre2Date.differenceDate(Calendar.getInstance().getTime(),evenement.getDate())>0) {
            //joursRestants.setText(String.valueOf(Calendar.getInstance().getTime().compareTo(evenement.getDate())));
            joursRestants.setText("Dans "+String.valueOf(NbrJourEntre2Date.differenceDate(Calendar.getInstance().getTime(),evenement.getDate()))+" jours");
        } else if(NbrJourEntre2Date.differenceDate(Calendar.getInstance().getTime(),evenement.getDate())==0) {
            joursRestants.setText("Aujourd'hui");
        } else {
            joursRestants.setText("Passé");
            bloc_event.setBackgroundColor(ContextCompat.getColor(nbParticipantsView.getContext(), R.color.colorIsClose));
        }
        if(!evenement.isPlacesLibres()) {
            //bloc_event.setBackgroundColor(ContextCompat.getColor(nbParticipantsView.getContext(), R.color.colorIsClose));
            textViewView.setText(evenement.getTitre()+" (COMPLET)");
            nbParticipantsView.setTextColor(ContextCompat.getColor(nbParticipantsView.getContext(), R.color.colorNO));
        } else {
            nbParticipantsView.setTextColor(ContextCompat.getColor(nbParticipantsView.getContext(), R.color.colorYES));
        }
    }
}

