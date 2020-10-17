package com.example.duan1_manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class Fragment_Info extends Fragment {
    TextView tv_tenkhachhang, tv_sodienthoai, tv_email,
            tv_tensanpham, tv_soluongsanpham , tv_giatien, tv_tongtien,
            tv_Diachi, tv_Ghichu, tv_masanpham ;

    String tenkh = "" ;
    String sodt = "" ;
    String emailkh = "" ;
    String tensp = "" ;
    String slsanpham = "" ;
    String diachi = "" ;
    String ghichu = "" ;
    int madonhang = 0 ;
    int giatiensp = 0 ;
    int tongtiensp = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container,false);
        tv_tenkhachhang = view.findViewById(R.id.tv_tenkhachhang);
        tv_sodienthoai = view.findViewById(R.id.tv_sodienthoai);
        tv_email = view.findViewById(R.id.tv_email);
        tv_Diachi = view.findViewById(R.id.tv_info_Diachi);
        tv_Ghichu = view.findViewById(R.id.tv_info_Ghichu);
        tv_masanpham = view.findViewById(R.id.tv_info_masp);
        tv_tensanpham = view.findViewById(R.id.tv_info_tensp);
        tv_soluongsanpham = view.findViewById(R.id.tv_info_soluongsp);
        tv_giatien = view.findViewById(R.id.tv_info_giasp) ;
        tv_tongtien = view.findViewById(R.id.tv_info_TongTien);

        GetInfo();

        return view;
    }

    private void GetInfo() {
        Bundle bundle = getArguments();
        tenkh = bundle.getString("tenkhachhang");
        sodt = bundle.getString("sodienthoai");
        emailkh = bundle.getString("email");
        tensp = bundle.getString("tensanpham") ;
        slsanpham = bundle.getString("soluong");
        madonhang = bundle.getInt("madonhang");
        giatiensp = bundle.getInt("giatien");
        tongtiensp = bundle.getInt("tongtien");
        diachi = bundle.getString("diachikhachhang");
        ghichu = bundle.getString("ghichu");

        tv_tenkhachhang.setText("Tên Khách Hàng : "+tenkh);
        tv_sodienthoai.setText("Số Điện Thoại : "+sodt);
        tv_email.setText("Email : "+emailkh);
        tv_masanpham.setText("Mã Sản Phẩm : "+madonhang);
        tv_tensanpham.setText("Tên Sản Phẩm : "+tensp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_giatien.setText("Giá Sản Phẩm : "+decimalFormat.format(giatiensp) +"đ");
        tv_soluongsanpham.setText("Số Lượng : "+slsanpham);
        tv_tongtien.setText("Tổng Tiền : "+decimalFormat.format(tongtiensp)+"đ");
        tv_Diachi.setText("Địa Chỉ : "+diachi);
        tv_Ghichu.setText("Ghi Chú : "+ghichu);


    }
}
