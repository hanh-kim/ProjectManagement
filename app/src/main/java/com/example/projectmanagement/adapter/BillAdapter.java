package com.example.projectmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectmanagement.R;
import com.example.projectmanagement.model.Bill;

import java.util.List;

public class BillAdapter extends BaseAdapter {
Context context;
List<Bill> billList;

    public BillAdapter(Context context, List<Bill> billList) {
        this.context = context;
        this.billList = billList;
    }

    @Override
    public int getCount() {
        return billList.size();
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
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent,false);
        TextView tvBillID, tvCustomerName,tvDate;
        tvBillID = convertView.findViewById(R.id.tv_ID_bill);
        tvDate = convertView.findViewById(R.id.tv_date_bill);
        Bill bill = billList.get(position);
        tvBillID.setText("Mã HĐ: "+bill.billID);

        tvDate.setText("Ngày mua: "+bill.date);
        return convertView;
    }
}
