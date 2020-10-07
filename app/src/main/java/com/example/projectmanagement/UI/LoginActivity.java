package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.projectmanagement.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnSignup;
    TextInputLayout textInputLayoutUser, textInputLayoutPassword;
    CheckBox checkRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        textInputLayoutUser = findViewById(R.id.edtL_username);
        textInputLayoutPassword = findViewById(R.id.edtL_password);
        checkRemember = findViewById(R.id.cboRememberAcc);
        restoreInfoAccountRemembered();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputLayoutUser.getEditText().getText().toString().trim().isEmpty()
                        || textInputLayoutPassword.getEditText().getText().toString().trim().isEmpty()) {
                    textInputLayoutUser.setErrorEnabled(true);
                    textInputLayoutUser.setError("Enter your Username !");
                    textInputLayoutPassword.setErrorEnabled(true);
                    textInputLayoutPassword.setError("Enter your Password !");
                } else {
                    textInputLayoutUser.setErrorEnabled(false);
                    textInputLayoutPassword.setErrorEnabled(false);
                    saveInfoAccountRemembered();
                    finish();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                }


            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


    }

    public void saveInfoAccountRemembered() {
        boolean chkRemember = checkRemember.isChecked();
        SharedPreferences sharedPreferences = getSharedPreferences("my_account", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username = textInputLayoutUser.getEditText().getText().toString().trim();
        String password = textInputLayoutPassword.getEditText().getText().toString().trim();
        if (!chkRemember) {
            editor.clear();
        } else {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("checkRemember", chkRemember);
        }
        editor.commit();

    }

    ////--------------------
    public void restoreInfoAccountRemembered() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_account", MODE_PRIVATE);
        boolean chkRemember = sharedPreferences.getBoolean("checkRemember", false);

        if (chkRemember) {
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            textInputLayoutUser.getEditText().setText(username);
            textInputLayoutPassword.getEditText().setText(password);

        }
        checkRemember.setChecked(chkRemember);
    }
}