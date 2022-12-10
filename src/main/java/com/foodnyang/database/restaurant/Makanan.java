package com.foodnyang.database.restaurant;

public class Makanan {
    private Integer idMenu, idResto, harga, stok;
    private String gambarMenu, namaMenu, deskripsiMenu;

    public Makanan (int idMenu, int idResto, String gambarMenu, String namaMenu, String deskripsiMenu, int harga, int stok){
        this.idMenu = idMenu;
        this.idResto = idResto;
        this.gambarMenu = gambarMenu;
        this.namaMenu = namaMenu;
        this.deskripsiMenu = deskripsiMenu;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdResto() {
        return idResto;
    }

    public Integer getIdMenu() {return this.idMenu;}
    public String getGambarMenu() {return this.gambarMenu;}
    public String getNamaMenu() {return this.namaMenu;}
    public String getDeskripsiMenu() {return this.deskripsiMenu;}
    public Integer getHarga() {return harga;}
    public Integer getStok() {return stok;}
}
