package com.example.pcmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class StrengthClicked extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private int position;

    private int cover = 0;

    Button button1;
    Button button2;
    Button repairing;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;

    String concrete = "불";
    String rebar = "불";
    String covering = "불";
    String size = "불";
    String mold = "불";
    String strength = "불";
    String crackDamage = "불";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_clicked);

        Intent intent = getIntent();

        TextView moduleNo = findViewById(R.id.tv_moduleNo2);
        TextView producer = findViewById(R.id.tv_producer2);
        TextView starting = findViewById(R.id.tv_starting2);
        TextView finishing = findViewById(R.id.tv_finishing2);
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

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        repairing = findViewById(R.id.button8);
        repairing.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                cover = 1;
                button1.setBackgroundColor(Color.parseColor("#00AA00"));

                break;

            case R.id.button2:
                cover = -1;
                button2.setBackgroundColor(Color.parseColor("#EE0000"));

                break;

            case R.id.button7:
                if (this.cover == 1) {
                    databaseReference.child("requested").child("strength").child("mID" + position).removeValue();
                    databaseReference.child("modules").child("mID" + position).child("test").child("strength").setValue("합격");

                    // ValueEventLIstener
                    mReference = mDatabase.getReference("modules").child("mID" + position).child("test");
                    mReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String md = dataSnapshot.getValue().toString();
                            Log.d("RESULT????", md);
                            String tempSt = md;
                            String cutSt;

                            cutSt = tempSt.substring(tempSt.indexOf("concrete")+9, tempSt.indexOf(","));
                            concrete = cutSt;
                            tempSt = tempSt.substring(tempSt.indexOf(",")+2);

                            cutSt = tempSt.substring(tempSt.indexOf("covering")+9, tempSt.indexOf(","));
                            covering = cutSt;
                            tempSt = tempSt.substring(tempSt.indexOf(",")+2);

                            cutSt = tempSt.substring(tempSt.indexOf("size")+5, tempSt.indexOf(","));
                            size = cutSt;
                            tempSt = tempSt.substring(tempSt.indexOf(",")+2);

                            cutSt = tempSt.substring(tempSt.indexOf("strength")+9, tempSt.indexOf(","));
                            strength = cutSt;
                            tempSt = tempSt.substring(tempSt.indexOf(",")+2);

                            cutSt = tempSt.substring(tempSt.indexOf("damage")+7, tempSt.indexOf(","));
                            crackDamage = cutSt;
                            tempSt = tempSt.substring(tempSt.indexOf(",")+2);

                            cutSt = tempSt.substring(tempSt.indexOf("rebar")+6, tempSt.indexOf(","));
                            rebar = cutSt;
                            tempSt = tempSt.substring(tempSt.indexOf(",")+2);

                            cutSt = tempSt.substring(tempSt.indexOf("mold")+5, tempSt.indexOf("}"));
                            mold = cutSt;

                            if (rebar.equals("합격") && covering.equals("합격") && crackDamage.equals("합격") && mold.equals("합격")
                                    && rebar.equals("합격") && size.equals("합격") && strength.equals("합격") &&concrete.equals("합격")) {
                                long now = System.currentTimeMillis();
                                Date mDate = new Date(now);
                                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                                String getTime = simpleDate.format(mDate);
                                databaseReference.child("modules").child("mID" + position).child("fabrication").child("date").child("finishing").setValue(getTime);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    databaseReference.child("modules").child("mID" + position).child("test").child("strength").setValue("불합격");
                    repairing.setEnabled(true);
                }

                break;

            case R.id.button8:
                String trans = String.format("%06d", position);
                databaseReference.child("modules").child("mID"+position).child("test").child("strength").setValue("수선 요청됨");

                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String getTime = simpleDate.format(mDate);
                databaseReference.child("repairing").child("strength").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("repairing").child("strength").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("repairing").child("strength").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("repairing").child("strength").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("repairing").child("strength").child("mID"+position).child("request").setValue(getTime);
                // 사진
                databaseReference.child("repairing").child("strength").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("repairing").child("strength").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("repairing").child("strength").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("repairing").child("strength").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;
        }
    }
}
