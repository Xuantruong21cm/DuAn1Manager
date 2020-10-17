package com.example.duan1_manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_Oder extends RecyclerView.Adapter<Adapter_Oder.ViewHolder>  {
    List<SanPham> list ;
    MainActivity context ;
    MyOnClick onClick ;
    public void onClickListener(MyOnClick myOnClick){
        this.onClick = myOnClick;
    }

    public Adapter_Oder(List<SanPham> list, MainActivity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_oder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final SanPham sanPham = list.get(position);
        holder.tv_Item_tensp.setText(sanPham.getTensanpham());
        holder.tv_Item_soluongsp.setText("Số lượng : "+sanPham.getSoluongsanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tv_Item_giasp.setText("Giá : "+decimalFormat.format(sanPham.getGiasanpham())+"đ");
        holder.tv_Item_TongTiensp.setText("Tổng tiền : "+decimalFormat.format(sanPham.getTonggiasanpham())+ "đ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(list.get(position));
            }
        });

        holder.img_remove_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa đơn hàng "+sanPham.getTensanpham());
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.DeleteSp(sanPham.getId());
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Item_tensp,tv_Item_giasp ,tv_Item_TongTiensp ;
        TextView tv_Item_soluongsp ;
        ImageView img_remove_Item ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_remove_Item = itemView.findViewById(R.id.img_remove_Item);
            tv_Item_tensp = itemView.findViewById(R.id.tv_Item_tensp);
            tv_Item_giasp = itemView.findViewById(R.id.tv_Item_giasp);
            tv_Item_soluongsp = itemView.findViewById(R.id.tv_Item_slsp);
            tv_Item_TongTiensp = itemView.findViewById(R.id.tv_Item_TongTiensp);
        }
    }
}
