package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.projectmanagement.R;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateBookCategoryActivity extends AppCompatActivity {
   TextInputLayout txtName;
   Button btnSave, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book_category);
        txtName=findViewById(R.id.edtL_bookTypeName);
        btnCancel=findViewById(R.id.btnCancel);
        btnSave=findViewById(R.id.btnSave);
        Intent intent= getIntent();
        txtName.getEditText().setText(intent.getStringExtra("data"));


    }
}