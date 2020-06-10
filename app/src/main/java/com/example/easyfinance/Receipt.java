package com.example.easyfinance;
import java.util.ArrayList;

public class Receipt {

    String shop;
    String imagePath;
    ArrayList<String> produkte = new ArrayList<String>();
    ArrayList<Integer> preise = new ArrayList<Integer>();

    public Receipt(String imagePath, String shop) {
        this.imagePath = imagePath;
        this.shop = shop;
    }
}
