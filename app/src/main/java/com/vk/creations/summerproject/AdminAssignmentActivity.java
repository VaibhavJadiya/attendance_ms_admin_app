package com.vk.creations.summerproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

public class AdminAssignmentActivity extends AppCompatActivity {

    FirebaseDatabase rootnode;
    DatabaseReference reference;
    TextInputEditText input_assignment_name,input_assignment_link,input_subject_name;
    Button bt_ok;
    String name,link,subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assignment);
    }

    public void ShowDailog(View view) {
        final AlertDialog.Builder alert=new AlertDialog.Builder(AdminAssignmentActivity.this);
        View mview=getLayoutInflater().inflate(R.layout.create_assignment_dailogbox,null);


        input_assignment_name=mview.findViewById(R.id.txt_dailog_assignment_name);
        input_assignment_link=mview.findViewById(R.id.txt_dailog_assignment_link);
        input_subject_name=mview.findViewById(R.id.txt_dailog_subject_name);

        bt_ok=mview.findViewById(R.id.btn_ok);

        //Creating Alert
        alert.setView(mview);
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        //OnCreate on Buttons
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Assignments");
                name=input_assignment_name.getText().toString().trim();
                link=input_assignment_link.getText().toString().trim();
                subject=input_subject_name.getText().toString().trim();



                AssignmentHelperClass helperClass=new AssignmentHelperClass(name,link,subject);
                reference.child(name).setValue(helperClass);
               startActivity(new Intent(AdminAssignmentActivity.this,AdminAssignmentActivity.class));
                StyleableToast.makeText(AdminAssignmentActivity.this,"Succefuly Assignment Created",R.style.success).show();
            }
        });
            alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAssignmentActivity.this,MainActivity.class));
    }
}