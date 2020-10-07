package com.example.projectmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.model.Book;

import java.util.List;

public class StatisticalBookAdapter extends BaseAdapter {

    Context context;
    List<Book> bookList;

    public StatisticalBookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.statistical_book_item, parent,false);
        TextView tvRank, tvName, tvID,tvNumberSold;
        tvRank = convertView.findViewById(R.id.tv_rank_number);
        tvName = convertView.findViewById(R.id.tv_name_book_statistic);
        tvID = convertView.findViewById(R.id.tv_id_book_statistic);
        tvNumberSold = convertView.findViewById(R.id.tv_number_book_sold);
        Book book = bookList.get(position);

        tvRank.setText(String.valueOf(position));
        tvName.setText("Tên sách: "+book.bookName);
        tvID.setText("Mã sách: "+book.bookID);
        tvNumberSold.setText("price: "+book.price);
        return convertView;
    }
}
