package com.ablanco.hashnestandroidapi;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class AccountInfoServiceHandler {

    public void getAccountInfo(final Context context,NetworkResponseListener<AccountInfoModel> listener){

        BaseServiceAsyncTask<AccountInfoModel> task = new BaseServiceAsyncTask<AccountInfoModel>(listener){
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    String respone = AccountInfoNetworkHandler.getAccountInfo(context);
                    AccountInfoModel accountInfoModel = GsonMapper.getInstance().createObject(respone,AccountInfoModel.class);
                    return accountInfoModel;
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
