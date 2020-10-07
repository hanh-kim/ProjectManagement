package com.example.projectmanagement.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectmanagement.R;
import com.example.projectmanagement.adapter.UserAdapter;
import com.example.projectmanagement.database.MyDatabase;
import com.example.projectmanagement.database.UserDAO;
import com.example.projectmanagement.model.InfoUser;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ListView lvUser;
    List<InfoUser> userList = new ArrayList<>();
    Toolbar toolbar;
    private UserDAO userDAO;
    private MyDatabase myDatabase;
    TextInputLayout edtUsername, edtFullname, edtPhone, edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new MyDatabase(UserListActivity.this);
        userDAO = new UserDAO(myDatabase);
        userList = userDAO.getAllData();
        checkListUser(userList);
    }

//    public void addUser() {
////        AlertDialog.Builder builder = new AlertDialog.Builder(UserListActivity.this);
////        View view = getLayoutInflater().inflate(R.layout.add_user, null);
////
////        edtUsername = view.findViewById(R.id.edtL_add_username);
////        edtFullname = view.findViewById(R.id.edtL_add_fullname);
////        edtPhone = view.findViewById(R.id.edtL_add_phone);
////        edtEmail = view.findViewById(R.id.edtL_add_email);
////        edtPassword = view.findViewById(R.id.edtL_add_password);
////
////        builder.setView(view);
////        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////
////                final String uUsername = edtUsername.getEditText().getText().toString().trim();
////                final String uFullname = edtFullname.getEditText().getText().toString().trim();
////                final String uPhone = edtPhone.getEditText().getText().toString().trim();
////                final String uEmail = edtEmail.getEditText().getText().toString().trim();
////                final String uPassword = edtPassword.getEditText().getText().toString().trim();
////                Toast.makeText(getApplicationContext(), uUsername + "/" + uFullname + "/" + uPhone + "/" + uEmail + "/" + uPassword, Toast.LENGTH_LONG).show();
////            }
////        });
////        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////
////                final String uUsername = edtUsername.getEditText().getText().toString().trim();
////                final String uFullname = edtFullname.getEditText().getText().toString().trim();
////                final String uPhone = edtPhone.getEditText().getText().toString().trim();
////                final String uEmail = edtEmail.getEditText().getText().toString().trim();
////                final String uPassword = edtPassword.getEditText().getText().toString().trim();
////                if (uUsername.isEmpty() || uFullname.isEmpty() || uPhone.isEmpty() || uEmail.isEmpty() || uPassword.isEmpty()) {
////                    Toast.makeText(getApplicationContext(), "Mời nhập đủ thông tin Người Dùng!", Toast.LENGTH_LONG).show();
////                } else {
////                    InfoUser user = new InfoUser();
////                    user.username = uUsername;
////                    user.fullName = uFullname.toUpperCase();
////                    user.phone = uPhone;
////                    user.email = uEmail;
////                    user.password = uPassword;
////
////                    if (userDAO.checkExist(user)) {
////                        Toast.makeText(getApplicationContext(), "Username đã tồn tại!", Toast.LENGTH_LONG).show();
////                    } else {
////                        userDAO.addData(user);
////                        Toast.makeText(getApplicationContext(), "Username đã tồn tại!", Toast.LENGTH_LONG).show();
////                        fillToList();
////                    }
////                }
////            }
////        });
////
////        builder.show();
////
////    }

    public void fillToList() {
        // adapter.....................
        userList = userDAO.getAllData();
        UserAdapter userAdapter = new UserAdapter(this, userList);
        lvUser.setAdapter(userAdapter);
    }

    public void checkListUser(List<InfoUser> list) {
        list = userDAO.getAllData();
        if (list.size() == 0) {
            setContentView(R.layout.layout_user_empty);
            toolbar = (Toolbar) findViewById(R.id.toolbar_userlist);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            ImageView icBigAdd = findViewById(R.id.ic_big_addUser);
            icBigAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UserListActivity.this, AddUserActivity.class));
                }
            });

        } else {
            setContentView(R.layout.activity_user_list);
            lvUser = findViewById(R.id.lvListUser);
            toolbar = (Toolbar) findViewById(R.id.toolbar_userlist);
            setSupportActionBar(toolbar);
            ImageView icAddUser = findViewById(R.id.ic_addUser);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            // getData. and fill to list..............
            fillToList();
            final List<InfoUser> finalList = list;

            lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final InfoUser user = finalList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserListActivity.this);
                    builder.setMessage("Bạn muốn xóa người dùng này?");
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDAO.deleteData(user);
                            checkListUser(userList);
                            Toast.makeText(getApplicationContext(), "Xóa thành công!", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog alertDialog = builder.show();

                    return false;
                }
            });

            icAddUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UserListActivity.this, AddUserActivity.class));
                }
            });
        }
    }

}
