package com.example.android.baseactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class MainActivity extends BaseActivity {

    private EditText emailEdt, passwordEdt;
    private Button signInBtn, signUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * View binding
         */
        emailEdt = findViewById(R.id.Email);
        passwordEdt = findViewById(R.id.password);
        signInBtn = findViewById(R.id.signin);
        signUpBtn = findViewById(R.id.signup);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressDialog("Signing Up ...");

                String email = emailEdt.getText().toString(); // email
                String password = passwordEdt.getText().toString(); // password

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    emailEdt.setError("please enter email");
                    passwordEdt.setError("Please enter 6 digit + password");
                    hideProgresDialog();
                } else {

                    performSignUp( email, password);
                }

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressDialog("Signing In ...");

                String email = emailEdt.getText().toString(); // email
                String password = passwordEdt.getText().toString(); // password

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    emailEdt.setError("please enter email");
                    passwordEdt.setError("Please enter 6 digit + password");
                    hideProgresDialog();
                } else {

                    performSignIn( email, password);
                }

            }
        });
    }


    /**
     * Sign up User using Firebase
     *
     * @param email
     * @param password
     */
    private void performSignUp(String email, String password) {

        getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    hideProgresDialog();
                } else {
                    signUpBtn.setVisibility(View.GONE);
                    hideProgresDialog();
                }


            }
        });

    }

    private void performSignIn(String email, String password) {

        getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    hideProgresDialog();
                } else {
                    signInBtn.setVisibility(View.INVISIBLE);
                    hideProgresDialog();
                }


            }
        });


    }

}






