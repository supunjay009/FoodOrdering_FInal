package com.example.foodordering.Activity.Domain;

public class Cart {

    private String fname,images;
    private  int price,qty,fid;
    public Cart() {
    }

    public Cart(int fid, String fname, String images,int price, int qty) {
        this.fid = fid;
        this.fname = fname;
        this.fname = images;
        this.price = price;
        this.qty = qty;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
