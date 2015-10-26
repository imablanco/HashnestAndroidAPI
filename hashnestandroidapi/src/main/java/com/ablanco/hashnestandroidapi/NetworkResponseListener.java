package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 3/10/15
 * HashnestAndroid
 */
public interface NetworkResponseListener<T> {

    void onResponse(T response);
    void onError(HashnestClientException error);
}

