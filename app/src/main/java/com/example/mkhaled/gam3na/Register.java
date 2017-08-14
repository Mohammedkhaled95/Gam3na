package com.example.mkhaled.gam3na;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Register extends AppCompatActivity {

    EditText nameEditText;
    EditText emailEditText;
    EditText passWordEditText;
    EditText confirmpassWordEditText;
    EditText phoneEditText;

    Button nextButton;
    ProgressBar mprogressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nameEditText = (EditText) findViewById(R.id.name_text);
        emailEditText = (EditText) findViewById(R.id.email_text);
        passWordEditText = (EditText) findViewById(R.id.password_text);
        confirmpassWordEditText = (EditText) findViewById(R.id.confirmPassword_text);
        phoneEditText = (EditText) findViewById(R.id.phone_text);

        //reference nextbutton
        nextButton = (Button) findViewById(R.id.register_button);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);


        nextButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (isValidName() && isValidEmail() && isValidPassWord() && isIdenticalPasswords() && isValidPhone()) {
                    //Register
                    Backendless.setUrl(Defaults.SERVER_URL);
                    Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);
                    BackendlessUser user = new BackendlessUser();
                    user.setProperty("email", emailEditText.getText().toString());
                    user.setProperty("name", nameEditText.getText().toString());
                    user.setProperty("phone", phoneEditText.getText().toString());
                    user.setPassword(passWordEditText.getText().toString());
                    mprogressBar.setVisibility(View.VISIBLE);


                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        public void handleResponse(BackendlessUser registeredUser) {
                            // user has been registered and now can login
                            Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Login.class));
                            mprogressBar.setVisibility(View.GONE);
                            finish();
                        }

                        public void handleFault(BackendlessFault fault) {
                            // an error has occurred, the error code can be retrieved with fault.getCode()
                            Toast.makeText(Register.this, "error creating account", Toast.LENGTH_SHORT).show();
                            Log.e("Registeration", "handleFault: " + fault.toString());
                        }
                    });


                } else {
                    //set errors
                    if (!isValidEmail()) {
                        emailEditText.setError("not valid email");
                    }
                    if (!isValidName()) {
                        nameEditText.setError("not valid name");
                    }
                    if (!isValidPassWord()) {
                        passWordEditText.setError("not valid password");
                    }
                    if (!isIdenticalPasswords()) {
                        confirmpassWordEditText.setError("not Identical Password");
                    }
                    if (!isValidPhone()) {
                        phoneEditText.setError("not valid Phone number");
                    }

                }
            }
        });
    }

    boolean isValidName() {
        if (nameEditText.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }


    //check Email
    boolean isValidEmail() {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
            return false;
        }
        return true;
    }

    //check password

    boolean isValidPassWord() {

        String password = passWordEditText.getText().toString();
        if (!password.isEmpty() && password.length() >= 8) {
            return true;
        }
        return false;

    }

    //confirm  password
    boolean isIdenticalPasswords() {
        if (passWordEditText.getText().toString().equals(confirmpassWordEditText.getText().toString())) {
            return true;
        }
        return false;
    }

    //check phone number
    boolean isValidPhone() {
        if (!Patterns.PHONE.matcher(phoneEditText.getText().toString()).matches()) {
            return false;
        }
        return true;
    }

}
