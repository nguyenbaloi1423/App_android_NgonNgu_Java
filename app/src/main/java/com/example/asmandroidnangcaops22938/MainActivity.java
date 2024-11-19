package com.example.asmandroidnangcaops22938;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asmandroidnangcaops22938.dao.DangKyMonHocDAO;
import com.example.asmandroidnangcaops22938.model.MonHoc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMonHoc = findViewById(R.id.btnMonHoc);
        Button btnBanDo = findViewById(R.id.btnBanDo);
        Button btnTinTuc = findViewById(R.id.btnTinTuc);
        Button btnMangXaHoi = findViewById(R.id.btnMangXaHoi);

        btnMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MonHocActivity.class));
            }
        });

        btnTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TinTucActivity.class));
            }
        });

        btnBanDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        btnMangXaHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChiaSeChoThayDiActivity.class));
            }
        });
    }
}