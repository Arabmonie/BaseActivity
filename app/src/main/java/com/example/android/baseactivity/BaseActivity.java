package com.example.android.baseactivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class BaseActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ProgressDialog pd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            // Firebase Auth instance
            mAuth = FirebaseAuth.getInstance();


            // Progress dialog

            pd = new ProgressDialog(this);

        }




    public  void showProgressDialog(String message) {
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.show();
    }
    public void hideProgresDialog(){
        pd.hide();

    }
    public  FirebaseAuth getFirebaseAuth(){
        return  mAuth;
}

}