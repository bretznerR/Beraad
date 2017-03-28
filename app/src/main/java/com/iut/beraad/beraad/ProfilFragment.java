package com.iut.beraad.beraad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by raphaelbretzner on 20/03/2017.
 */

public class ProfilFragment extends Fragment {

    private TextView nomprenom_profil;
    ImageView image_profil;
    Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_profil,container,false);

        this.nomprenom_profil = (TextView) view.findViewById(R.id.nomprenom_profil);
        this.image_profil = (ImageView) view.findViewById(R.id.image_profil);

        this.nomprenom_profil.setText(Profile.getCurrentProfile().getName());


        // fetching facebook's profile picture
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                URL imageURL = null;
                try {
                    imageURL = new URL("https://graph.facebook.com/" + Profile.getCurrentProfile().getId() + "/picture?type=large");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    bitmap  = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                image_profil.setImageBitmap(bitmap);
            }
        }.execute();


        Button logOut = (Button) view.findViewById(R.id.button_log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.clearCurrentUser(getActivity());
                // We can logout from facebook by calling following method
                LoginManager.getInstance().logOut();
                Intent i= new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView image;

        public DownloadImageTask(ImageView image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls) {
            // Do downloading stuff
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }
}
