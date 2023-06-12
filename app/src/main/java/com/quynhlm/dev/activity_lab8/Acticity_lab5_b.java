package com.quynhlm.dev.activity_lab8;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Acticity_lab5_b extends AppCompatActivity {

    ListView listView;

    Toolbar toolbar;

    ArrayList<Sinh_Vien_model> list;

    SearchView searchView;

    Sinh_Vien_Adapter sinh_vien_adapter;


    private void minmap() {
        listView = findViewById(R.id.listView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sinh Vien");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = new ArrayList<>();
    }

    ActivityResultLauncher<Intent> nhan_du_lieu = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                String co_so = intent.getStringExtra(Activity_Lab5_a.KEY_COSO);
                String name = intent.getStringExtra(Activity_Lab5_a.KEY_NAME);
                String diaChi = intent.getStringExtra(Activity_Lab5_a.KEY_DIACHI);
                list.add(new Sinh_Vien_model(co_so, name, diaChi));
                fill();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_lab5_b);
        setSupportActionBar(toolbar);
        docFile();
        minmap();
        list.add(new Sinh_Vien_model("FPOLY HA NOI", "QUYNH", "VINH PHUC"));
        list.add(new Sinh_Vien_model("FPOLY HO CHI MINH", "QUYNH1", "VINH PHUC"));
        list.add(new Sinh_Vien_model("FPOLY CAN THO", "QUYNH2", "VINH PHUC"));
        list.add(new Sinh_Vien_model("FPOLY DA NANG", "QUYNH3", "VINH PHUC"));
        ghiFile();
        fill();
    }

    public void fill() {
        sinh_vien_adapter = new Sinh_Vien_Adapter(this, list, list);
        listView.setAdapter(sinh_vien_adapter);
    }

    public void deleteItem(int position) {
        // AlertDialog.Builder để xây dựng hộp thoại xác nhận xóa
        AlertDialog.Builder builder = new AlertDialog.Builder(Acticity_lab5_b.this);
        builder.setTitle("Xoa Sinh Vien");
        builder.setMessage("Ban co chac chan muon xoa sinh vien nay khong ??");
        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
            // setPositiveButton() để thiết lập nút "Xóa"
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                ghiFile();
                fill();
            }
        });
        //setNegativeButton() để thiết lập nút "Hủy" trong hộp thoại
        builder.setNegativeButton("Huy", null);
        builder.show();
        //show() để hiển thị hộp thoại xác nhận xóa cho người dùng.
    }

    ActivityResultLauncher<Intent> sua_du_lieu = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                String co_so = intent.getStringExtra(Activity_Lab5_a.KEY_COSO);
                String name = intent.getStringExtra(Activity_Lab5_a.KEY_NAME);
                String diaChi = intent.getStringExtra(Activity_Lab5_a.KEY_DIACHI);
                svmodel.setCo_so(co_so);
                svmodel.setName(name);
                svmodel.setDiaChi(diaChi);
                ghiFile();
                fill();
            }
        }
    });
    public static final String KEY_SV_MODEL = "sv_model";

    public void Update(int position) {
        Intent intent = new Intent(Acticity_lab5_b.this, Activity_Lab5_a.class);
        svmodel = list.get(position);
        intent.putExtra(KEY_SV_MODEL, String.valueOf(svmodel));
        sua_du_lieu.launch(intent);
    }

    private Sinh_Vien_model svmodel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sinh_vien_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sinh_vien_adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(Acticity_lab5_b.this, Activity_Lab5_a.class);
            nhan_du_lieu.launch(intent);
        }
        if (item.getItemId() == R.id.menu_dangxuat) {
            startActivity(new Intent(Acticity_lab5_b.this, Activity_Dang_Nhap.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static String file_name = "sinh_vien.txt";

    public void ghiFile() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void docFile() {
        try {
            FileInputStream fileInputStream = openFileInput(file_name);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<Sinh_Vien_model>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}