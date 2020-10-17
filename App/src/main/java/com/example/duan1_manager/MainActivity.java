package com.example.duan1_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView_Oder ;
    List<SanPham> sanPhamList ;
    Adapter_Oder adapter_oder ;
    LinearLayoutManager layoutManager ;
    ImageView img_quanLySP, img_refesh, img_exit ;
    ProgressBar Prb_refresh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView_Oder = findViewById(R.id.recyceView_Oder);
        initUI();
        OrderView();
        initListener();
        ShowInfo();


    }

    private void initUI() {
        img_quanLySP = findViewById(R.id.img_quanlySP);
        img_refesh = findViewById(R.id.img_refesh);
        img_exit = findViewById(R.id.img_exit);
        Prb_refresh = findViewById(R.id.Prb_refresh);
    }

    private void initListener() {
        img_quanLySP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),img_quanLySP);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_ThemSP :
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment fragment = new Fragment_ThemSP();
                                fragmentTransaction.replace(R.id.frame_main,fragment).commit();
                                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
                                break;
                            case R.id.menu_QuanLySP :
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        img_refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prb_refresh.setVisibility(View.VISIBLE);
                CountDownTimer countDownTimer = new CountDownTimer(3000,3000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Prb_refresh.setVisibility(View.INVISIBLE);
                        recreate();
                    }
                }.start();
            }
        });

        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thoát Ứng Dụng ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             System.exit(0);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });


    }

    private void ShowInfo() {
        adapter_oder.onClickListener(new MyOnClick() {
            @Override
            public void onClick(SanPham sanPham) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new Fragment_Info();


                Bundle bundle = new Bundle();
                bundle.putInt("madonhang",sanPham.getMadonhang());
                bundle.putString("tensanpham",sanPham.getTensanpham());
                bundle.putString("soluong",sanPham.getSoluongsanpham());
                bundle.putInt("giatien",sanPham.getGiasanpham());
                bundle.putInt("tongtien",sanPham.getTonggiasanpham());
                bundle.putString("tenkhachhang",sanPham.getTenkhachhang());
                bundle.putString("sodienthoai",sanPham.getSodienthoai());
                bundle.putString("email",sanPham.getEmail());
                bundle.putString("diachikhachhang",sanPham.getDiachikhachhang());
                bundle.putString("ghichu",sanPham.ghichu);
                fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.frame_main,fragment).commit();
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
        });
    }


    private void OrderView() {
        Prb_refresh.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server_LOcal.Sp_New, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i<response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        int madonhang =jsonObject.getInt("madonhang");
                        int masanpham = jsonObject.getInt("masanpham");
                        String tensanpham = jsonObject.getString("tensanpham");
                        int tonggiasanpham = jsonObject.getInt("giasanpham");
                        String soluongsanpham = jsonObject.getString("soluongsanpham") ;
                        String tenkhachhang = jsonObject.getString("tenkhachhang");
                        String sodienthoai = jsonObject.getString("sodienthoai") ;
                        String email = jsonObject.getString("email") ;
                        String diachi = jsonObject.getString("diachikhachhang");
                        String ghichu = jsonObject.getString("ghichu") ;
                        sanPhamList.add(new SanPham(id,madonhang,masanpham,tensanpham,tonggiasanpham,soluongsanpham,tenkhachhang,sodienthoai,email,diachi,ghichu));
                        adapter_oder.notifyDataSetChanged();
                    }
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Thất bại",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        sanPhamList = new ArrayList<>();
        adapter_oder = new Adapter_Oder(sanPhamList,MainActivity.this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_Oder.setLayoutManager(layoutManager);
        recyclerView_Oder.setAdapter(adapter_oder);
    }

    public void DeleteSp(final int idsanpham){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server_LOcal.Delete_Oder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Thành công")){
                    Toast.makeText(getApplicationContext(),"Đã Xóa",Toast.LENGTH_SHORT).show();
                    adapter_oder.notifyItemRemoved(idsanpham);
                    adapter_oder.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idsanpham",String.valueOf(idsanpham));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void noClick(View view) {

    }
}
