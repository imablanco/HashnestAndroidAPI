package com.ablanco.hashnestandroidapi;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class TradingOrderServiceHandler {

    public void getActiveEntrustOrder(final Context c, final String marketId,NetworkResponseListener<ArrayList<EntrustTradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.getActiveEntrustOrder(c,marketId);
                    ArrayList<EntrustTradingOrderModel> entrustTradingOrderModels = GsonMapper.getInstance().createCollection(response,EntrustTradingOrderModel.class);
                    return entrustTradingOrderModels;
                } catch (HashnestClientException e) {
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

    public void getTradingOrder(final Context c, final String orderId,NetworkResponseListener<ArrayList<TradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<TradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<TradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.getTradingOrder(c, orderId, -1, -1);
                    ArrayList<TradingOrderModel> tradingOrderModels = GsonMapper.getInstance().createCollection(response,TradingOrderModel.class);
                    return tradingOrderModels;
                } catch (HashnestClientException e) {
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

    public void getTradingOrder(final Context c, final String orderId, final int page, final int limit,NetworkResponseListener<ArrayList<EntrustTradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.getTradingOrder(c, orderId, page, limit);
                    ArrayList<TradingOrderModel> tradingOrderModels = GsonMapper.getInstance().createCollection(response,TradingOrderModel.class);
                    return tradingOrderModels;
                } catch (HashnestClientException e) {
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

    public void createEntrustOrder(final Context c, final float amount,final float pricePerUnit, final TradingOrderType tradingOrderType,NetworkResponseListener<ArrayList<EntrustTradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.createEntrustOrder(c, String.valueOf(amount), String.valueOf(pricePerUnit), tradingOrderType);
                    EntrustTradingOrderModel tradingOrderModel = GsonMapper.getInstance().createObject(response, EntrustTradingOrderModel.class);
                    return tradingOrderModel;
                } catch (HashnestClientException e) {
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

    public void cancelEntrusOrder(final Context c, final String orderId,NetworkResponseListener<Boolean> listener){
        BaseServiceAsyncTask<Boolean> task = new BaseServiceAsyncTask<Boolean>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.cancelEntrustOrder(c, orderId);
                    Boolean succes = GsonMapper.getInstance().getSuccesResult(response);
                    return succes;
                } catch (HashnestClientException e) {
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

    public void cancelAllEntrustOrders(final Context c, final String marketId,NetworkResponseListener<Boolean> listener){
        BaseServiceAsyncTask<Boolean> task = new BaseServiceAsyncTask<Boolean>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.cancelAllEntrustOrders(c, marketId);
                    Boolean succes = GsonMapper.getInstance().getSuccesResult(response);
                    return succes;
                } catch (HashnestClientException e) {
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

}
