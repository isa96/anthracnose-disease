package com.example.xl_imdp_patech.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xl_imdp_patech.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText email, name, pass, passrepeat;
    Button buttonRegister;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_edittext);
        name = findViewById(R.id.username_edittext);
        pass = findViewById(R.id.password_edittext);
        passrepeat = findViewById(R.id.confirm_pass_edittext);
        buttonRegister = findViewById(R.id.register_button);
        backbutton = findViewById(R.id.backbut);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = checkRegisterForm();
                String emailUsr = email.getText().toString().trim();
                String passUsr = pass.getText().toString().trim();
                createUser(emailUsr, passUsr, valid);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }

    private boolean checkRegisterForm(){
        if(email.getText().toString().trim().isEmpty()){
            email.setError("Email tidak boleh kosong!");
        }
        else if(name.getText().toString().trim().isEmpty()){
            name.setError("Nama tidak boleh kosong!");
        }
        else if(pass.getText().toString().trim().isEmpty()){
            pass.setError("Password tidak boleh kosong!");
        }
        else if(passrepeat.getText().toString().trim().isEmpty()){
            passrepeat.setError("Password tidak boleh kosong!");
        }
        else if(!(passrepeat.getText().toString().trim().equals(pass.getText().toString().trim()))){
            passrepeat.setError("Password tidak sama!");
        }
        else{
            return true;
        }
        return false;
    }

    private void createUser(String email, String password, boolean valid){
        if (!valid) {
            Toast.makeText(getApplicationContext(), "Ulangi Registrasi Form!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            goToLogin();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registrasi Gagal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToLogin(){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}