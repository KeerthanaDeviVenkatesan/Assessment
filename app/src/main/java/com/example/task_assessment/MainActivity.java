package com.example.task_assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText mobile;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        name=findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        login=findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAddress(name,email,mobile);
                Intent intent = new Intent(MainActivity.this,AnotherMainActivity.class);
                intent.putExtra("name",name.getText().toString().trim());
                intent.putExtra("email",email.getText().toString().trim());
                intent.putExtra("mobile",mobile.getText().toString().trim());
                startActivity(intent);


            }
        });
    }


    public boolean validateAddress(EditText name,EditText email,EditText mobile){
        String nameInput=name.getText().toString();
        String emailInput=email.getText().toString();
        String mobileInput=mobile.getText().toString();
        if(!nameInput.isEmpty()&&!emailInput.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()&&!mobileInput.isEmpty()&&Patterns.PHONE.matcher(mobileInput).matches()&&mobileInput.length()==10) {
            Toast.makeText(this, "Correct email id", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Correct phone number", Toast.LENGTH_SHORT).show();


        }
        else
            Toast.makeText(this,"Give Correct Value",Toast.LENGTH_SHORT).show();
        return false;
    }
}