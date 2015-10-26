package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class CurrencyNetworkHandler {

    public static String getCurrency() throws HashnestClientException {
        return NetworkManager.getInstance().get(NetworkConstants.CURRENCY_PATH,null);
    }
}
