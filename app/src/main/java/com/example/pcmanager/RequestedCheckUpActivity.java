package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestedCheckUpActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7, button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_checkup);
/*
        button1 = findViewById(R.id.button1);
        button1.setEnabled(false);
        button2 = findViewById(R.id.button2);
        button2.setEnabled(false);
        button3 = findViewById(R.id.button3);
        button3.setEnabled(false);
        button4 = findViewById(R.id.button3);
        button4.setEnabled(false);
        button5 = findViewById(R.id.button5);
        button5.setEnabled(false);
        button6 = findViewById(R.id.button6);
        button6.setEnabled(false);
        button8 = findViewById(R.id.button8);
        button8.setEnabled(false);
*/
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                Intent intent1 = new Intent(this, Mold.class);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, Covering.class);
                startActivity(intent2);
                break;

            case R.id.button3:
                Intent intent3 = new Intent(this, Rebar.class);
                startActivity(intent3);
                break;

            case R.id.button4:
                Intent intent4 = new Intent(this, Concrete.class);
                startActivity(intent4);
                break;

            case R.id.button5:
                Intent intent5 = new Intent(this, Strength.class);
                startActivity(intent5);
                break;

            case R.id.button6:
                Intent intent6 = new Intent(this, DesignStrength.class);
                startActivity(intent6);
                break;

            case R.id.button7:
                Intent intent7 = new Intent(this, ReqSize.class);
                startActivity(intent7);
                break;

            case R.id.button8:
                Intent intent8 = new Intent(this, CrackDamage.class);
                startActivity(intent8);
                break;
        }
    }
}
