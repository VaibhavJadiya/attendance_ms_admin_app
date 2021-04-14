package com.vk.creations.summerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

public class AddUserActivity extends AppCompatActivity {

    TextInputEditText u_name,u_email,u_username,u_number,u_password,u_confrimpassword;
    TextInputLayout nameLayout,emaillayout,passwordLayout,usernameLayout,numberLayout,confirmLayout;
    Button signup,existing_user;
    String s_name,s_email,s_username,s_number,s_password,s_confirmpassword,val;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        u_name=findViewById(R.id.input_name);
        u_email=findViewById(R.id.input_email);
        u_username=findViewById(R.id.input_username);
        u_number=findViewById(R.id.input_number);
        u_password=findViewById(R.id.input_password);
        signup=findViewById(R.id.btn_signup);

        nameLayout=findViewById(R.id.name_user_layout);
        usernameLayout=findViewById(R.id.username_user_layout);
        emaillayout=findViewById(R.id.email_user_layout);
        numberLayout=findViewById(R.id.number_user_layout);
        passwordLayout=findViewById(R.id.password_user_layout);
        confirmLayout=findViewById(R.id.confirmpassword_user_layout);

        bar=findViewById(R.id.progreesbar);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!valid_name()||!valid_email()||!valid_username()||!valid_number()||!valid_password()){
                    return;
                }

                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Users");

                s_name=u_name.getText().toString().trim();


                s_email=u_email.getText().toString().trim();
                s_username=u_username.getText().toString().trim();
                s_number=u_number.getText().toString().trim();
                s_password=u_password.getText().toString().trim();

                //Checking is Username alrady exists



                //Validating Form

                //Direct Signup ho jaega
                bar.setVisibility(View.VISIBLE);
                UserHelperClass helperClass=new UserHelperClass(s_name,s_email,s_username,s_number,s_password);
               // Toast.makeText(AddUserActivity.this, "user load", Toast.LENGTH_SHORT).show();
                reference.child(s_username).setValue(helperClass);
                bar.setVisibility(View.INVISIBLE);
                // Toast.makeText(SignupActivity.this, "Registered Sucessful!", Toast.LENGTH_SHORT).show();
                StyleableToast.makeText(AddUserActivity.this,"Registration Succesfully",R.style.success).show();
                startActivity(new Intent(AddUserActivity.this,MainActivity.class));



            }
        });
    }

    private Boolean valid_name(){
        String val=u_name.getText().toString().trim();
        if(val.isEmpty()){
            nameLayout.setError("Name Can't be Empty");
            return false;
        }
        else{
            nameLayout.setError(null);
            return true;
        }

    }
    private Boolean valid_number(){
        String val=u_number.getText().toString().trim();
        if(val.isEmpty()){
            numberLayout.setError("Number Can't be Empty");
            return false;
        }
        else{
            numberLayout.setError(null);
            return true;
        }

    }
    private Boolean valid_username(){
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        String val=u_username.getText().toString().trim();
        if(val.isEmpty()){
            usernameLayout.setError("Username Can't be Empty");
            return false;
        }
        else if (!val.matches(noWhiteSpace)) {
            usernameLayout.setError("White Spaces are not allowed");
            return false;
        }
        else{
            usernameLayout.setError(null);
            return true;
        }

    }
    private Boolean valid_email(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String val=u_email.getText().toString().trim();
        if(val.isEmpty()){
            emaillayout.setError("Email Can't be Empty");
            return false;
        }
        else if (!val.matches(emailPattern)) {
            emaillayout.setError("Invalid email address");
            return false;
        }
        else{
            emaillayout.setError(null);
            return true;
        }

    }
    private Boolean valid_password(){
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                //  "(?=.*[a-zA-Z])" +      //any letter
                // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                // "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$";

        String val=u_password.getText().toString().trim();
        if(val.isEmpty()){
            passwordLayout.setError("Email Can't be Empty");
            return false;
        }
        else if (!val.matches(passwordVal)) {
            passwordLayout.setError("At Least 8 Digit password");
            return false;
        }
        else{
            passwordLayout.setError(null);
            return true;
        }

    }

}