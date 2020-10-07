package com.example.projectmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.adapter.StatisticalBookAdapter;
import com.example.projectmanagement.model.Book;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {
    ListView lvStatistic;
    List<Book> bookList = new ArrayList<>();
    TextInputLayout txtStartDate, txtEndDate;
    ImageView icStartDate, icEndDate;
    Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        lvStatistic = findViewById(R.id.lvStatistic);
        txtStartDate = findViewById(R.id.txtStartDate);
        txtEndDate = findViewById(R.id.txtEndDate);
        icEndDate = findViewById(R.id.ic_calendar_end);
        icStartDate = findViewById(R.id.ic_calendar_start);
        btnCheck = findViewById(R.id.btnCheckStatistic);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        icStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(txtStartDate);
            }
        });

        icEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(txtEndDate);
            }
        });

        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(txtStartDate);
            }
        });

        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(txtEndDate);
            }
        });

        for (int i = 0; i < 20; i++) {
            Book book = new Book();
            book.bookID =""+(i + 1);
            book.bookName = "Lập trình Android";
            book.amount = 100;
            book.price = 150000;
            bookList.add(book);
        }

        StatisticalBookAdapter adapter = new StatisticalBookAdapter(this, bookList);
        lvStatistic.setAdapter(adapter);

    }

    public void chooseDate(final TextInputLayout txt) {

        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(StatisticActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //cach 1: sd SimpleDateFormat
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDateFormat.format(calendar.getTime());

                // cach2: gan chuoi truc tiep
                //   String date = dayOfMonth+"/"+(month+1)+"/"+year;
                txt.getEditText().setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();


    }

}


