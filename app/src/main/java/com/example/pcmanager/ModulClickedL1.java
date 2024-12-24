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

public class ModulClickedL1 extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_clicked_l1);

        Intent intent = getIntent();

        TextView moduleNo = (TextView) findViewById(R.id.tv_moduleNo);
        TextView producer = (TextView) findViewById(R.id.tv_producer);
        TextView starting = (TextView) findViewById(R.id.tv_starting);
        TextView finishing = (TextView) findViewById(R.id.tv_finishing);

        TextView length = (TextView) findViewById(R.id.tv_length);
        TextView height = (TextView) findViewById(R.id.tv_height);
        TextView width = (TextView) findViewById(R.id.tv_width);
        TextView depth = (TextView) findViewById(R.id.tv_depth);
        TextView thickness = (TextView) findViewById(R.id.tv_thickness);
        TextView diagonal = (TextView) findViewById(R.id.tv_diagonal);

        TextView rebar = (TextView) findViewById(R.id.tv_rebar);
        TextView covering = (TextView) findViewById(R.id.tv_covering);
        TextView mold = (TextView) findViewById(R.id.tv_mold);
        TextView concrete = (TextView) findViewById(R.id.tv_concrete);
        TextView strength = (TextView) findViewById(R.id.tv_strength);
        TextView size = (TextView) findViewById(R.id.tv_size);
        TextView crackDamage = (TextView) findViewById(R.id.tv_crackDamage);

        moduleNo.setText(intent.getStringExtra("moduleNo"));
        producer.setText(intent.getStringExtra("producer"));
        starting.setText(intent.getStringExtra("starting"));
        finishing.setText(intent.getStringExtra("finishing"));

        length.setText(intent.getStringExtra("length"));
        height.setText(intent.getStringExtra("height"));
        width.setText(intent.getStringExtra("width"));
        depth.setText(intent.getStringExtra("depth"));
        thickness.setText(intent.getStringExtra("thickness"));
        diagonal.setText(intent.getStringExtra("diagonal"));

        rebar.setText(intent.getStringExtra("rebar"));
        covering.setText(intent.getStringExtra("covering"));
        mold.setText(intent.getStringExtra("mold"));
        concrete.setText(intent.getStringExtra("concrete"));
        strength.setText(intent.getStringExtra("strength"));
        size.setText(intent.getStringExtra("size"));
        crackDamage.setText(intent.getStringExtra("crackDamage"));

        String stPosition = intent.getStringExtra("moduleNo");
        stPosition = stPosition.substring(2);
        position = Integer.parseInt(stPosition);
        Log.d("POSITION!!!", Integer.toString(position));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {

        String trans = String.format("%06d", position);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String getTime = simpleDate.format(mDate);

        switch (view.getId()) {
            case R.id.bt_req_rebar:
                databaseReference.child("modules").child("mID"+position).child("test").child("rebar").setValue("요청됨");

                databaseReference.child("requested").child("rebar").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("rebar").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("rebar").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("rebar").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("rebar").child("mID"+position).child("test").child("mold").setValue("미검사");
                databaseReference.child("requested").child("rebar").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("rebar").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("rebar").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("rebar").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("rebar").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;

            case R.id.bt_req_covering:
                databaseReference.child("modules").child("mID"+position).child("test").child("covering").setValue("요청됨");

                databaseReference.child("requested").child("covering").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("covering").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("covering").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("covering").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("covering").child("mID"+position).child("test").child("mold").setValue("미검사");
                databaseReference.child("requested").child("covering").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("covering").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("covering").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("covering").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("covering").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;

            case R.id.bt_req_mold:
                databaseReference.child("modules").child("mID"+position).child("test").child("mold").setValue("요청됨");

                databaseReference.child("requested").child("mold").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("mold").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("mold").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("mold").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("mold").child("mID"+position).child("test").child("mold").setValue("미검사");
                databaseReference.child("requested").child("mold").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("mold").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("mold").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("mold").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("mold").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;

            case R.id.bt_req_concrete:
                databaseReference.child("modules").child("mID"+position).child("test").child("concrete").setValue("요청됨");

                databaseReference.child("requested").child("concrete").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("concrete").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("concrete").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("concrete").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("concrete").child("mID"+position).child("test").child("concrete").setValue("미검사");
                databaseReference.child("requested").child("concrete").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("concrete").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("concrete").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("concrete").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("concrete").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;

            case R.id.bt_req_strength:
                databaseReference.child("modules").child("mID"+position).child("test").child("strength").setValue("요청됨");

                databaseReference.child("requested").child("strength").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("strength").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("strength").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("strength").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("strength").child("mID"+position).child("test").child("mold").setValue("미검사");
                databaseReference.child("requested").child("strength").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("strength").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("strength").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("strength").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("strength").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;

            case R.id.bt_req_size:
                databaseReference.child("modules").child("mID"+position).child("test").child("size").setValue("요청됨");

                databaseReference.child("requested").child("size").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("size").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("size").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("size").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("size").child("mID"+position).child("test").child("size").setValue("미검사");
                databaseReference.child("requested").child("size").child("mID"+position).child("test").child("request").setValue(getTime);
                // 사진
                databaseReference.child("requested").child("size").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("requested").child("size").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("requested").child("size").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("requested").child("size").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;

            case R.id.bt_req_crackDamage:
                databaseReference.child("modules").child("mID"+position).child("test").child("crack or damage").setValue("요청됨");

                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("requested").child("crack or damage").child("mID"+position).child("test").child("size").setValue("미검사");
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

    public ModulClickedL1() {

    }

    public void setPosition(int position) {
        this.position = position;
    }
}
