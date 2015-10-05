package com.ablanco.hashnestandroidapi;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class OpenMarketNetworkHandler {

    public static String getOpenedMarkets(Context context) throws HashnestClientException {

        return NetworkManager.getInstance(context).get(NetworkConstants.OPEN_MARKETS_PATH,null);
    }

    public static String getTradingOrderHistory(Context context,String marketId,TradingOrderType tradingOrderType) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{marketId,tradingOrderType.getValue()});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_MARKET_ID,marketId);
        params.put(NetworkConstants.PARAM_CATEGORY,tradingOrderType.getValue());

        return NetworkManager.getInstance(context).get(NetworkConstants.MARKET_HISTORY_PATH,params);
    }
}
