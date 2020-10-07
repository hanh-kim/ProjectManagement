package com.example.projectmanagement.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.adapter.BillAdapter;
import com.example.projectmanagement.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillCategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView icAddBill;
    ListView lvBillCategory;
    List<Bill> billList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_category);
        // toolbar.............
        lvBillCategory = findViewById(R.id.lvBillCategory);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        icAddBill = findViewById(R.id.ic_addBill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        icAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillCategoryActivity.this, AddBillActivity.class));
            }
        });
        for (int i = 0; i < 20; i++) {
            Bill bill = new Bill();
            bill.billID ="HD"+(i + 1);
            bill.bookName="Android nâng cao";
            bill.numberBook=3;
            bill.bookPrice=150000;
            bill.date = "30/09/2020";
            billList.add(bill);
        }

        BillAdapter adapter = new BillAdapter(this, billList);
        lvBillCategory.setAdapter(adapter);

        lvBillCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill bill = billList.get(position);
                Intent intent = new Intent(BillCategoryActivity.this,BillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID",""+bill.billID);
                bundle.putString("bookName",""+bill.bookName);
                bundle.putString("numberBook",""+bill.numberBook);
                bundle.putString("price",""+bill.bookPrice);
                bundle.putString("orderDate",""+bill.date);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });

       lvBillCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               AlertDialog.Builder builder = new AlertDialog.Builder(BillCategoryActivity.this);
               builder.setMessage("Bạn muốn Xóa hay Sửa thông tin hóa đơn này?");
               builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });

               builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });

               builder.show();

               return false;
           }
       });

    }
}