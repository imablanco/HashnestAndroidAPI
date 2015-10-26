package com.ablanco.hashnestandroidapi;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Álvaro Blanco Cabrero on 16/10/15
 * HashnestAndroidAPI-master
 */
class NetworkManager {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * Hashnest username
     */
    private String mUsername;

    /**
     * Hashnest API Key
     */
    private String mAPIKey;

    /**
     * Hashnest secret key
     */
    private String mSecret;

    /**
     * Client to execeute requests
     */
    private OkHttpClient mClient;

    private static NetworkManager mInstance;

    public static NetworkManager getInstance(){
        if(mInstance == null){
            mInstance = new NetworkManager(null,null,null);
        }

        return mInstance;
    }

    public static void init(String username, String apiKey, String secret){
        mInstance = new NetworkManager(username,apiKey,secret);

    }

    private NetworkManager(String username, String apiKey, String secret){

        if(username == null || apiKey == null || secret == null){
            throw new InvalidParameterException("Error dealing with parameters, did you miss something or maybe forgot to call init?");
        }

        this.mUsername = username;
        this.mAPIKey = apiKey;
        this.mSecret = secret;

        this.mClient = new OkHttpClient();
        this.mClient.setConnectTimeout(10, TimeUnit.SECONDS);
        this.mClient.setRetryOnConnectionFailure(true);
    }

    public String get(String path,Map<String,String> params) throws HashnestClientException {

        StringBuilder rawParams = new StringBuilder();
        rawParams.append(buildAuthParams());

        if(params != null ){
            for (String key : params.keySet()){
                rawParams.append(key);
                rawParams.append(params.get(key));
            }
        }

        String encodedParams = rawParams.toString();
        try {
            encodedParams = encodedParams.replace(" ", "%20");
        }catch(Exception e){
            e.printStackTrace();
        }

        String finalUrl = NetworkConstants.HASHNEST_BASE_URL + path + encodedParams;
        try{
            RequestBody body = RequestBody.create(JSON, "");
            Request request = new Request.Builder()
                    .url(finalUrl)
                    .post(body)
                    .build();
            Response response = mClient.newCall(request).execute();
            if(response.isSuccessful()){
                return response.body().string();
            }else {
                throw new HashnestClientException(response.code(),null);
            }
        }catch (IOException e){
            throw new HashnestClientException(HashnestClientException.SERVER_ERROR, HashnestClientException.MSG_INTERNAL_SERVER_ERROR);
        }
    }

    private String getSignature(String nonce) {
        String signature = "";
        try {

            String message = nonce + mUsername + mAPIKey;

            final Charset asciiCs = Charset.forName("US-ASCII");
            final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(asciiCs.encode(mSecret).array(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            final byte[] mac_data = sha256_HMAC.doFinal(asciiCs.encode(message).array());
            for (final byte element : mac_data)
            {
                signature += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
            }

            return signature;

        }
        catch (Exception e){
        }

        return signature;
    }

    private String buildAuthParams(){
        String nonce = String.valueOf(System.currentTimeMillis());
        return NetworkConstants.PARAM_ACCESS_KEY + mAPIKey + NetworkConstants.PARAM_NONCE + nonce + NetworkConstants.PARAM_SIGNATURE + getSignature(nonce);

    }

    /**
     * Method to validate params
     *
     * @param params String params to validate
     * @return 0 if all ok. If not the number of the first wrong param
     */
    public static int validateParams(String[] params) {

        for (int i = 0; i < params.length; i++) {
            if (params[i] == null || params[i].isEmpty()) return i + 1;
        }
        return 0;
    }
}
