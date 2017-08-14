package com.example.mkhaled.gam3na;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Login extends AppCompatActivity {


    Button loginButton;
    EditText emailEditText;
    EditText passwordEditText;


    TextView newuserTextView;
    TextView forgotpasswordTextView;
    ProgressBar nproProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailEditText = (EditText) findViewById(R.id.email_editTxt);
        passwordEditText = (EditText) findViewById(R.id.password_editTxt);


        newuserTextView = (TextView) findViewById(R.id.newUser_textView);
        forgotpasswordTextView = (TextView) findViewById(R.id.forgot_password);


        loginButton = (Button) findViewById(R.id.Login_button);
        nproProgressBar = (ProgressBar) findViewById(R.id.progressBar_login);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //LOGIN
                Backendless.setUrl(Defaults.SERVER_URL);
                Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);


                if (validEmail() && validPassWord()) {
                    nproProgressBar.setVisibility(View.VISIBLE);


                    Backendless.UserService.login(emailEditText.getText().toString(), passwordEditText.getText().toString(), new AsyncCallback<BackendlessUser>() {
                                @Override
                                public void handleResponse(BackendlessUser response) {
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    i.putExtra("email", emailEditText.getText().toString());
                                    startActivity(i);
                                    nproProgressBar.setVisibility(View.INVISIBLE);
                                    finish();
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(Login.this, "wrong Username or Password", Toast.LENGTH_SHORT).show();
                                    nproProgressBar.setVisibility(View.INVISIBLE);


                                    Log.e("getMessage", "handleFault: " + fault.toString());


                                }
                            }
                            , true);
                } else {
                    emailEditText.setError("invalid username");
                    passwordEditText.setError("invalid password");

                }


            }
        });


        newuserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                finish();

            }
        });
    }

    boolean validEmail() {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
            return false;
        }
        return true;
    }

    boolean validPassWord() {

        String password = emailEditText.getText().toString();
        if (!password.isEmpty() && password.length() >= 8) {
            return true;
        }
        return false;

    }

}
