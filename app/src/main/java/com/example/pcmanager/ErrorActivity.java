package com.example.pcmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {

    String modNo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        Intent intent = getIntent();

        TextView moduleNo = (TextView) findViewById(R.id.tv_moduleNo2);
        TextView producer = (TextView) findViewById(R.id.tv_producer2);
        TextView starting = (TextView) findViewById(R.id.tv_starting2);
        TextView finishing = (TextView) findViewById(R.id.tv_finishing2);
        TextView requested = (TextView) findViewById(R.id.tv_requested);

        moduleNo.setText(intent.getStringExtra("moduleNo"));
        producer.setText(intent.getStringExtra("producer"));
        starting.setText(intent.getStringExtra("starting"));
        finishing.setText(intent.getStringExtra("finishing"));
        requested.setText(intent.getStringExtra("requested"));

        modNo = moduleNo.getText().toString();
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                Intent intent1 = new Intent(this, ErrorFloor.class);
                intent1.putExtra("moduleNo", modNo);
                intent1.putExtra("state", modNo);
                intent1.putExtra("length", modNo);
                intent1.putExtra("width", modNo);
                intent1.putExtra("thickness", modNo);
                intent1.putExtra("diagonal", modNo);

                startActivity(intent1);
                break;

            case R.id.button2:
                Intent intent2 = new Intent(this, ErrorSlab.class);
                intent2.putExtra("moduleNo", modNo);
                intent2.putExtra("state", modNo);
                intent2.putExtra("length", modNo);
                intent2.putExtra("width", modNo);
                intent2.putExtra("depth", modNo);

                startActivity(intent2);
                break;

            case R.id.button3:
                Intent intent3 = new Intent(this, ErrorColumn.class);
                intent3.putExtra("moduleNo", modNo);
                intent3.putExtra("state", modNo);
                intent3.putExtra("length", modNo);
                intent3.putExtra("width", modNo);
                intent3.putExtra("depth", modNo);

                startActivity(intent3);
                break;

            case R.id.button4:
                Intent intent4 = new Intent(this, ErrorWall.class);
                intent4.putExtra("moduleNo", modNo);
                intent4.putExtra("state", modNo);
                intent4.putExtra("height", modNo);
                intent4.putExtra("width", modNo);
                intent4.putExtra("thickness", modNo);
                intent4.putExtra("diagonal", modNo);

                startActivity(intent4);
                break;

            case R.id.button5:
                Intent intent5 = new Intent(this, ErrorStair.class);
                intent5.putExtra("moduleNo", modNo);
                intent5.putExtra("state", modNo);
                intent5.putExtra("length", modNo);
                intent5.putExtra("width", modNo);

                startActivity(intent5);
                break;

            case R.id.button6:
                Intent intent6 = new Intent(this, ErrorOpen.class);
                intent6.putExtra("moduleNo", modNo);
                intent6.putExtra("state", modNo);
                intent6.putExtra("length", modNo);
                intent6.putExtra("width", modNo);
                intent6.putExtra("diagonal", modNo);

                startActivity(intent6);
                break;
        }
    }
}
