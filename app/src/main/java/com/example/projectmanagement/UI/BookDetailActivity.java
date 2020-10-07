package com.example.projectmanagement.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmanagement.R;

public class BookDetailActivity extends AppCompatActivity {
    TextView tvID, tvName, tvType, tvAuthor, tvAmount, tvImportPrice, tvprice, tvImportDate;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView icEdit = findViewById(R.id.ic_edit);
        ImageView icDelete = findViewById(R.id.ic_delete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailActivity.this);
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
                        BookDetailActivity.this.finish();
                    }
                });

                builder.show();
            }
        });

        icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this,UpdateBookActivity.class);

                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        tvID = findViewById(R.id.tv_detail_bookID);
        tvName = findViewById(R.id.tv_detail_bookName);
        tvType = findViewById(R.id.tv_detail_bookType);
        tvAuthor = findViewById(R.id.tv_detail_author);
        tvAmount = findViewById(R.id.tv_detail_amount);
        tvImportPrice = findViewById(R.id.tv_detail_importPrice);
        tvprice = findViewById(R.id.tv_detail_price);
        tvImportDate = findViewById(R.id.tv_detail_importDate);

        tvID.setText("ID sách: " + bundle.getString("id"));
        tvName.setText("Tên sách: " + bundle.getString("bookName"));
        tvType.setText("Thể loại: " + bundle.getString("kindOfBook"));
        tvAuthor.setText("Tác giả: " + bundle.getString("numberOfBook"));
        tvAmount.setText("Sô lượng: " + bundle.getString("author"));
        tvImportPrice.setText("Giá nhập: " + bundle.getString("importPrice"));
        tvprice.setText("Giá bán: " + bundle.getString("price"));
        tvImportDate.setText("Ngày nhập: " + bundle.getString("importDate"));


    }

}