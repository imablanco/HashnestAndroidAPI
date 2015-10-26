package com.ablanco.hashnestandroidapi;

import java.util.HashMap;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class OpenMarketNetworkHandler {

    public static String getOpenedMarkets() throws HashnestClientException {

        return NetworkManager.getInstance().get(NetworkConstants.OPEN_MARKETS_PATH,null);
    }

    public static String getTradingOrderHistory(String marketId,TradingOrderType tradingOrderType) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{marketId,tradingOrderType.getValue()});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_MARKET_ID,marketId);
        params.put(NetworkConstants.PARAM_CATEGORY,tradingOrderType.getValue());

        return NetworkManager.getInstance().get(NetworkConstants.MARKET_HISTORY_PATH,params);
    }
}
