package com.quynhlm.dev.activity_lab8;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Co_so_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Co_so_Model> list;

    public Co_so_Adapter(Context context, ArrayList<Co_so_Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.item_co_so, parent, false);

        TextView txtname = convertView.findViewById(R.id.txtname);
        ImageView imglogo = convertView.findViewById(R.id.imglogo);

        txtname.setText(list.get(position).getName());
        imglogo.setImageResource(list.get(position).getImglogo());
        return convertView;
    }
}
