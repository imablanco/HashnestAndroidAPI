package com.ablanco.hashnestandroidapi;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class TradingOrderNetworkHandler {

    public static String getActiveEntrustOrder(Context context,String marketId) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{marketId});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_MARKET_ID,marketId);

        return NetworkManager.getInstance(context).get(NetworkConstants.ACTIVE_ORDERS_PATH,params);
    }

    public static String getTradingOrder(Context context,String marketId,int page,int limitPerPage) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{marketId});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_MARKET_ID,marketId);
        if(page >= 0){
            params.put(NetworkConstants.PARAM_PAGE,String.valueOf(page));
        }

        if(limitPerPage > 0){
            params.put(NetworkConstants.PARAM_PAGE_PER_AMOUNT,String.valueOf(limitPerPage));

        }

        return NetworkManager.getInstance(context).get(NetworkConstants.TRADING_ORDER_PATH,params);
    }


    public static String createEntrustOrder(Context context,String amount,String ppc,TradingOrderType orderType) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{amount,ppc,orderType.getValue()});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_AMOUNT,amount);
        params.put(NetworkConstants.PARAM_PPC,ppc);
        params.put(NetworkConstants.PARAM_CATEGORY,orderType.getValue());

        return NetworkManager.getInstance(context).get(NetworkConstants.ORDERS_PATH,params);
    }

    public static String cancelEntrustOrder(Context context,String orderId) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{orderId});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_ORDER_ID,orderId);

        return NetworkManager.getInstance(context).get(NetworkConstants.CANCEL_ORDER_PATH,params);
    }

    public static String cancelAllEntrustOrders(Context context,String marketId) throws HashnestClientException {
        int error;
        error = NetworkManager.validateParams(new String[]{marketId});
        if(error > 0){
            throw new HashnestClientException(HashnestClientException.INVALID_PARAMS,HashnestClientException.MSG_SERVER_INVALID_PARAMS);
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(NetworkConstants.PARAM_MARKET_ID,marketId);

        return NetworkManager.getInstance(context).get(NetworkConstants.CANCEL_ALL_ORDER_PATH,params);
    }

}
