package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class AccountInfoNetworkHandler {

    public static String getAccountInfo() throws HashnestClientException {
        return NetworkManager.getInstance().get(NetworkConstants.ACCOUNT_PATH,null);
    }
}
