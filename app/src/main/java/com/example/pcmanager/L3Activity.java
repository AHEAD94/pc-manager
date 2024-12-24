package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class L3Activity extends AppCompatActivity {

    Button button4;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3);

        button4 = findViewById(R.id.button3);
        button4.setEnabled(false);
        button5 = findViewById(R.id.button5);
        button5.setEnabled(false);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1: //
                Intent intent1 = new Intent(this, ProjectActivity.class);
                startActivity(intent1);
                break;

            case R.id.button2: //

                break;

            case R.id.button3: //

                break;

            case R.id.button4: // 연락처

                break;

            case R.id.prebutton:
                finish();
                break;
        }
    }
}
