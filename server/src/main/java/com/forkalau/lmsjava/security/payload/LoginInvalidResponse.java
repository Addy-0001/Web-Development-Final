package com.forkalau.lmsjava.security.payload;

public class LoginInvalidResponse {
    private String barcode;
    private String password;

    public LoginInvalidResponse() {
        this.barcode = "Invalid Barcode";
        this.password = "Invalid Password";
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}