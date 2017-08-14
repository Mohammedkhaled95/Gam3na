package com.example.mkhaled.gam3na;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;

public class CreateEvent extends AppCompatActivity {
    EditText editTextCreateTitle;
    EditText editTextCreateDescription;
    EditText editTextCreatePlace;
    EditText editTextCreateTime;
    Button createbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        editTextCreateTime = (EditText) findViewById(R.id.create_time);
        editTextCreatePlace = (EditText) findViewById(R.id.create_place);
        editTextCreateDescription = (EditText) findViewById(R.id.create_description);
        editTextCreateTitle = (EditText) findViewById(R.id.create_title);

        createbtn = (Button) findViewById(R.id.create_button);


        //Creating an event
        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextCreateTitle.getText().toString().isEmpty() && editTextCreateDescription.getText().toString().isEmpty() && editTextCreatePlace.getText().toString().isEmpty() && editTextCreateTime.getText().toString().isEmpty()) {
                    Toast.makeText(CreateEvent.this, "Not valid values", Toast.LENGTH_SHORT).show();

                } else {
                    //send data to backendless
                    HashMap eventData = new HashMap<>();
                    eventData.put("title", editTextCreateTitle.getText().toString());
                    eventData.put("description", editTextCreateDescription.getText().toString());
                    eventData.put("time", editTextCreateTime.getText().toString());
                    eventData.put("place", editTextCreatePlace.getText().toString());

                    Backendless.Data.of("EVENT").save(eventData, new AsyncCallback<Map>() {
                        @Override
                        public void handleResponse(Map response) {
                            Toast.makeText(CreateEvent.this, "Event Created Succefully ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(CreateEvent.this, EventList.class);
                            startActivity(i);
                            finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(CreateEvent.this, "Error Creating Event ", Toast.LENGTH_SHORT).show();


                        }
                    });
                }


            }
        });


    }
}
