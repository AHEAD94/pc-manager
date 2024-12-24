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

public class ErrorFloor extends AppCompatActivity {

    // for uploading data
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    int position;

    EditText TextInputEditText_length;
    EditText TextInputEditText_width;
    EditText TextInputEditText_thickness;
    EditText TextInputEditText_diagonal;

    TextView TV_result1;
    TextView TV_result2;
    TextView TV_result3;
    TextView TV_result4;

    // 임의의 부재치수
    int length = 5000;
    int width = 3000;
    int thickness = 300;
    int diagonal = 5100;

    String lenErr = null;
    String widErr = null;
    String thiErr = null;
    String diaErr = null;

    Button button1;
    int lock[] = new int[4];
    Button buttonRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_floor);

        for (int i = 0; i < 4; i++) {
            lock[i] = 0;
        }

        Intent intent = getIntent();
        button1 = findViewById(R.id.button1);
        button1.setEnabled(false);
        buttonRepair = findViewById(R.id.buttonRepair);
        buttonRepair.setEnabled(false);

        TextView moduleNo = (TextView) findViewById(R.id.tv_moduleNo3);
        TextView state = (TextView) findViewById(R.id.tv_state);
        TextView TV_length = (TextView) findViewById(R.id.tv_length_rs);
        TextView TV_width = (TextView) findViewById(R.id.tv_width_rs);
        TextView TV_thickness = (TextView) findViewById(R.id.tv_thickness_rs);
        TextView TV_diagonal = (TextView) findViewById(R.id.tv_diagonal_rs);

        moduleNo.setText(intent.getStringExtra("moduleNo"));
        state.setText(intent.getStringExtra("state"));
        TV_length.setText(intent.getStringExtra("length"));
        TV_width.setText(intent.getStringExtra("width"));
        TV_thickness.setText(intent.getStringExtra("thickness"));
        TV_diagonal.setText(intent.getStringExtra("diagonal"));

        String stPosition = intent.getStringExtra("moduleNo");
        stPosition = stPosition.substring(2);
        position = Integer.parseInt(stPosition);
        Log.d("POSITION!!!", Integer.toString(position));

        TextInputEditText_length = findViewById(R.id.editText1);
        TextInputEditText_width = findViewById(R.id.editText2);
        TextInputEditText_thickness = findViewById(R.id.editText3);
        TextInputEditText_diagonal = findViewById(R.id.editText4);

        TextInputEditText_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lock[0] = 1;
                int unlock = 0;
                for (int i = 0; i < 4; i++) unlock += lock[i];
                if (unlock == 4) button1.setEnabled(true);

                int len = Integer.parseInt( "" + TextInputEditText_length.getText().toString());
                if (Math.abs(len - length) > 7) { // 길이검사
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
                for (int i = 0; i < 4; i++) unlock += lock[i];
                if (unlock == 4) button1.setEnabled(true);

                int wid = Integer.parseInt( "" + TextInputEditText_width.getText().toString());
                if (Math.abs(wid - width) > 3) { // 폭검사
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
        TextInputEditText_thickness.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lock[2] = 1;
                int unlock = 0;
                for (int i = 0; i < 4; i++) unlock += lock[i];
                if (unlock == 4) button1.setEnabled(true);

                int thi = Integer.parseInt( "" + TextInputEditText_thickness.getText().toString());
                if (thi - thickness > 5 || thi - thickness < -2) { // 두께검사
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
        TextInputEditText_diagonal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lock[3] = 1;
                int unlock = 0;
                for (int i = 0; i < 4; i++) unlock += lock[i];
                if (unlock == 4) button1.setEnabled(true);

                int dia = Integer.parseInt( "" + TextInputEditText_diagonal.getText().toString());
                if (dia - diagonal > 10 || dia - diagonal < -2) { // 대각선검사
                    TV_result4 = findViewById(R.id.textView_result4);
                    TV_result4.setText("불합격");
                    TV_result4.setTextColor(Color.parseColor("#EE0000"));
                }
                else {
                    TV_result4 = findViewById(R.id.textView_result4);
                    TV_result4.setText("합격");
                    TV_result4.setTextColor(Color.parseColor("#00AA00"));
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
                int thi = Integer.parseInt( "" + TextInputEditText_thickness.getText().toString());
                int dia = Integer.parseInt( "" + TextInputEditText_diagonal.getText().toString());

                if (Math.abs(len - length) > 7) { // 길이검사
                    lenErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("length").setValue(TextInputEditText_length.getText().toString()+"_부적합"); // 5000
                    //lengthTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("length").setValue(TextInputEditText_length.getText().toString()+"_적합"); // 5000
                    lenErr = "적합";
                    //lengthTv.setText("합격");
                }

                if (Math.abs(wid - width) > 3) { // 폭검사
                    widErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("width").setValue(TextInputEditText_width.getText().toString()+"_부적합"); // 3002
                    //widthTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("width").setValue(TextInputEditText_width.getText().toString()+"_적합"); // 3002
                    widErr = "적합";
                    //widthTv.setText("합격");
                }

                if ((thi - thickness > 5) || (thi - thickness < -2)) { // 두께검사
                    thiErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("thickness").setValue(TextInputEditText_thickness.getText().toString()+"_부적합"); // 304
                    //thicknessTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("thickness").setValue(TextInputEditText_thickness.getText().toString()+"_적합"); // 304
                    thiErr = "적합";
                    //thicknessTv.setText("합격");
                }

                if ((dia - diagonal > 10) || (dia - diagonal < -2)) { // 대각선검사
                    diaErr = "부적합";
                    databaseReference.child("modules").child("mID"+position).child("size").child("diagonal").setValue(TextInputEditText_diagonal.getText().toString()+"_부적합"); // 5097
                    //diagonalTv.setText("부적합");
                }
                else {
                    databaseReference.child("modules").child("mID"+position).child("size").child("diagonal").setValue(TextInputEditText_diagonal.getText().toString()+"_적합"); // 5097
                    diaErr = "적합";
                    //diagonalTv.setText("합격");
                }

                Toast.makeText(getApplicationContext(), "길이 : "+lenErr+" ,  폭 : "+widErr+" ,  두께 : "+thiErr+" ,  대각선 : "+diaErr, Toast.LENGTH_LONG).show();

                if (lenErr == "적합" && widErr == "적합" && thiErr == "적합" && diaErr == "적합") {
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
