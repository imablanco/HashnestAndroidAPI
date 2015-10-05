package com.ablanco.hashnestandroidapi;

import android.os.AsyncTask;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class BaseServiceAsyncTask<T> extends AsyncTask<Void,Void,Object> {

    private NetworkResponseListener<T> listener;

    public BaseServiceAsyncTask(NetworkResponseListener<T> listener){
        this.listener = listener;
    }

    @Override
    protected Object doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if(o != null && o instanceof HashnestClientException){
                listener.onError((HashnestClientException) o);
        }else {
            listener.onResponse((T) o);
        }
    }
}
