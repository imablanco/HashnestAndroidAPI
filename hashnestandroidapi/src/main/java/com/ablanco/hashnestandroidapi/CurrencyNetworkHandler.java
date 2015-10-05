package com.ablanco.hashnestandroidapi;

import android.content.Context;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class CurrencyNetworkHandler {

    public static String getCurrency(Context context) throws HashnestClientException {
        return NetworkManager.getInstance(context).get(NetworkConstants.CURRENCY_PATH,null);
    }
}
