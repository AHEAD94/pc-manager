package com.example.pcmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ModuleListL1 extends AppCompatActivity { // 서명 저장 기능 추가(계정 당)

    // for listView
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;
    int count;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    public ArrayList<Module> moduleArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_list_l1);

        listView = (ListView) findViewById(R.id.listviewmodule);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        // clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                moduleArray.get(position).setModuleInfo();

                Intent intent = new Intent(getApplicationContext(), ModulClickedL1.class);
                intent.putExtra("moduleNo", moduleArray.get(position).getModuleNo());
                intent.putExtra("producer", moduleArray.get(position).getProducer());
                intent.putExtra("starting", moduleArray.get(position).getStarting());
                intent.putExtra("finishing", moduleArray.get(position).getFinishing());

                intent.putExtra("length", moduleArray.get(position).getLength());
                intent.putExtra("height", moduleArray.get(position).getHeight());
                intent.putExtra("width", moduleArray.get(position).getWidth());
                intent.putExtra("depth", moduleArray.get(position).getDepth());
                intent.putExtra("thickness", moduleArray.get(position).getThickness());
                intent.putExtra("diagonal", moduleArray.get(position).getDiagonal());

                intent.putExtra("rebar", moduleArray.get(position).getRebar());
                intent.putExtra("covering", moduleArray.get(position).getCovering());
                intent.putExtra("mold", moduleArray.get(position).getMold());
                intent.putExtra("concrete", moduleArray.get(position).getConcrete());
                intent.putExtra("strength", moduleArray.get(position).getStrength());
                intent.putExtra("size", moduleArray.get(position).getSize());
                intent.putExtra("crackDamage", moduleArray.get(position).getCrackDamage());

                Log.d("THE POSITION CLICKED", Integer.toString(position));

                startActivity(intent);
            }
        });

        // ValueEventLIstener
        mReference = mDatabase.getReference("modules");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                moduleArray.clear();
                for (DataSnapshot moduleData : dataSnapshot.getChildren()) {
                    String md = moduleData.getValue().toString();
                    Module tempModule = new Module(md);

                    moduleArray.add(tempModule);
                    adapter.add("부재번호 : "+tempModule.getModuleNo()+" ,  부재상태 : "+tempModule.getState());
                }

                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);
                count = adapter.getCount()+1;
                TextView textView = findViewById(R.id.tvCount);
                textView.setText("등록된 부재 수: " + (count-1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // for uploading data
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String getTime = simpleDate.format(mDate);

        switch (view.getId()) {
            case R.id.button1:
                //databaseReference.child("message").push().setValue("호우!");

                // 부재번호
                String trans = String.format("%06d", count);
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("No").setValue("SN"+trans);
                // 제작
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("fabrication").child("state").setValue("진행중");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("fabrication").child("date").child("starting").setValue(getTime);
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("fabrication").child("date").child("finishing").setValue("생산 진행중"); // 마지막 부재검사 요청 시
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("fabrication").child("producer").setValue("현재작업자");
                // 형상
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("size").child("length").setValue("미측정"); // 5000
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("size").child("height").setValue("미측정");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("size").child("width").setValue("미측정"); // 3002
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("size").child("depth").setValue("미측정");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("size").child("thickness").setValue("미측정"); // 304
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("size").child("diagonal").setValue("미측정"); // 5097
                // 검사결과
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("mold").setValue("미검사");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("covering").setValue("미검사");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("rebar").setValue("미검사");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("concrete").setValue("미검사");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("strength").setValue("미검사");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("size").setValue("미검사");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("test").child("crack or damage").setValue("미검사");
                // 수선
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("repair").child("item").setValue("내역 없음");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("repair").child("date").setValue("내역 없음");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("repair").child("repairer").setValue("내역 없음");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("repair").child("history").setValue("내역 없음");
                // 사진
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("photo").child("item").setValue("미등록");
                // 보고서
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("report").child("preliminary").setValue("미등록");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("report").child("certificate").setValue("미등록");
                databaseReference.child("modules").child("mID"+Integer.toString(count)).child("report").child("abrogation").setValue("미등록");

                count++;

                break;
        }
    }
}
