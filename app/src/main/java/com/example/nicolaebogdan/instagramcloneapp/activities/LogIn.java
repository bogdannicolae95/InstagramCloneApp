package com.example.nicolaebogdan.instagramcloneapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.example.nicolaebogdan.instagramcloneapp.R;

public class LogIn extends AppCompatActivity {

    Button login;
    EditText EmailAdress;
    EditText password;
    SharedPreferences sharedPreferences;

    public static final String TAG = LogIn.class.getSimpleName().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        login = findViewById(R.id.btn_login);
        EmailAdress = findViewById((R.id.username));
        password = findViewById(R.id.password);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,Timeline.class);
                if (EmailAdress.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(LogIn.this,"You need to fill email and pass!",Toast.LENGTH_LONG).show();
                }else {
                    intent.putExtra(Constants.USERNAME,EmailAdress.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });



    }


}
