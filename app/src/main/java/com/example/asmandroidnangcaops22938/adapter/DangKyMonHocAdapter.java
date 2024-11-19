package com.example.asmandroidnangcaops22938.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asmandroidnangcaops22938.DangKyMonHocActivity;
import com.example.asmandroidnangcaops22938.R;
import com.example.asmandroidnangcaops22938.model.MonHoc;
import com.example.asmandroidnangcaops22938.model.ThongTin;
import com.example.asmandroidnangcaops22938.service.DKMonHocSevice;

import java.util.ArrayList;
import java.util.HashMap;

public class DangKyMonHocAdapter extends RecyclerView.Adapter<DangKyMonHocAdapter.ViewHoler> {

    private Context context;
    private ArrayList<MonHoc> list;
    private int id;
    private boolean isAll;

    public DangKyMonHocAdapter(ArrayList<MonHoc> list, Context context, int id, boolean isAll) {
        this.list = list;
        this.context = context;
        this.id = id;
        this.isAll = isAll;
    }


    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((DangKyMonHocActivity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dangkymonhoc, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.txtCode.setText(String.valueOf(list.get(position).getCode()));
        holder.txtName.setText(list.get(position).getName());
        holder.txtTeacher.setText(list.get(position).getTeacher());

        if (list.get(position).getIsRegister() == id) {
            holder.btnDangky.setText("Hủy Đăng ký môn học");
            holder.btnDangky.setBackgroundColor(Color.YELLOW);
            holder.btnDangky.setTextColor(Color.GREEN);

        } else {
            holder.btnDangky.setText("Đăng ký môn học");
            holder.btnDangky.setBackgroundColor(Color.BLUE);
            holder.btnDangky.setTextColor(Color.RED);
        }

        holder.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DKMonHocSevice.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("code", list.get(holder.getAdapterPosition()).getCode());
                bundle.putInt("isRegister", list.get(holder.getAdapterPosition()).getIsRegister());
                bundle.putBoolean("isAll", isAll);
                intent.putExtras(bundle);
                context.startService(intent);
            }
        });

        holder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()).getListTT());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView txtCode, txtName, txtTeacher;
        Button btnDangky;
        ImageView ivDetail;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            btnDangky = itemView.findViewById(R.id.btnDangKy);
            ivDetail = itemView.findViewById(R.id.ivDetail);
        }
    }

    private void showDialog(ArrayList<ThongTin> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thongtin, null);
        builder.setView(view);

        ListView listViewTT = view.findViewById(R.id.listViewTT);

        ArrayList<HashMap<String, Object>> listTT = new ArrayList<>();
        for (ThongTin tt : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("date", "Ngày Học:" + tt.getDate());
            hs.put("address", "Địa điểm:" + tt.getAddress());
            listTT.add(hs);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                context,
                listTT,
                android.R.layout.simple_list_item_2,
                new String[]{"date", "address"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        listViewTT.setAdapter(adapter);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
