package com.iut.beraad.beraad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Adrien on 21/03/2017.
 */

public class ListeAmisFragment extends Fragment{

    private SortedSet<Personne> amis_trie_alphabetique;
    private RecyclerView recyclerView;
    private FloatingActionButton search_ami;
    private AutoCompleteTextView ami_cherche_ami;
    private LinearLayout ami_bloc_cherche;
    private FloatingActionButton fab;
    private boolean rechercheEstAffiche = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_liste_amis,container,false);
        this.amis_trie_alphabetique = new TreeSet(new ComparateurParAlphabetique());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_amis);
        this.ami_cherche_ami = (AutoCompleteTextView) view.findViewById(R.id.ami_cherche_ami);
        this.ami_bloc_cherche = (LinearLayout) view.findViewById(R.id.ami_bloc_cherche);
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);

        this.fab.setOnClickListener(new View.OnClickListener() {
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
                    fab.setImageResource(R.drawable.ic_search);
                    rechercheEstAffiche=false;
                }

            }
        });


        GraphRequestBatch batch = new GraphRequestBatch(
                GraphRequest.newMyFriendsRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONArrayCallback() {
                            @Override
                            public void onCompleted(
                                    JSONArray jsonArray,
                                    GraphResponse response) {
                                // Code pour liste amis utilisant l'application
                                try {
                                    JSONObject jsonObject = response.getJSONObject();
                                    JSONArray data = response.getJSONObject().getJSONArray("data");

                                    // Ajout des amis dans recyclerView
                                    for (int i = 0; i < data.length(); i++) {
                                        String name = data.getJSONObject(i).get("name").toString();
                                        String id = data.getJSONObject(i).get("id").toString();
                                        String url = "https://graph.facebook.com/" + id + "/picture?type=large";

                                        String fname = name.substring(0, name.indexOf(" "));
                                        String lname = name.substring(name.indexOf(" "), name.length());
                                        amis_trie_alphabetique.add(new Personne(id, fname, lname));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(new AmisAdapter(amis_trie_alphabetique));
                            }
                        })
        );
        batch.addCallback(new GraphRequestBatch.Callback() {
            @Override
            public void onBatchCompleted(GraphRequestBatch graphRequests) {
            }
        });
        batch.executeAsync();

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,picture");
        return view;
    }
}
