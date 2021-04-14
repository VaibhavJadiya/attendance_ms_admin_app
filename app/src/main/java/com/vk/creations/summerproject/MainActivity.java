package com.vk.creations.summerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.muddzdev.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {

    Button bt_adduser,bt_attendance,btn_assignment,bt_custome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_adduser=findViewById(R.id.btn_create_user);
        bt_attendance=findViewById(R.id.btn_attendance);
        btn_assignment=findViewById(R.id.bt_assignment);
        bt_custome=findViewById(R.id.btn_cutome);

        bt_custome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CustomeListView.class));
            }
        });

        btn_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AdminAssignmentActivity.class));
            }
        });
        bt_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,AddUserActivity.class));
            }
        });
        bt_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SubjectActivity.class));
            }
        });

    }
}