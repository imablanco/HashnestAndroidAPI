package com.ablanco.hashnestandroidapi;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import java.io.File;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Álvaro Blanco Cabrero on 3/10/15
 * HashnestAndroidAPI
 */
class NetworkManager {


    // Default maximum disk usage in bytes
    private static final int DEFAULT_DISK_USAGE_BYTES = 25 * 1024 * 1024;

    // Default cache folder name
    private static final String DEFAULT_CACHE_DIR = "volleyCache";

    /**
     * Request timeout in seconds
     */
    private static final int REQUEST_TIMEOUT = 15;

    /**
     * Singleton instance
     */
    private static NetworkManager mInstance = null;
    /**
     * Network request queue
     */
    private RequestQueue mRequestQueue = null;

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


    public static void init(Context context,String username, String apiKey, String secret){
        mInstance = new NetworkManager(context,username,apiKey,secret);

    }

    public static NetworkManager getInstance(Context context) {
        if (mInstance == null && context != null) {
            mInstance = new NetworkManager(context,null,null,null);
        }

        return mInstance;
    }

    private NetworkManager(Context context,String username, String apiKey, String secret) {
        // set up default queue

        this.mUsername = username;
        this.mAPIKey = apiKey;
        this.mSecret = secret;

        if(context == null || username == null || apiKey == null || secret == null){
            throw new InvalidParameterException("Error dealing with parameters, did you miss something or maybe forgot to call init?");
        }

        // define cache folder
        File rootCache = context.getExternalCacheDir();
        if (rootCache == null) {
            // Can't find External Cache Dir, switching to application specific cache directory
            rootCache = context.getCacheDir();
        }

        File cacheDir = new File(rootCache, DEFAULT_CACHE_DIR);
        cacheDir.mkdirs();

        HttpStack stack = new HurlStack();
        Network defaultNetwork = new BasicNetwork(stack);
        DiskBasedCache diskBasedCache = new DiskBasedCache(cacheDir, DEFAULT_DISK_USAGE_BYTES);
        mRequestQueue = new RequestQueue(diskBasedCache, defaultNetwork);
        mRequestQueue.start();

    }

    public String get(final String path,Map<String,String> params) throws HashnestClientException{
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

        RequestFuture<String> future = RequestFuture.newFuture();
        StringRequest request = new StringRequest(Request.Method.POST,NetworkConstants.HASHNEST_BASE_URL + path + encodedParams, future, future);
        request.setShouldCache(false);
        mRequestQueue.add(request);
        try {
            return future.get(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new HashnestClientException(HashnestClientException.SERVER_ERROR, HashnestClientException.MSG_INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            if (e.getCause() != null && e.getCause() instanceof TimeoutError) {
                throw new HashnestClientException(HashnestClientException.SERVER_ERROR, HashnestClientException.MSG_SERVER_TIMEOUT);
            } else if (e.getCause() != null
                    && e.getCause() instanceof VolleyError
                    && null != ((VolleyError) e.getCause()).networkResponse) {
                throw new HashnestClientException((VolleyError) e.getCause());
            } else {
                throw new HashnestClientException(HashnestClientException.SERVER_ERROR, HashnestClientException.MSG_INTERNAL_SERVER_ERROR);
            }
        }catch (TimeoutException e){
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
