package com.example.pcmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:

                break;

            case R.id.button2:

                break;

            case R.id.prebutton:
                finish();
                break;
        }
    }
}
