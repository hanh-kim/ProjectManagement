package com.example.projectmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.model.BookType;

import java.util.List;

public class BookCategoryAdapter extends BaseAdapter {
    Context context;
    List<BookType> categoryList;

    public BookCategoryAdapter(Context context, List<BookType> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
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
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent,false);
        TextView textView = convertView.findViewById(R.id.tv_name_category);
        BookType bookType =  categoryList.get(position);
        textView.setText(bookType.name);
        return convertView;
    }
}
