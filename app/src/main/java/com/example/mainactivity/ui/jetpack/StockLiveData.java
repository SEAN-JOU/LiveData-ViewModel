package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;
import android.content.Context;

///extends Live Data
public class StockLiveData extends LiveData<String> {
    private static StockLiveData sInstance;
    private StockManager stockManager;
    Context context;

    private SimplePriceListener listener = new SimplePriceListener() {
        @Override
        public void onPriceChanged(String price) {
            setValue(price);
        }
    };

    StockLiveData(String symbol, Context context) {
        stockManager = new StockManager(symbol,context);
        this.context =context;
    }

    public static StockLiveData getInstance(String symbol, Context context) {
        if (sInstance == null) {
            sInstance = new StockLiveData(symbol,context);
        }
        return  sInstance;
    }

    @Override
    protected void onActive() {
        super.onActive();
        stockManager.requestPriceUpdates(listener);
    }

    // 當LiveData對象具有活動觀察者時調用該方法


    @Override
    protected void onInactive() {
        super.onInactive();
        stockManager.removeUpdates(listener);
    }

    //當對LiveData像沒有任何活動觀察者時調用該方法
}
