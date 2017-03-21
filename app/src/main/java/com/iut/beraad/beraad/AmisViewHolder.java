package com.iut.beraad.beraad;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Adrien on 21/03/2017.
 */

public class AmisViewHolder extends RecyclerView.ViewHolder {

    private TextView ami_nom_prenom;
    private ImageView ami_image;
    private LinearLayout ami_bloc;
    private LinearLayout ami_plus;
    private boolean estVisible = false;

    public AmisViewHolder(View itemView) {
        super(itemView);

        this.ami_nom_prenom = (TextView) itemView.findViewById(R.id.ami_nom_prenom);
        this.ami_image = (ImageView) itemView.findViewById(R.id.ami_image);
        this.ami_bloc = (LinearLayout) itemView.findViewById(R.id.ami_bloc);
        this.ami_plus = (LinearLayout) itemView.findViewById(R.id.ami_plus);

        this.ami_bloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!estVisible) {
                    ami_plus.setVisibility(View.VISIBLE);
                    estVisible = true;
                } else {
                    ami_plus.setVisibility(View.GONE);
                    estVisible = false;
                }
            }
        });

    }

    public void bind(Personne personne){
        this.ami_nom_prenom.setText(personne.getPrenom()+" "+personne.getNom());
        Picasso.with(this.ami_image.getContext()).load(personne.getUrl_img()).centerCrop().fit().into(this.ami_image);
    }
}
