package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public enum TradingOrderType {
    SALE("sale"),
    PURCHASE("purchase");

    private final String value;

    TradingOrderType(final String value) {
        this.value = value;
    }

    String getValue(){
        return this.value;
    }
}
