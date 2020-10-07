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

public class BookCategoryDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvID, tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView icEdit = findViewById(R.id.ic_edit);
        ImageView icDelete = findViewById(R.id.ic_delete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Intent intent= getIntent();
        tvID = findViewById(R.id.tv_detail_typeID);
        tvName = findViewById(R.id.tv_detail_typeName);

        tvID.setText("ID thể loại: " + intent.getStringExtra("id"));
        tvName.setText("Tên thể loại: " + intent.getStringExtra("name"));


        icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookCategoryDetailActivity.this);
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
                        BookCategoryDetailActivity.this.finish();
                    }
                });

                builder.show();
            }
        });

        icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BookCategoryDetailActivity.this,UpdateBookCategoryActivity.class);
                intent1.putExtra("data",intent.getStringExtra("name"));
                startActivity(intent1);
            }
        });

    }
}