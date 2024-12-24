package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class L1Activity extends AppCompatActivity {

    //Button RepairButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1);

        //RepairButton = findViewById(R.id.button1);
        //RepairButton.setEnabled(false);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:

                Intent intent1 = new Intent(this, RepairActivity.class);
                startActivity(intent1);
                break;

            case R.id.button2:
                Intent intent2 = new Intent(this, ModuleListL1.class);
                startActivity(intent2);
                break;
        }
    }
}
