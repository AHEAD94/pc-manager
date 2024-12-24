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

public class Concrete extends AppCompatActivity {

    // for listView
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;
    int count;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    public ArrayList<Module> reqConcreteArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete);

        listView = (ListView) findViewById(R.id.lv_req_concrete);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        // clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                //reqMoldArray.get(position).setModuleInfo();

                Intent intent = new Intent(getApplicationContext(), ConcreteClicked.class);
                intent.putExtra("moduleNo", reqConcreteArray.get(position).getModuleNo());
                intent.putExtra("producer", reqConcreteArray.get(position).getProducer());
                intent.putExtra("starting", reqConcreteArray.get(position).getStarting());
                intent.putExtra("finishing", reqConcreteArray.get(position).getFinishing());
                intent.putExtra("requested", reqConcreteArray.get(position).getTime());

                Log.d("THE POSITION CLICKED", Integer.toString(position));

                startActivity(intent);
            }
        });

        // ValueEventLIstener
        mReference = mDatabase.getReference("requested").child("concrete");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                reqConcreteArray.clear();

                for (DataSnapshot moduleData : dataSnapshot.getChildren()) {
                    String md = moduleData.getValue().toString();
                    Module tempModule = new Module(md);
                    tempModule.setTime(md);

                    reqConcreteArray.add(tempModule);
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
