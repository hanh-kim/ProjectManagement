package com.example.projectmanagement.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmanagement.R;
import com.example.projectmanagement.database.BillDAO;
import com.google.android.material.textfield.TextInputLayout;

public class BillActivity extends AppCompatActivity {
    TextView tvID, tvTotalPrice, tvCustomerName, tvBookName, tvAmountOfBook, tvPrice, tvDate;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView icEdit = findViewById(R.id.ic_edit);
        ImageView icDelete = findViewById(R.id.ic_delete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("data");


        tvID = findViewById(R.id.tv_info_billID);
        tvBookName = findViewById(R.id.tv_info_bill_bookName);
        tvAmountOfBook = findViewById(R.id.tv_info_bill_amount);
        tvPrice = findViewById(R.id.tv_info_bill_price);
        tvDate = findViewById(R.id.tv_info_bill_OrderDate);
        tvTotalPrice = findViewById(R.id.tv_info_bill_totalPrice);

        tvID.setText("Mã hóa đơn: " + bundle.getString("ID"));
        tvBookName.setText("Tên sách: " + bundle.getString("bookName"));
        tvAmountOfBook.setText("Số lượng: " + bundle.getString("numberBook"));
        tvPrice.setText("Giá bán: " + bundle.getString("price")+" vnđ");
        tvDate.setText("Ngày mua: " + bundle.getString("orderDate"));
        tvTotalPrice.setText("Tổng Cộng: " + bundle.getString("totalPrice") +" vnđ");
        icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this);
                builder.setMessage("Bạn muốn xóa sách này?");
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Xóa thành công!", Toast.LENGTH_LONG).show();
                        BillActivity.this.finish();
                    }
                });

                builder.show();
            }
        });

        icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, UpdateBillActivity.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });





    }
}