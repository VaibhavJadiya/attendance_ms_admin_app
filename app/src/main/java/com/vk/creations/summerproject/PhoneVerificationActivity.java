package com.vk.creations.summerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PhoneVerificationActivity extends AppCompatActivity {
    PinView pinView;
    String otpid;
    String Phonenumber;
    Button verify;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        pinView = findViewById(R.id.firstPinView);
        verify = findViewById(R.id.btn_verify);

        Intent intent = getIntent();
        Phonenumber = "+91" + intent.getStringExtra("number");

        mAuth=FirebaseAuth.getInstance();
        //Creating OTP
        initiateopt();
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pinView.getText().toString().isEmpty()){
                    Toast.makeText(PhoneVerificationActivity.this, "OTP is Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,pinView.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
            
            
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PhoneVerificationActivity.this, Phonenumber, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PhoneVerificationActivity.this,MainActivity.class));
                }
            });
    }

    private void initiateopt() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Phonenumber,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                        otpid=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(PhoneVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(PhoneVerificationActivity.this,MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(PhoneVerificationActivity.this, "Verification Filed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}