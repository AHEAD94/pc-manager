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

public class RepairSizeList extends AppCompatActivity {

    // for listView
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;
    int count;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    public ArrayList<Module> repSizeArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_list);

        repSizeArray = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv_rep_size);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        // clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                //repSizeArray.get(position).setModuleInfo();

                Intent intent = new Intent(getApplicationContext(), RepairSizeClicked.class);
                intent.putExtra("moduleNo", repSizeArray.get(position).getModuleNo());
                intent.putExtra("producer", repSizeArray.get(position).getProducer());
                intent.putExtra("starting", repSizeArray.get(position).getStarting());
                intent.putExtra("finishing", repSizeArray.get(position).getFinishing());
                intent.putExtra("requested", repSizeArray.get(position).getTime());

                intent.putExtra("length", repSizeArray.get(position).getLength());
                intent.putExtra("height", repSizeArray.get(position).getHeight());
                intent.putExtra("width", repSizeArray.get(position).getWidth());
                intent.putExtra("depth", repSizeArray.get(position).getDepth());
                intent.putExtra("thickness", repSizeArray.get(position).getThickness());
                intent.putExtra("diagonal", repSizeArray.get(position).getDiagonal());

                Log.d("THE POSITION CLICKED", Integer.toString(position));

                startActivity(intent);
            }
        });

        // ValueEventLIstener
        mReference = mDatabase.getReference("repairing").child("size");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                repSizeArray.clear();

                for (DataSnapshot moduleData : dataSnapshot.getChildren()) {
                    String md = moduleData.getValue().toString();
                    Module tempModule = new Module(md);
                    tempModule.setTime(md);

                    repSizeArray.add(tempModule);
                    adapter.add("부재번호 : "+tempModule.getModuleNo()+" ,  요청일시 : "+tempModule.getTime());

                    Log.d("TEST!!!!!", md);
                }

                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);
                count = adapter.getCount()+1;
                TextView textView = findViewById(R.id.tvCount3);
                textView.setText("수리 요청된 부재 수: " + (count-1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}