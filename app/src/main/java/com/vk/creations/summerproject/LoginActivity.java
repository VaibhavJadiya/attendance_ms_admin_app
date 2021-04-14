package com.vk.creations.summerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase rootnode;
    DatabaseReference reference;
    TextInputEditText l_username,l_password;
    TextInputLayout usernameLayout,passwordLayout;
    Button butnSignin,txt_btn_signup;
    String db_username,db_password;
    public String system_username,system_password;
    ProgressBar l_progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        butnSignin=findViewById(R.id.btn_signin);
        l_progressbar=findViewById(R.id.login_progressbar);
        l_username=findViewById(R.id.username);
        l_password=findViewById(R.id.password);
        txt_btn_signup=findViewById(R.id.btn_new_user);
        usernameLayout=findViewById(R.id.username_login_layout);
        passwordLayout=findViewById(R.id.password_login_Layout);

        butnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!valid_name()|| !valid_password()){
                    return;
                }
                l_progressbar.setVisibility(View.VISIBLE);
                system_username=l_username.getText().toString().trim();
                system_password=l_password.getText().toString().trim();
                Query checkUser=FirebaseDatabase.getInstance().getReference("Admin").orderByChild("username").equalTo(system_username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            db_username=snapshot.child(system_username).child("username").getValue(String.class);
                            db_password=snapshot.child(system_username).child("password").getValue(String.class);

                          //  Toast.makeText(LoginActivity.this, system_password, Toast.LENGTH_SHORT).show();
                            if (system_password.equals(db_password)){
                                l_progressbar.setVisibility(View.INVISIBLE);
                                StyleableToast.makeText(LoginActivity.this,"Succesfully Login",R.style.success).show();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        }
                        else {
                            StyleableToast.makeText(LoginActivity.this,"Invalid Admin username",R.style.error).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        txt_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }

    private Boolean valid_name(){
        String val=l_username.getText().toString().trim();
        if(val.isEmpty()){
            usernameLayout.setError("Name Can't be Empty");
            return false;
        }
        else{
            usernameLayout.setError(null);
            return true;
        }

    }
    private Boolean valid_password(){
        String val=l_password.getText().toString().trim();
        if(val.isEmpty()){
            passwordLayout.setError("Password Can't be Empty");
            return false;
        }
        else{
            passwordLayout.setError(null);
            return true;
        }

    }

}