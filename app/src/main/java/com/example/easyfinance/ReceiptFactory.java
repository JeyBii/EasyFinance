package com.example.easyfinance;

public class ReceiptFactory {

    private ReceiptFactory instance = null;

    private ReceiptFactory() {
    }
    public ReceiptFactory getInstance() {

        if (instance == null)
            instance = new ReceiptFactory();

        return instance;
    }

    public void createReceipt(String imagePath, String shop) {

        Receipt receipt = new Receipt(imagePath, shop);

    }
}
