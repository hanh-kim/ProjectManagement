package com.example.projectmanagement.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class AddUserActivity extends AppCompatActivity {
    private MyDatabase myDatabase;
    TextInputLayout edtUsername, edtFullname, edtPhone, edtEmail, edtPassword, edtConfirmPassword;
    private UserDAO userDAO;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        myDatabase = new MyDatabase(AddUserActivity.this);
        userDAO = new UserDAO(myDatabase);

        edtUsername = findViewById(R.id.edtL_add_username);
        edtFullname = findViewById(R.id.edtL_add_fullname);
        edtPhone = findViewById(R.id.edtL_add_phone);
        edtEmail = findViewById(R.id.edtL_add_email);
        edtPassword = findViewById(R.id.edtL_add_password);
        edtConfirmPassword = findViewById(R.id.edtL_add_confirmPassword);
        btnCancel = findViewById(R.id.btn_addUser_cancel);
        btnSave = findViewById(R.id.btn_addUser_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddUserActivity.this, UserListActivity.class));
                AddUserActivity.this.finish();
            }
        });

    }

    public void addUser() {
        final String uUsername = edtUsername.getEditText().getText().toString().trim();
        final String uFullname = edtFullname.getEditText().getText().toString().trim();
        final String uPhone = edtPhone.getEditText().getText().toString().trim();
        final String uEmail = edtEmail.getEditText().getText().toString().trim();
        final String uPassword = edtPassword.getEditText().getText().toString().trim();
        final String uConfirmPassword = edtConfirmPassword.getEditText().getText().toString().trim();
        if (uUsername.isEmpty() || uFullname.isEmpty() || uPhone.isEmpty() || uEmail.isEmpty() || uPassword.isEmpty() || uConfirmPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Mời nhập đủ thông tin Người Dùng!", Toast.LENGTH_LONG).show();
            return;
        } else if (!uConfirmPassword.equals(uPassword)) {
            edtConfirmPassword.setError("Mật khẩu nhập lại không khớp!");
            Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp!", Toast.LENGTH_LONG).show();
            return;
        } else {
            edtConfirmPassword.setErrorEnabled(false);
            InfoUser user = new InfoUser();
            user.username = uUsername;
            user.fullName = uFullname.toUpperCase();
            user.phone = uPhone;
            user.email = uEmail;
            user.password = uPassword;

            if (userDAO.checkExist(user)) {
                edtUsername.setError("Username đã tồn tại!");
                Toast.makeText(getApplicationContext(), "Username đã tồn tại!", Toast.LENGTH_LONG).show();
            } else {
                edtUsername.setErrorEnabled(false);
                userDAO.addData(user);
                Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddUserActivity.this, UserListActivity.class));
                AddUserActivity.this.finish();

            }
        }
    }
}