package com.quynhlm.dev.activity_lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Activity_Lab5_a extends AppCompatActivity {

    Spinner spinner;
    EditText edtUserName;
    EditText edtDiaChi;
    Button btnSibmit;

    public static String KEY_COSO = "co_so";
    public static String KEY_NAME = "user_name";
    public static String KEY_DIACHI = "dia_chi";
    ArrayList<Co_so_Model> list;

    private void minmap() {
        spinner = findViewById(R.id.spinner);
        edtUserName = findViewById(R.id.edtusername_them);
        edtDiaChi = findViewById(R.id.edtdiaChi_them);
        btnSibmit = findViewById(R.id.btnSuvmid);
        list = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5_a);
        minmap();
        list.add(new Co_so_Model("Fpoly HaNoi", R.drawable.hn));
        list.add(new Co_so_Model("Fpoly Ho chi Minh", R.drawable.hcm));
        list.add(new Co_so_Model("Fpoly Da nang", R.drawable.dn));
        list.add(new Co_so_Model("Fpoly Can tho", R.drawable.ct));

        Co_so_Adapter co_so_adapter = new Co_so_Adapter(this, list);
        spinner.setAdapter(co_so_adapter);

        btnSibmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posi = spinner.getSelectedItemPosition();
                String co_so = list.get(posi).getName();
                String name = edtUserName.getText().toString();
                String diachi = edtDiaChi.getText().toString();

                Intent intent = new Intent(Activity_Lab5_a.this, Acticity_lab5_b.class);
                intent.putExtra(KEY_COSO, co_so);
                intent.putExtra(KEY_NAME, name);
                intent.putExtra(KEY_DIACHI, diachi);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}