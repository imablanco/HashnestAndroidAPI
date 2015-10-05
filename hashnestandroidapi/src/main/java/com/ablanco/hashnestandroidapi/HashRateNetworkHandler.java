package com.ablanco.hashnestandroidapi;

import android.content.Context;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class HashRateNetworkHandler {

    public static String getHashRate(Context context) throws HashnestClientException {
        return NetworkManager.getInstance(context).get(NetworkConstants.HASH_ACCOUNT_PATH,null);
    }
}
