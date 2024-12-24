package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText TextInputEditText_ESN;
    EditText TextInputEditText_password;
    Button LoginButton;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    List<Object> Array = new ArrayList<>();



    // info
    String ESN_1 = "1234"; // Level1
    String Pass_1 = "1";

    String ESN_2 = "1235"; // Level2
    String Pass_2 = "12";

    String ESN_3 = "1236"; // Level3
    String Pass_3 = "123";

    String ESN_ad = "1237"; // Admin
    String Pass_ad = "1234";


    //check_equal
    public boolean validation(String ESN, String password) {
        return input_ESN.equals(ESN) && input_password.equals(password);
    }

    String input_ESN = null, input_password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        // Level 1
        databaseReference.child("Employee").child("eID1").child("ESN").setValue("1234");
        databaseReference.child("Employee").child("eID1").child("password").setValue("1");
        // Level 2
        databaseReference.child("Employee").child("eID2").child("ESN").setValue("1235");
        databaseReference.child("Employee").child("eID2").child("password").setValue("12");
        // Level 3
        databaseReference.child("Employee").child("eID3").child("ESN").setValue("1236");
        databaseReference.child("Employee").child("eID3").child("password").setValue("123");
        // Admin
        databaseReference.child("Employee").child("eID4").child("ESN").setValue("1237");
        databaseReference.child("Employee").child("eID4").child("password").setValue("1234");
 */

        TextInputEditText_ESN = findViewById(R.id.TextInputEditText_ESN);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        LoginButton = findViewById(R.id.LoginButton);

        //deactivate login button
        LoginButton.setEnabled(false);

        //user input sensing method
        TextInputEditText_ESN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Texting on ESN part", s+ "/" + count + " " + start + ", "+before);
                String ESN = TextInputEditText_ESN.getText().toString();
                input_ESN = ESN;
                if (input_ESN != null && input_password != null) LoginButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Texting on ESN part", s+ "/" + count + " " + start + ", "+before);
                String password = TextInputEditText_password.getText().toString();
                input_password = password;

                if (input_ESN != null && input_password != null) LoginButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ESN = TextInputEditText_ESN.getText().toString();
                final String password = TextInputEditText_password.getText().toString();
/*
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot employees : dataSnapshot.getChildren()) {
                            //String emp = employees.getValue().toString();
                            //Array.add(emp);
                            for (DataSnapshot employee : dataSnapshot.getChildren()) {
                                if (ESN.equals(employees.getValue().toString()) && password.equals(employees.getValue().toString())) {

                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
 */
                if (ESN.equals(ESN_1) && password.equals(Pass_1)) {
                    Intent intent = new Intent(getBaseContext(), L1Activity.class);
                    startActivity(intent);
                }
                else if (ESN.equals(ESN_2) && password.equals(Pass_2)) {
                    Intent intent = new Intent(getBaseContext(), L2Activity.class);
                    startActivity(intent);
                }
                else if (ESN.equals(ESN_3) && password.equals(Pass_3)) {
                    Intent intent = new Intent(getBaseContext(), L3Activity.class);
                    startActivity(intent);
                }
                else if (ESN.equals(ESN_ad) && password.equals(Pass_ad)) {
                    Intent intent = new Intent(getBaseContext(), L4Activity.class);
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(), "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ClientLogin:
                Intent intent = new Intent(this, ClientActivity.class);
                startActivity(intent);
                break;
        }
    }
}