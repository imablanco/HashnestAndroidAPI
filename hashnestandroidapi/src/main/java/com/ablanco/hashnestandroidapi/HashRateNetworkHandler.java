package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class HashRateNetworkHandler {

    public static String getHashRate() throws HashnestClientException {
        return NetworkManager.getInstance().get(NetworkConstants.HASH_ACCOUNT_PATH,null);
    }
}
