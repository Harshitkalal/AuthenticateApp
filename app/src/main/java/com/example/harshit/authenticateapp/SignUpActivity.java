package com.example.harshit.authenticateapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail,editTextPassword;
    String Email,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }
    public void createuser(){

        Email =editTextEmail.getText().toString().trim();
        password=editTextPassword.getText().toString().trim();
        if(!validation()){
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplication(),"user Registered",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplication(),"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validation(){
        boolean valid =true;

        if(Email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            valid =false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please Enter valid Email address");
            editTextEmail.requestFocus();
            valid =false;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Please Enter the password");
            editTextPassword.requestFocus();
            valid=false;
        }
        if(password.length()<6){
            editTextPassword.setError("Minimum password length is 6");
            editTextPassword.requestFocus();
            valid=false;
        }

        return valid; }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignUp:
                createuser();
                break;
            case R.id.textViewLogin:
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;
                default:
                    break;
        }
    }
}
