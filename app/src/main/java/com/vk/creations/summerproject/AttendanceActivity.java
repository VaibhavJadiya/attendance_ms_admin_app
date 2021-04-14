package com.vk.creations.summerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.PriorityGoalRow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ListView studentlist;
    FirebaseListAdapter adapter;
    ArrayList<String> NameArray=new ArrayList<>();
    ArrayList<String> UserNameArray=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        progressBar=findViewById(R.id.attendance_progreebar);
        progressBar.setVisibility(View.VISIBLE);
        Intent intent=getIntent();
        final String dis_subject=intent.getStringExtra("subject");
        final String dis_date=intent.getStringExtra("date");
        studentlist=findViewById(R.id.name_list);
        Query query= FirebaseDatabase.getInstance().getReference().child("Attendance").child(dis_date).child(dis_subject);
        FirebaseListOptions<AttendanceHelperClass> options=new FirebaseListOptions.Builder<AttendanceHelperClass>()
                .setLayout(R.layout.user_design)
                .setQuery(query,AttendanceHelperClass.class)
                .build();
        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name_txt=v.findViewById(R.id.lst_name);
                TextView username_txt=v.findViewById(R.id.lst_username);
                AttendanceHelperClass DateObj=(AttendanceHelperClass) model;
                NameArray.add(String.valueOf(DateObj.getName()));
                name_txt.setText(DateObj.getName().toString());
                username_txt.setText(DateObj.getUsername().toString());
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
        studentlist.setAdapter(adapter);
        studentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AttendanceActivity.this, NameArray.get(i), Toast.LENGTH_SHORT).show();


            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
