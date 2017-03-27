package com.iut.beraad.beraad;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
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
    public ImageButton ami_supp_ami;
    private boolean estVisible = false;

    public AmisViewHolder(final View itemView) {
        super(itemView);

        this.ami_nom_prenom = (TextView) itemView.findViewById(R.id.ami_nom_prenom);
        this.ami_image = (ImageView) itemView.findViewById(R.id.ami_image);
        this.ami_bloc = (LinearLayout) itemView.findViewById(R.id.ami_bloc);
        this.ami_plus = (LinearLayout) itemView.findViewById(R.id.ami_plus);
        this.ami_supp_ami = (ImageButton) itemView.findViewById(R.id.ami_supp_ami);

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

        this.ami_supp_ami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(itemView.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Es-tu sûr de ne plus le vouloir comme ami ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Non", null)
                        .setNeutralButton("Peut-être",null)
                        .show();
            }
        });

    }

    public void bind(Personne personne){
        this.ami_nom_prenom.setText(personne.getPrenom()+" "+personne.getNom());
        Picasso.with(this.ami_image.getContext()).load(personne.getUrl_img()).centerCrop().fit().into(this.ami_image);
    }
}
