package com.example.pcmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class RepairCrackDamageClicked extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_crack_damage_clicked);

        Intent intent = getIntent();

        TextView moduleNo = (TextView) findViewById(R.id.tv_moduleNo);
        TextView producer = (TextView) findViewById(R.id.tv_producer);
        TextView starting = (TextView) findViewById(R.id.tv_starting);
        TextView finishing = (TextView) findViewById(R.id.tv_finishing);
        TextView requested = findViewById(R.id.tv_requested);

        moduleNo.setText(intent.getStringExtra("moduleNo"));
        producer.setText(intent.getStringExtra("producer"));
        starting.setText(intent.getStringExtra("starting"));
        finishing.setText(intent.getStringExtra("finishing"));
        requested.setText(intent.getStringExtra("requested"));

        String stPosition = intent.getStringExtra("moduleNo");
        stPosition = stPosition.substring(2);
        position = Integer.parseInt(stPosition);
        Log.d("POSITION!!!", Integer.toString(position));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button15:
                String trans = String.format("%06d", position);
                databaseReference.child("modules").child("mID"+position).child("test").child("crack or damage").setValue("재검사 요청됨");
                databaseReference.child("repairing").child("crack or damage").child("mID"+position).removeValue();

                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String getTime = simpleDate.format(mDate);
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("test").child("crack or damage").setValue("재검사");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;
        }
    }
}
