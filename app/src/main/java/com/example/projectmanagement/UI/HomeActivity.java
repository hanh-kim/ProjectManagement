package com.example.projectmanagement.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projectmanagement.R;
import com.example.projectmanagement.database.MyDatabase;
import com.example.projectmanagement.model.BookType;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    CardView card_bookType, card_book, card_bill, card_account, card_statistic;
    List<BookType> categoryList = new ArrayList<>();
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDatabase = new MyDatabase(HomeActivity.this);

        card_bookType = findViewById(R.id.card_bookType);
        card_book = findViewById(R.id.card_book);
        card_bill = findViewById(R.id.card_bill);
        card_account = findViewById(R.id.card_account);
        card_statistic = findViewById(R.id.card_statistic);

        card_bookType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
                startActivity(new Intent(HomeActivity.this, BookCategoryActivity.class));
            }
        });
        card_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
                startActivity(new Intent(HomeActivity.this, BookActivity.class));
            }
        });
        card_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
                startActivity(new Intent(HomeActivity.this, BillCategoryActivity.class));
            }
        });
        card_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
                startActivity(new Intent(HomeActivity.this, UserListActivity.class));
            }
        });
        card_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
                startActivity(new Intent(HomeActivity.this, StatisticActivity.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_QLS:

                startActivity(new Intent(HomeActivity.this, BookActivity.class));

                break;
            case R.id.item_QLTL:
                startActivity(new Intent(HomeActivity.this, BookCategoryActivity.class));
                break;
            case R.id.item_QLHD:
                startActivity(new Intent(HomeActivity.this, BillCategoryActivity.class));
                break;
            case R.id.item_TK:
                startActivity(new Intent(HomeActivity.this, StatisticActivity.class));
                break;
            case R.id.item_QLND:
                startActivity(new Intent(HomeActivity.this, UserListActivity.class));
                break;
            case R.id.item_MyAcc:
                startActivity(new Intent(HomeActivity.this, AccountActivity.class));
                break;
            case R.id.item_SignOut:
                this.finish();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                Toast.makeText(getApplicationContext(), "Sign out", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}