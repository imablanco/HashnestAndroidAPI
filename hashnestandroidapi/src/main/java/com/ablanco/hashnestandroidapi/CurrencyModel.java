package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 3/10/15
 * HashnestAndroidAPI
 */
public class CurrencyModel {

    protected CurrencyType currency;
    protected String amount;
    protected String blocked;
    protected String total;

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
