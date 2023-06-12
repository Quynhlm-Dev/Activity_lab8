package com.quynhlm.dev.activity_lab8;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class Sinh_Vien_Adapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Sinh_Vien_model> list, listOld;


    public Sinh_Vien_Adapter(Context context, ArrayList<Sinh_Vien_model> list, ArrayList<Sinh_Vien_model> listOld) {
        this.context = context;
        this.list = list;
        this.listOld = listOld;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Sinh_Vien_model> getList() {
        return list;
    }

    public void setList(ArrayList<Sinh_Vien_model> list) {
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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_sinh_vien, parent, false);

        TextView txtco_so = convertView.findViewById(R.id.txtCo_so);
        TextView txtname = convertView.findViewById(R.id.txtname_sinh_vien);
        TextView txtdiachi = convertView.findViewById(R.id.txtdiaChi);

        Button btnDelete = convertView.findViewById(R.id.btndelete);
        Button btnUpdate = convertView.findViewById(R.id.btnupdata);

        txtco_so.setText(list.get(position).getCo_so());
        txtname.setText(list.get(position).getName());
        txtdiachi.setText(list.get(position).getDiaChi());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Acticity_lab5_b) context).deleteItem(position);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Acticity_lab5_b) context).Update(position);
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String sv = constraint.toString();
                if (sv.isEmpty()) {
                    list = listOld;
                } else {
                    ArrayList<Sinh_Vien_model> lists = new ArrayList<>();
                    for (Sinh_Vien_model sv1 : listOld) {
                        if (sv1.getName().toLowerCase().contains(sv.toLowerCase())) {
                            lists.add(sv1);
                        }
                    }
                    list = lists;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Sinh_Vien_model>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
