package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectmanagement.R;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateBillActivity extends AppCompatActivity {
    TextView tvTotalPrice;
    TextInputLayout txtCustomerName, txtBookName, txtAmountOfBook, txtPrice, txtDate;
    Button btnSave, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bill);
        tvTotalPrice = findViewById(R.id.tv_bill_total);
        txtCustomerName= findViewById(R.id.edtL_bill_customerName);
        txtBookName= findViewById(R.id.edtL_bill_bookName);
        txtAmountOfBook= findViewById(R.id.edtL_bill_amount);
        txtPrice= findViewById(R.id.edtL_bill_price);
        txtDate= findViewById(R.id.edtL_bill_date);
        btnCancel=findViewById(R.id.btn_bill_cancel);
        btnSave = findViewById(R.id.btn_bill_save);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        txtCustomerName.getEditText().setText( bundle.getString("customerName"));
        txtBookName.getEditText().setText( bundle.getString("bookName"));
        txtAmountOfBook.getEditText().setText(bundle.getString("numberBook"));
        txtPrice.getEditText().setText(  bundle.getString("price"));
        txtDate.getEditText().setText( bundle.getString("orderDate"));
        String am=txtAmountOfBook.getEditText().getText().toString().trim();
        String pr=txtPrice.getEditText().getText().toString().trim();

        double total= Double.parseDouble(am)*Double.parseDouble(pr);
        tvTotalPrice.setText("Tổng Cộng: "+total +" vnđ");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateBillActivity.this,BillActivity.class));
                UpdateBillActivity.this.finish();

            }
        });
    }
}