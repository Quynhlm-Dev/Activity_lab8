package com.quynhlm.dev.activity_lab8;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;

public class Activity_Dang_Nhap extends AppCompatActivity {

    private TextInputEditText edtusername;
    private TextInputEditText edtpassword;
    private CheckBox chkremember;
    private Button btnDangNhap;
    private Button btnDangKy;

    private String tk;

    private String mk;

    String username;

    String password;

    String username_dk;
    String password_dk;


    ActivityResultLauncher<Intent> getdata = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        edtusername.setText(intent.getStringExtra(Activity_Dang_ky.KEY_USERNAME));
                        edtpassword.setText(intent.getStringExtra(Activity_Dang_ky.KEY_PASSWORD));
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        minmap();


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtusername.getText().toString();
                password = edtpassword.getText().toString();
                docfile_dk();
                if (username.equals(username_dk) && password.equals(password_dk)) {
                    startActivity(new Intent(Activity_Dang_Nhap.this, Acticity_lab5_b.class));
                    Toast.makeText(Activity_Dang_Nhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity_Dang_Nhap.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Dang_Nhap.this, Activity_Dang_ky.class);
                getdata.launch(intent);
            }
        });
    }

    private void minmap() {
        edtusername = findViewById(R.id.edtUsername);
        edtpassword = findViewById(R.id.edtpassword);
        btnDangNhap = findViewById(R.id.btndangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        chkremember = findViewById(R.id.chkremember);
    }

//    private void docFile() {
//        SharedPreferences lay = getSharedPreferences("data", MODE_PRIVATE);
//        u = lay.getString("username", "");
//        p = lay.getString("password", "");
//    }

    private void docfile_dk() {
        try {
            FileInputStream fileInputStream = openFileInput(Activity_Dang_ky.file_name);
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while ((read = fileInputStream.read()) != -1) {
                builder.append((char) read);
            }
            String data = builder.toString();
            String arrdata[] = data.split(",");
            if (arrdata != null && arrdata.length > 0) {
                username_dk = arrdata[0];
                password_dk = arrdata[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
