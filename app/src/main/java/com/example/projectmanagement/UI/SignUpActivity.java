package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectmanagement.R;
import com.example.projectmanagement.database.MyDatabase;
import com.example.projectmanagement.database.UserDAO;
import com.example.projectmanagement.model.InfoUser;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout txtUsername, txtFullname, txtPhone, txtEmail, txtPassword, txtConfirmPassword;
    Button btnSignup, btnCancel;
    private UserDAO userDAO;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDatabase = new MyDatabase(SignUpActivity.this);
        userDAO = new UserDAO(myDatabase);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btnCancel = findViewById(R.id.btn_cancel_signUp);
        btnSignup = findViewById(R.id.btn_ok_signUp);
        txtUsername = findViewById(R.id.edtL_su_username);
        txtFullname = findViewById(R.id.edtL_su_fullname);
        txtPhone = findViewById(R.id.edtL_su_phoneNumber);
        txtEmail = findViewById(R.id.edtL_su_email);
        txtPassword = findViewById(R.id.edtL_su_password);
        txtConfirmPassword = findViewById(R.id.edtL_su_confirmPassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    public void addUser() {
        final String uUsername = txtUsername.getEditText().getText().toString().trim();
        final String uFullname = txtFullname.getEditText().getText().toString().trim();
        final String uPhone = txtPhone.getEditText().getText().toString().trim();
        final String uEmail = txtEmail.getEditText().getText().toString().trim();
        final String uPassword = txtPassword.getEditText().getText().toString().trim();
        final String uConfirmPassword = txtConfirmPassword.getEditText().getText().toString().trim();
        if (uUsername.isEmpty() || uFullname.isEmpty() || uPhone.isEmpty() || uEmail.isEmpty() || uPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Mời nhập đủ thông tin Người Dùng!", Toast.LENGTH_LONG).show();
            return;
        } else if (!uConfirmPassword.equals(uPassword)) {
            txtConfirmPassword.setError("Mật khẩu nhập lại không khớp!");
            Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp!", Toast.LENGTH_LONG).show();
            return;
        } else {
            txtConfirmPassword.setErrorEnabled(false);
            InfoUser user = new InfoUser();
            user.username = uUsername;
            user.fullName = uFullname.toUpperCase();
            user.phone = uPhone;
            user.email = uEmail;
            user.password = uPassword;

            if (userDAO.checkExist(user)) {
                txtUsername.setError("Username đã tồn tại!");
                Toast.makeText(getApplicationContext(), "Username đã tồn tại!", Toast.LENGTH_LONG).show();
            } else {
                txtUsername.setErrorEnabled(false);
                userDAO.addData(user);
                Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        }
    }
}