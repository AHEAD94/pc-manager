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

public class RepairRebarList extends AppCompatActivity {

    // for listView
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;
    int count;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    public ArrayList<Module> repRebarArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_rebar_list);

        listView = (ListView) findViewById(R.id.lv_rep_rebar);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        // clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                //reqMoldArray.get(position).setModuleInfo();

                Intent intent = new Intent(getApplicationContext(), RepairRebarClicked.class);
                intent.putExtra("moduleNo", repRebarArray.get(position).getModuleNo());
                intent.putExtra("producer", repRebarArray.get(position).getProducer());
                intent.putExtra("starting", repRebarArray.get(position).getStarting());
                intent.putExtra("finishing", repRebarArray.get(position).getFinishing());
                intent.putExtra("requested", repRebarArray.get(position).getTime());

                Log.d("THE POSITION CLICKED", Integer.toString(position));

                startActivity(intent);
            }
        });

        // ValueEventLIstener
        mReference = mDatabase.getReference("repairing").child("rebar");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                repRebarArray.clear();

                for (DataSnapshot moduleData : dataSnapshot.getChildren()) {
                    String md = moduleData.getValue().toString();
                    Module tempModule = new Module(md);
                    tempModule.setTime(md);

                    repRebarArray.add(tempModule);
                    adapter.add("부재번호 : "+tempModule.getModuleNo()+" ,  요청일시 : "+tempModule.getTime());

                    Log.d("TEST!!!!!", md);
                }

                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);
                count = adapter.getCount()+1;
                TextView textView = findViewById(R.id.tvCount4);
                textView.setText("등록된 부재 수: " + (count-1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
