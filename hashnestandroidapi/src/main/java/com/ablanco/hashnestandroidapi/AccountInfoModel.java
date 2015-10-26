package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class AccountInfoModel {

    protected String id;
    protected String email;
    protected String tempAccessToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTempAccessToken() {
        return tempAccessToken;
    }

    public void setTempAccessToken(String tempAccessToken) {
        this.tempAccessToken = tempAccessToken;
    }
}
