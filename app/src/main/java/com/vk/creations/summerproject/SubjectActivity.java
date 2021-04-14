package com.vk.creations.summerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity {

    Button btn_iot,btn_java,btn_ict,btn_dbms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        btn_dbms=findViewById(R.id.btn_dbms);
        btn_ict=findViewById(R.id.btn_ict);
        btn_java=findViewById(R.id.btn_java);
        btn_iot=findViewById(R.id.btn_iot);

        btn_dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DateActivity.class);
                intent.putExtra("subject","DBMS");
                startActivity(intent);
            }
        });
        btn_iot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DateActivity.class);
                intent.putExtra("subject","IOT");
                startActivity(intent);
            }
        });
        btn_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DateActivity.class);
                intent.putExtra("subject","JAVA");
                startActivity(intent);
            }
        });
        btn_ict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DateActivity.class);
                intent.putExtra("subject","ICT");
                startActivity(intent);
            }
        });
    }
}