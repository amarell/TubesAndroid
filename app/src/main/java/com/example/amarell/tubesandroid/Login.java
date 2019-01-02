package com.example.amarell.tubesandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth mAuth;
    EditText EtEmail, EtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();
        EtEmail =(EditText) findViewById(R.id.username);
        EtPassword=(EditText) findViewById(R.id.password);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.TvSignUp).setOnClickListener(this);
}
    private void userlogin() {
        String email = EtEmail.getText().toString().trim();
        String password= EtPassword.getText().toString().trim();
        if (email.isEmpty()){
            EtEmail.setError("Email harus Diisi");
            EtEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EtEmail.setError("Masukan Email Yang Valid");
            EtEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            EtPassword.setError("Password harus diisi");
            EtPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            EtPassword.setError("Password minimal 6 karakter");
            EtPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.TvSignUp:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.login:
                userlogin();
                break;
        }
    }
}
