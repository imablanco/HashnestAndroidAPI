package com.ablanco.hashnestandroidapi;

import android.content.Context;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class AccountInfoNetworkHandler {

    public static String getAccountInfo(Context c) throws HashnestClientException {
        return NetworkManager.getInstance(c).get(NetworkConstants.ACCOUNT_PATH,null);
    }
}
