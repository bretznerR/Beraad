package com.iut.beraad.beraad;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by raphaelbretzner on 27/03/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView btnLogin;
    private ProgressDialog progressDialog;
    Personne user;
    String urlParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connexion);
        if(PrefUtils.getCurrentUser(getApplicationContext()) != null || Profile.getCurrentProfile() != null){
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }

    public void verifProfil() {
        if(user != null && PrefUtils.getCurrentUser(LoginActivity.this) == null) {
            urlParameters = "fb_id="+user.facebookID
                    +"&fb_nom="+user.getNom()
                    +"&fb_prenom="+user.getPrenom();
            new MyDownloadTask().execute();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions("public_profile", "email", "user_friends");

        btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.show();

                loginButton.performClick();

                loginButton.setPressed(true);

                loginButton.invalidate();

                loginButton.registerCallback(callbackManager, mCallBack);

                loginButton.setPressed(false);

                loginButton.invalidate();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            progressDialog.dismiss();
            // App code
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            try {
                                String id = object.getString("id").toString();
                                System.out.println(id.length());
                                String longName = object.getString("name").toString();
                                String fname = longName.substring(0, longName.indexOf(" "));
                                String name = longName.substring(longName.indexOf(" "), longName.length());
                                user = new Personne(id, fname,
                                        name
                                        );
                                verifProfil();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(LoginActivity.this, "Bienvenue " + Profile.getCurrentProfile().getName(), Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
            progressDialog.dismiss();
        }
    };

    class MyDownloadTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            if(user != null && PrefUtils.getCurrentUser(LoginActivity.this) == null) {
                try {
                    String myurl = "http://pageperso.iut.univ-paris8.fr/~alemaire/Beraad/index.php?module=evenement&action=verifUserExistant";
                    URL url = new URL(myurl);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(urlParameters);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            return null;
        }
    }
}
