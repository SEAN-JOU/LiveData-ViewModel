package com.example.mainactivity.ui.jetpack;

import android.content.Context;

import android.widget.Toast;

public class StockManager {
    private String symbol;
    private SimplePriceListener listener;
    Context context;

    StockManager(String symbol, Context context) {
        this.symbol = symbol;
        this.context =context;
    }

    void updatePrice(String price) {
        listener.onPriceChanged(price);
    }


    void requestPriceUpdates(SimplePriceListener listener) {
        this.listener = listener;
        updatePrice(symbol);
    }

    void removeUpdates(SimplePriceListener listener) {
        this.listener = listener;
        Toast.makeText(context,"null",Toast.LENGTH_SHORT).show();
    }
}
