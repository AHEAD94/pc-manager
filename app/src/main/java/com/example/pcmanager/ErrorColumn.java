package com.example.pcmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ErrorColumn extends AppCompatActivity {

    // for uploading data
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    int position;

    EditText TextInputEditText_length;
    EditText TextInputEditText_width;
    EditText TextInputEditText_depth;

    TextView TV_result1;
    TextView TV_result2;
    TextView TV_result3;

    // 임의의 부재치수
    int length = 5000;
    int width = 3000;
    int depth = 300;

    String lenErr = null;
    String widErr = null;
    String depErr = null;

    Button button1;
    Button buttonRepair;
    int lock[] = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_column);

        for (int i = 0; i < 3; i++) {
            lock[i] = 0;
        }

        Intent intent = getIntent();
        button1 = findViewById(R.id.button1);
        button1.setEnabled(false);
        buttonRepair = findViewById(R.id.buttonRepair);
        buttonRepair.setEnabled(false);

        TextView moduleNo = findViewById(R.id.tv_moduleNo3);
        TextView state = findViewById(R.id.tv_state);
        TextView TV_length = findViewById(R.id.tv_length_rs);
        TextView TV_width = findViewById(R.id.tv_width_rs);
        TextView TV_depth = findViewById(R.id.tv_depth_rs);

        moduleNo.setText(intent.getStringExtra("moduleNo"));
        state.setText(intent.getStringExtra("state"));
        TV_length.setText(intent.getStringExtra("length"));
        TV_width.setText(intent.getStringExtra("width"));
        TV_depth.setText(intent.getStringExtra("depth"));

        String stPosition = intent.getStringExtra("moduleNo");
        stPosition = stPosition.substring(2);
        position = Integer.parseInt(stPosition);
        Log.d("POSITION!!!", Integer.toString(position));

        TextInputEditText_length = findViewById(R.id.editText1);
        TextInputEditText_width = findViewById(R.id.editText2);
        TextInputEditText_depth = findViewById(R.id.editText3);

        TextInputEditText_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lock[0] = 1;
                int unlock = 0;
                for (int i = 0; i < 3; i++) unlock += lock[i];
                if (unlock == 3) button1.setEnabled(true);

                int len = Integer.parseInt( "" + TextInputEditText_length.getText().toString());
                if (Math.abs(len - length) > 13) { // 길이검사
                    TV_result1 = findViewById(R.id.textView_result1);
                    TV_result1.setText("불합격");
                    TV_result1.setTextColor(Color.parseColor("#EE0000"));
                }
                else {
                    TV_result1 = findViewById(R.id.textView_result1);
                    TV_result1.setText("합격");
                    TV_result1.setTextColor(Color.parseColor("#00AA00"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextInputEditText_width.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lock[1] = 1;
                int unlock = 0;
                for (int i = 0; i < 3; i++) unlock += lock[i];
                if (unlock == 3) button1.setEnabled(true);

                int wid = Integer.parseInt( "" + TextInputEditText_width.getText().toString());
                if (Math.abs(wid - width) > 6) { // 폭검사
                    TV_result2 = findViewById(R.id.textView_result2);
                    TV_result2.setText("불합격");
                    TV_result2.setTextColor(Color.parseColor("#EE0000"));
                }
                else {
                    TV_result2 = findViewById(R.id.textView_result2);
                    TV_result2.setText("합격");
                    TV_result2.setTextColor(Color.parseColor("#00AA00"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextInputEditText_depth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lock[2] = 1;
                int unlock = 0;
                for (int i = 0; i < 3; i++) unlock += lock[i];
                if (unlock == 3) button1.setEnabled(true);

                int dep = Integer.parseInt( "" + TextInputEditText_depth.getText().toString());
                if (Math.abs(dep - depth) > 6) { // 깊이검사
                    TV_result3 = findViewById(R.id.textView_result3);
                    TV_result3.setText("불합격");
                    TV_result3.setTextColor(Color.parseColor("#EE0000"));
                }
                else {
                    TV_result3 = findViewById(R.id.textView_result3);
                    TV_result3.setText("합격");
                    TV_result3.setTextColor(Color.parseColor("#00AA00"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button1:
                int len = Integer.parseInt( "" + TextInputEditText_length.getText().toString());
                int wid = Integer.parseInt( "" + TextInputEditText_width.getText().toString());
                int dep = Integer.parseInt( "" + TextInputEditText_depth.getText().toString());

                if (Math.abs(len - length) > 13) { // 길이검사
                    lenErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("length").setValue(TextInputEditText_length.getText().toString()+"_부적합"); // 5000
                    //lengthTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("length").setValue(TextInputEditText_length.getText().toString()+"_적합"); // 5000
                    lenErr = "적합";
                    //lengthTv.setText("합격");
                }

                if (Math.abs(wid - width) > 6) { // 폭검사
                    widErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("width").setValue(TextInputEditText_width.getText().toString()+"_부적합"); // 3002
                    //widthTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("width").setValue(TextInputEditText_width.getText().toString()+"_적합"); // 3002
                    widErr = "적합";
                    //widthTv.setText("합격");
                }

                if (Math.abs(dep - depth) > 6) { // 깊이검사
                    depErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("depth").setValue(TextInputEditText_depth.getText().toString()+"_부적합"); // 304
                    //thicknessTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("depth").setValue(TextInputEditText_depth.getText().toString()+"_적합"); // 304
                    depErr = "적합";
                    //thicknessTv.setText("합격");
                }

                Toast.makeText(getApplicationContext(), "길이 : "+lenErr+" ,  폭 : "+widErr+" ,  깊이 : "+depErr, Toast.LENGTH_LONG).show();

                if (lenErr == "적합" && widErr == "적합" && depErr == "적합") {
                    databaseReference.child("requested").child("size").child("mID"+position).removeValue();
                    databaseReference.child("modules").child("mID"+position).child("test").child("size").setValue("합격");
                    Log.d("delete!!!", "mID"+position);
                }
                else {
                    buttonRepair.setEnabled(true); // 수선으로 보내기
                    databaseReference.child("modules").child("mID"+position).child("test").child("size").setValue("불합격");
                    databaseReference.child("requested").child("size").child("mID"+position).child("test").child("size").setValue("불합격");
                }
                break;

            case R.id.buttonRepair:
                String trans = String.format("%06d", position);
                databaseReference.child("modules").child("mID"+position).child("test").child("size").setValue("수선 요청됨");

                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String getTime = simpleDate.format(mDate);
                databaseReference.child("repairing").child("size").child("mID"+position).child("No").setValue("SN"+trans);
                databaseReference.child("repairing").child("size").child("mID"+position).child("fabrication").child("date").child("starting").setValue("현재일시");
                databaseReference.child("repairing").child("size").child("mID"+position).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("repairing").child("size").child("mID"+position).child("fabrication").child("producer").setValue("현재작업자");
                databaseReference.child("repairing").child("size").child("mID"+position).child("request").setValue(getTime);
                // 사진
                databaseReference.child("repairing").child("size").child("mID"+position).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("repairing").child("size").child("mID"+position).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("repairing").child("size").child("mID"+position).child("report").child("certificate").setValue("미등록");
                databaseReference.child("repairing").child("size").child("mID"+position).child("report").child("abrogation").setValue("미등록");
                break;
        }
    }
}
