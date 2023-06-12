package com.quynhlm.dev.activity_lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Activity_Dang_ky extends AppCompatActivity {

    TextInputEditText edtusername;
    TextInputEditText edtpassword;
    TextInputEditText nhapLai;
    Button btnDangNhap;
    Button btnTroVe;

    String username;

    String password;

    public static String KEY_USERNAME = "username";

    public static String KEY_PASSWORD = "password";

    private void minmap() {
        edtusername = findViewById(R.id.edtUsername_dk);
        edtpassword = findViewById(R.id.edtpassword_dk);
        nhapLai = findViewById(R.id.edtNhaplai);
        btnDangNhap = findViewById(R.id.btndangNhap_dk);
        btnTroVe = findViewById(R.id.btnTroLai);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        minmap();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtusername.getText().toString();
                password = edtpassword.getText().toString();
                Intent intent = new Intent(Activity_Dang_ky.this, Activity_Dang_Nhap.class);
                intent.putExtra(KEY_USERNAME, username);
                intent.putExtra(KEY_PASSWORD, password);
                setResult(RESULT_OK, intent);
                finish();
                ghiFile();
            }
        });
    }
    public static String file_name = "acount.txt";

    public void ghiFile() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(username.getBytes());
            fileOutputStream.write(",".getBytes());
            fileOutputStream.write(password.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Ghi File thanh cong", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}