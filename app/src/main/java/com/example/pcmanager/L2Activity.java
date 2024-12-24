package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class L2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l2);
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
                Intent intent3 = new Intent(this, RequestedCheckUpActivity.class);
                startActivity(intent3);
                break;

            case R.id.button4: //
                Intent intent4 = new Intent(this, TestReport.class);
                startActivity(intent4);
                break;
        }
    }
}
