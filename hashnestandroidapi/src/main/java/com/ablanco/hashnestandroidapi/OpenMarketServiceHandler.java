package com.ablanco.hashnestandroidapi;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class OpenMarketServiceHandler {

    public void getOpenedMarkets(final Context context,NetworkResponseListener<ArrayList<OpenMarketModel>> listener){

        BaseServiceAsyncTask<ArrayList<OpenMarketModel>> task = new BaseServiceAsyncTask<ArrayList<OpenMarketModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = OpenMarketNetworkHandler.getOpenedMarkets(context);
                    ArrayList<OpenMarketModel> openMarketModels = GsonMapper.getInstance().createCollection(response,OpenMarketModel.class);
                    return openMarketModels;
                } catch (HashnestClientException e){
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }

    public void getTradingHistpry(final Context context, final String marketId, final TradingOrderType tradingOrderType,NetworkResponseListener<ArrayList<TradingOrderModel>> listener){

        BaseServiceAsyncTask<ArrayList<TradingOrderModel>> task = new BaseServiceAsyncTask<ArrayList<TradingOrderModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = OpenMarketNetworkHandler.getTradingOrderHistory(context,marketId,tradingOrderType);
                    ArrayList<TradingOrderModel> tradingOrderModels = GsonMapper.getInstance().createCollection(response,TradingOrderModel.class);
                    return tradingOrderModels;
                } catch (HashnestClientException e){
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }
}
