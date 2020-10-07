package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.adapter.BookCategoryAdapter;
import com.example.projectmanagement.model.BookType;

import java.util.ArrayList;
import java.util.List;

public class BookCategoryActivity extends AppCompatActivity {

    ListView lvBookCategory;
    List<BookType> categoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category);
        // toolbar.............
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView  icAddBook = findViewById(R.id.ic_addBook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        icAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(BookCategoryActivity.this, AddBookCategoryActivity.class));
            }
        });

    // list...........
        BookType bookType1 = new BookType();
        bookType1.ID="KT";
        bookType1.name="Kinh Tế";

        categoryList.add(bookType1);

        BookType bookType2 = new BookType();
        bookType2.ID="IT";
        bookType2.name="CNTT";

        categoryList.add(bookType2);

        BookType bookType3 = new BookType();
        bookType3.ID="NN";
        bookType3.name="Ngoại Ngữ";

        categoryList.add(bookType3);

        // listView adapter
        lvBookCategory=findViewById(R.id.lvBookCategory);
        lvBookCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookCategoryActivity.this,BookCategoryDetailActivity.class);
                BookType category = categoryList.get(position);
                intent.putExtra("id",""+category.ID);
                intent.putExtra("name",category.name);

                startActivity(intent);

            }
        });
        BookCategoryAdapter adapter = new BookCategoryAdapter(this,categoryList);
        lvBookCategory.setAdapter(adapter);
        //..................



    }
}