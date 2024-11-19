package com.example.asmandroidnangcaops22938;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmandroidnangcaops22938.adapter.DangKyMonHocAdapter;
import com.example.asmandroidnangcaops22938.model.MonHoc;
import com.example.asmandroidnangcaops22938.service.DangKyMonHocService;

import java.util.ArrayList;

public class DangKyMonHocActivity extends AppCompatActivity {

    RecyclerView recyclerDangKyMonHoc;
    int id;
    boolean isAll;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_mon_hoc);

        recyclerDangKyMonHoc = findViewById(R.id.RecyclerDangKyMonHoc);
        TextView txtTitle = findViewById(R.id.txtTitle);

        intentFilter = new IntentFilter();
        intentFilter.addAction("DSMonHoc");
        intentFilter.addAction("DKMonHoc");

        //id của người dùng đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        id = sharedPreferences.getInt("id", -1);

        //lấy giá trị isAll
        Intent intentIsAll = getIntent();
        Bundle bundleIsAll = intentIsAll.getExtras();
        isAll = bundleIsAll.getBoolean("isAll");
        if(isAll){
            txtTitle.setText("ĐĂNG KÝ MÔN HỌC");
        }else{
            txtTitle.setText("MÔN HỌC ĐÃ ĐĂNG KÝ");
        }

        Intent intent = new Intent(DangKyMonHocActivity.this, DangKyMonHocService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putBoolean("isAll", isAll);
        intent.putExtras(bundle);
        startService(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcast);
    }

    private  void loadData(ArrayList<MonHoc> list){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerDangKyMonHoc.setLayoutManager(linearLayoutManager);
        DangKyMonHocAdapter adapter = new DangKyMonHocAdapter(list,this , id, isAll);
        recyclerDangKyMonHoc.setAdapter(adapter);

    }

    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "DSMonHoc":
                case "DKMonHoc":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check",true);

                    if(check){
                        ArrayList<MonHoc>list = (ArrayList<MonHoc>) bundle.getSerializable("list");
                        loadData(list);
                    }else{
                        Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    break;
            }
        }
    };
}