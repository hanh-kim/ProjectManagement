package com.example.projectmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.model.InfoUser;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Context context;
    List<InfoUser> listUser;
    public UserAdapter(Context context, List<InfoUser> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user,parent,false);

        TextView tvUserID = convertView.findViewById(R.id.tv_username);
        TextView tvUsername = convertView.findViewById(R.id.tv_fullname);

        InfoUser user = listUser.get(position);

        tvUserID.setText("Username: "+user.username);
        tvUsername.setText("Họ tên: "+user.fullName);
        return convertView;
    }
}
