package com.vk.creations.summerproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class CustomeListView extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    FirebaseListAdapter adapter;
    ArrayList<String> NameArray=new ArrayList<>();
    ArrayList<String> UsernameArray=new ArrayList<>();
    // now paste some images in drawable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_list_view);

        listView=findViewById(R.id.custome_listview);
        Query query=FirebaseDatabase.getInstance().getReference().child("Users");
        FirebaseListOptions<CustomeListHelperClass> options=new FirebaseListOptions.Builder<CustomeListHelperClass>()
                .setLayout(R.layout.user_design)
                .setQuery(query,CustomeListHelperClass.class)
                .build();

        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {

                TextView txt_name=v.findViewById(R.id.lst_name);
                TextView txt_username=v.findViewById(R.id.lst_username);
                CustomeListHelperClass lst_obj=(CustomeListHelperClass) model;
                NameArray.add(String.valueOf(lst_obj.getName()));
                //Toast.makeText(CustomeListView.this, NameArray.get(position), Toast.LENGTH_SHORT).show();
                txt_name.setText(lst_obj.getName().toString());
                txt_username.setText(lst_obj.getUsername().toString());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CustomeListView.this, NameArray.get(i),Toast.LENGTH_SHORT).show();
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