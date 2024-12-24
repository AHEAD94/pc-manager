package com.example.pcmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RepairActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1: //
                Intent intent1 = new Intent(this, RepairMoldList.class);
                startActivity(intent1);
                break;

            case R.id.button2: //
                Intent intent2 = new Intent(this, RepairCoveringList.class);
                startActivity(intent2);
                break;

            case R.id.button3: //
                Intent intent3 = new Intent(this, RepairRebarList.class);
                startActivity(intent3);
                break;

            case R.id.button4: //
                Intent intent4 = new Intent(this, RepairConcreteList.class);
                startActivity(intent4);
                break;

            case R.id.button5: //
                Intent intent5 = new Intent(this, RepairStrengthList.class);
                startActivity(intent5);
                break;

            case R.id.button6: //
                Intent intent6 = new Intent(this, RepairStrengthList.class);
                startActivity(intent6);
                break;

            case R.id.button7: //
                Intent intent7 = new Intent(this, RepairSizeList.class);
                startActivity(intent7);
                break;

            case R.id.button8: //
                Intent intent8 = new Intent(this, RepairCrackDamageList.class);
                startActivity(intent8);
                break;
        }
    }
}
