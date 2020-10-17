package com.example.duan1_manager;

public class SanPham {
    int id ;
    int madonhang ;
    int giasanpham ;
    String tensanpham ;
    int tonggiasanpham ;
    String soluongsanpham ;
    String tenkhachhang;
    String sodienthoai ;
    String email ;
    String diachikhachhang ;
    String ghichu ;

    public SanPham(int id,int madonhang,int giasanpham, String tensanpham, int tonggiasanpham, String soluongsanpham, String tenkhachhang, String sodienthoai, String email,String diachikhachhang, String ghichu) {
        this.id = id;
        this.madonhang = madonhang;
        this.giasanpham = giasanpham;
        this.tensanpham = tensanpham;
        this.tonggiasanpham = tonggiasanpham;
        this.soluongsanpham = soluongsanpham;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachikhachhang = diachikhachhang;
        this.ghichu = ghichu ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getTonggiasanpham() {
        return tonggiasanpham;
    }

    public void setTonggiasanpham(int tonggiasanpham) {
        this.tonggiasanpham = tonggiasanpham;
    }

    public String getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(String soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachikhachhang() {
        return diachikhachhang;
    }

    public void setDiachikhachhang(String diachikhachhang) {
        this.diachikhachhang = diachikhachhang;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
