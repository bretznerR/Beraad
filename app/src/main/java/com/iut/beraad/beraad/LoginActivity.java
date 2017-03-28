package com.iut.beraad.beraad;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by raphaelbretzner on 27/03/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView btnLogin;
    private ProgressDialog progressDialog;
    Personne user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        System.out.println("----------> " + 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        System.out.println("----------> " + 1);
        if(PrefUtils.getCurrentUser(LoginActivity.this) != null){
            System.out.println("----------> " + 2);
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(homeIntent);
            System.out.println("----------> " + 3);
            finish();
        }

        else {
            System.out.println("errrrrooooooooor");
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
                progressDialog.setMessage("Loading...");
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

                            System.out.println("object: " + object + "");

                            try {
                                String longName = object.getString("name").toString();
                                String fname = longName.substring(0, longName.indexOf(" "));
                                String name = longName.substring(longName.indexOf(" "), longName.length());
                                user = new Personne(fname,
                                        name,
                                        object.getString("email").toString()
                                        );
                                user.facebookID = object.getString("id").toString();
                                user.gender = object.getString("gender").toString();
                                PrefUtils.setCurrentUser(user, LoginActivity.this);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(LoginActivity.this, "welcome " + user.getNom(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
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
}
