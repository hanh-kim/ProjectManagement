package com.example.projectmanagement.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.adapter.BookAdapter;
import com.example.projectmanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    ListView lvBook;
    List<Book> bookList = new ArrayList<>();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        lvBook = findViewById(R.id.lvListBook);
        // toolbar.............
        toolbar = (Toolbar) findViewById(R.id.toolbar_book);
        setSupportActionBar(toolbar);
        ImageView icAddBook = findViewById(R.id.ic_addBook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        icAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookActivity.this, AddBookActivity.class));
            }
        });
//        Intent intent=getIntent();
//        String title = intent.getStringExtra("data").toString().trim();
//        getSupportActionBar().setTitle(title);

        for (int i = 0; i < 20; i++) {
            Book book = new Book();
            book.bookID =""+ (i + 1);
            book.bookName = "Lập trình Android";
            book.typeID="CNTT";
            book.author="FPT Education";
            book.amount = 100;
            book.importPrice=100000;
            book.price = 150000;
            book.importDate="01/01/2020";
            bookList.add(book);
        }

        final BookAdapter adapter = new BookAdapter(this, bookList);
        lvBook.setAdapter(adapter);

//.LongClick.....Update and Delete.....................................
        lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                builder.setMessage("Bạn muốn Xóa hay Sửa thông tin sách này?");
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
        // click to book detail............................
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookActivity.this, BookDetailActivity.class);
                Book book = bookList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", "" + book.bookID);
                bundle.putString("bookName", "" + book.bookName);
                bundle.putString("kindOfBook", "" + book.typeID);
                bundle.putString("numberOfBook", "" + book.amount);
                bundle.putString("author", "" + book.author);
                bundle.putString("importPrice", "" + book.importPrice);
                bundle.putString("price", "" + book.price);
                bundle.putString("importDate", "" + book.importDate);
                intent.putExtra("data", bundle);
                startActivity(intent);

            }
        });


    }
}