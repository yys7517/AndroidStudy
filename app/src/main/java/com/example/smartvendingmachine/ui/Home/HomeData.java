package com.example.smartvendingmachine.ui.Home;

public class HomeData {
    private String DRID;
    private String DRCode;
    private String DRStock;
    private String DRPrice;

    public String getDRID() {
        return DRID;
    }

    public String getDRCode() {
        return DRCode;
    }

    public String getDRStock() {
        return DRStock;
    }

    public String getDRPrice() { return DRPrice;}

    public void setDRID(String DRID) {
        this.DRID = DRID;
    }

    public void setDRCode(String DRCode) {
        this.DRCode = DRCode;
    }

    public void setDRStock(String DRStock) {
        this.DRStock = DRStock;
    }

    public void setDRPrice(String DRPrice) { this.DRPrice = DRPrice; }
}

