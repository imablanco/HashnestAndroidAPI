package com.ablanco.hashnestandroidapi;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class CurrencyServiceHandler {

    public void getCurrency(NetworkResponseListener<ArrayList<CurrencyModel>> listener){

        BaseServiceAsyncTask<ArrayList<CurrencyModel>> task = new BaseServiceAsyncTask<ArrayList<CurrencyModel>>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String response = CurrencyNetworkHandler.getCurrency();
                    ArrayList<CurrencyModel> currencies = GsonMapper.getInstance().createCollection(response,CurrencyModel.class);
                    return currencies;
                }catch (HashnestClientException e){
                    return e;
                }catch (JsonSyntaxException e){
                    return new HashnestClientException(HashnestClientException.SERVER_INVALID_RESPONSE,HashnestClientException.MSG_SERVER_INVALID_RESPONSE);
                }
            }
        };

        task.execute();
    }
}
