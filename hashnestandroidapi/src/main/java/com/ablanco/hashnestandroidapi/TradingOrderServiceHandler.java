package com.ablanco.hashnestandroidapi;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class TradingOrderServiceHandler {

    public void getActiveEntrustOrder(final String marketId,NetworkResponseListener<ArrayList<EntrustTradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<EntrustTradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.getActiveEntrustOrder(marketId);
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

    public void getTradingOrder(final String marketId,NetworkResponseListener<ArrayList<TradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<TradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<TradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.getTradingOrder(marketId, -1, -1);
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

    public void getTradingOrder(final String marketId, final int page, final int limit,NetworkResponseListener<ArrayList<TradingOrderModel>> listener){
        BaseServiceAsyncTask<ArrayList<TradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<TradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.getTradingOrder(marketId, page, limit);
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

    public void createEntrustOrder(final String marketId, final float amount,final float pricePerUnit, final TradingOrderType tradingOrderType,NetworkResponseListener<EntrustTradingOrderModel> listener){
        BaseServiceAsyncTask<EntrustTradingOrderModel> task = new BaseServiceAsyncTask<EntrustTradingOrderModel>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.createEntrustOrder(marketId, String.valueOf(amount), String.valueOf(pricePerUnit), tradingOrderType);
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

    public void cancelEntrusOrder(final String orderId,NetworkResponseListener<Boolean> listener){
        BaseServiceAsyncTask<Boolean> task = new BaseServiceAsyncTask<Boolean>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.cancelEntrustOrder( orderId);
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

    public void cancelAllEntrustOrders(final String marketId,NetworkResponseListener<Boolean> listener){
        BaseServiceAsyncTask<Boolean> task = new BaseServiceAsyncTask<Boolean>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = TradingOrderNetworkHandler.cancelAllEntrustOrders( marketId);
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
