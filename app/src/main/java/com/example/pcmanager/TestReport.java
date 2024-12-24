package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class TestReport extends AppCompatActivity {

    // for listView
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;
    int count;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    public ArrayList<Module> moduleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);

        listView = (ListView) findViewById(R.id.lv_report);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        // clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                //reqMoldArray.get(position).setModuleInfo();

                Intent intent = new Intent(getApplicationContext(), TestReportClicked.class);
                intent.putExtra("moduleNo", moduleArrayList.get(position).getModuleNo());
                intent.putExtra("producer", moduleArrayList.get(position).getProducer());
                intent.putExtra("starting", moduleArrayList.get(position).getStarting());
                intent.putExtra("finishing", moduleArrayList.get(position).getFinishing());
                intent.putExtra("requested", moduleArrayList.get(position).getTime());

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
                moduleArrayList.clear();

                for (DataSnapshot moduleData : dataSnapshot.getChildren()) {
                    String md = moduleData.getValue().toString();
                    Module tempModule = new Module(md);
                    tempModule.setTime(md);

                    moduleArrayList.add(tempModule);

                    Log.d("TEST!!!!!", md);
                }
                for (Module modules : moduleArrayList) {
                    modules.setModuleInfo();
                    if (modules.getRebar().equals("합격")
                            && modules.getCovering().equals("합격")
                            && modules.getCrackDamage().equals("합격")
                            && modules.getMold().equals("합격")
                            && modules.getRebar().equals("합격")
                            && modules.getSize().equals("합격")
                            && modules.getStrength().equals("합격"))
                        adapter.add("부재번호 : "+modules.getModuleNo()+" ,  생산완료일시:"+modules.getFinishing());
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);
                count = adapter.getCount()+1;
                TextView textView = findViewById(R.id.tvCount4);
                textView.setText("확인된 부재 수: " + (count-1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
