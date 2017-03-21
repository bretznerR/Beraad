package com.iut.beraad.beraad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Adrien on 20/03/2017.
 */

public class ProfilFragment extends Fragment {

    private TextView nomprenom_profil;
    private TextView email_profil;
    private ImageView image_profil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_profil,container,false);

        this.nomprenom_profil = (TextView) view.findViewById(R.id.nomprenom_profil);
        this.email_profil = (TextView) view.findViewById(R.id.email_profil);
        this.image_profil = (ImageView) view.findViewById(R.id.image_profil);

        this.nomprenom_profil.setText(PersonneConnecte.getPersonneConnecte().getPrenom()+" "+PersonneConnecte.getPersonneConnecte().getNom());
        this.email_profil.setText(PersonneConnecte.getPersonneConnecte().getEmail());

//        Picasso.with(getContext()).load(PersonneConnecte.getPersonneConnecte().getUrl_img()).centerCrop().fit().into(image_profil);
            this.image_profil.setImageResource(R.drawable.parisguidetower);


        return view;
    }
}
