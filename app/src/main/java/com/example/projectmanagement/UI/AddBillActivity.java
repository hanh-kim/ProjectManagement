package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectmanagement.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddBillActivity extends AppCompatActivity {
    TextView tvTotalPrice;
    TextInputLayout txtCustomerName, txtBookName, txtAmountOfBook, txtPrice, txtDate;
    Button btnSave, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        getSupportActionBar().setTitle("Thêm hóa đơn");
        tvTotalPrice = findViewById(R.id.tv_bill_total);
        txtCustomerName= findViewById(R.id.edtL_bill_customerName);
        txtBookName= findViewById(R.id.edtL_bill_bookName);
        txtAmountOfBook= findViewById(R.id.edtL_bill_amount);
        txtPrice= findViewById(R.id.edtL_bill_price);
        txtDate= findViewById(R.id.edtL_bill_date);
        btnCancel=findViewById(R.id.btn_bill_cancel);
        btnSave = findViewById(R.id.btn_bill_save);
    }
}