package com.ablanco.hashnestandroidapi;

import com.android.volley.VolleyError;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
public class HashnestClientException extends Exception{

    // Available exceptions
    public static final int UNKNOWN                 = 0;
    public static final int UNAUTHORIZED            = 1;
    public static final int INTERNET_UNAVAILABLE    = 2;
    public static final int SERVER_ERROR            = 3;
    public static final int INVALID_PARAMS          = 4;
    public static final int SERVER_INVALID_PARAMS   = 5;
    public static final int SERVER_INVALID_RESPONSE = 6;


    public static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String MSG_SERVER_INVALID_PARAMS = "Invalid requested resource";
    public static final String MSG_UNAUTHORIZED = "Unauthorized User";
    public static final String MSG_SERVER_INVALID_RESPONSE = "Invalid response received";
    public static final String MSG_SERVER_TIMEOUT = "Server time out error";

    private int code;
    private String message;

    public HashnestClientException(int code,String msg) {
        super(msg);

        setHashnestClientCode(code);

        if(super.getMessage() == null){
            setHashnestDefaultMessage();
        }
    }

    public HashnestClientException(VolleyError volleyError) {
        super(volleyError.getMessage());

        setHashnestClientCode(volleyError.networkResponse.statusCode);
        if (null == super.getMessage()) {
            setHashnestDefaultMessage();
        }
    }

    private void setHashnestDefaultMessage() {
        switch (code) {
            case HashnestClientException.SERVER_ERROR: this.message = HashnestClientException.MSG_INTERNAL_SERVER_ERROR;
                break;
            case HashnestClientException.UNAUTHORIZED: this.message = HashnestClientException.MSG_UNAUTHORIZED;
                break;
            case HashnestClientException.SERVER_INVALID_PARAMS: this.message = HashnestClientException.MSG_SERVER_INVALID_PARAMS;
                break;
            default: this.message = MSG_SERVER_INVALID_RESPONSE;
                break;
        }
    }

    /**
     * Personalization of error code
     * @param code
     */
    private void setHashnestClientCode(int code) {
        switch (code) {
            case 500:
            case 404: this.code = HashnestClientException.SERVER_ERROR;
                break;
            case 401: this.code = HashnestClientException.UNAUTHORIZED;
                break;
            case 400: this.code = HashnestClientException.SERVER_INVALID_PARAMS;
                break;
            default: this.code = code;
                break;
        }
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}


