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

public class BookAdapter extends BaseAdapter {
    Context context;
    List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
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
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent,false);
        TextView tvBookID, tvBookName,tvPriceOfBook,tvNumberOfBook;
        tvBookID = convertView.findViewById(R.id.tv_ID_book);
        tvBookName = convertView.findViewById(R.id.tv_name_book);
        tvNumberOfBook = convertView.findViewById(R.id.tv_number_book);
        tvPriceOfBook = convertView.findViewById(R.id.tv_price_book);

        Book book = bookList.get(position);
        tvBookID.setText("Mã sách: "+book.bookID);
        tvBookName.setText("Tên sách: "+book.bookName);
        tvNumberOfBook.setText("SL: "+book.amount);
        tvPriceOfBook.setText("Giá bán: "+book.price +" vnđ");

        return convertView;
    }
}
