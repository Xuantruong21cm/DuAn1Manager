package com.example.duan1_manager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Fragment_ThemSP extends Fragment {
    EditText edt_tensanpham, edt_giasanpham, edt_motasanpham ;
    Spinner spn_maLoaiSP ;
    Button btn_XacNhan_Themsp ,btn_QuayLai_Themsp ;
    TextView edt_hinhanhsanpham ;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    String image_code = "";
    Bitmap bitmap ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themsp,container,false);
        edt_tensanpham = view.findViewById(R.id.edt_tensanpham);
        edt_giasanpham = view.findViewById(R.id.edt_giasanpham);
        edt_hinhanhsanpham = view.findViewById(R.id.edt_hinhanhsanpham);
        edt_motasanpham = view.findViewById(R.id.edt_motasanpham);
        spn_maLoaiSP = view.findViewById(R.id.spn_maLoaiSP);
        btn_XacNhan_Themsp = view.findViewById(R.id.btn_XacNhan_Themsp) ;
        btn_QuayLai_Themsp = view.findViewById(R.id.btn_QuayLai_Themsp);
        CatchEvenSpiner();
        initListener();



        return view;
    }
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    if (data.getData().getPath() != null) {
                        edt_hinhanhsanpham.setText(data.getData().getPath());
                    }
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    image_code = encodeImage(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {

            }

        } else {
            Log.d("sss", "onActivityResult: " + data.getData().toString());
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm = Bitmap.createScaledBitmap(bm, 210, 210, true);
        bm.compress(Bitmap.CompressFormat.JPEG, 78, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("length", "encodeImage: " + encImage.length());

        return encImage;
    }

    private void CatchEvenSpiner() {
        String[] idsanpham = new String[]{"1.Thịt Lợn","2.Thịt Bò","3.Thịt Gà","4.Thịt Cá"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,idsanpham);
        spn_maLoaiSP.setAdapter(arrayAdapter);
    }

    private void initListener() {
        edt_hinhanhsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        //permission grandted
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission already grandted
                        pickImageFromGallery();
                    }
                } else {
                    //system os is less than android 6
                    pickImageFromGallery();
                }
            }
        });

        btn_QuayLai_Themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_XacNhan_Themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tensp  = edt_tensanpham.getText().toString().trim();
                final String giasp = edt_giasanpham.getText().toString().trim();
                final String hinhsp = image_code ;
                final String motasp = edt_motasanpham.getText().toString().trim();
                final String idsp = spn_maLoaiSP.getSelectedItem().toString().replaceAll("[^0-9/]","");
                if (tensp.isEmpty()||giasp.isEmpty()||hinhsp.isEmpty()
                ||motasp.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),"Không Được Để Trống Dữ Liệu",Toast.LENGTH_SHORT).show();
                }else {
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    StringRequest request = new StringRequest(Request.Method.POST, Server_LOcal.ThemSp, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            JSONArray jsonArray = new JSONArray();
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("tensanpham",tensp);
                                jsonObject.put("giasanpham",giasp);
                                jsonObject.put("hinhsanpham",hinhsp);
                                jsonObject.put("motasanpham",motasp);
                                jsonObject.put("idsanpham",idsp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(jsonObject);
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("json",jsonArray.toString());
                            return hashMap;
                        }
                    };
                    requestQueue.add(request);
                    Toast.makeText(getActivity().getApplicationContext(),"Thêm Sản Phẩm Thành Công",Toast.LENGTH_SHORT).show();
                    for (Fragment fragment : getActivity().getSupportFragmentManager().getFragments()){
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was grandted
                    pickImageFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(getContext(), "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
