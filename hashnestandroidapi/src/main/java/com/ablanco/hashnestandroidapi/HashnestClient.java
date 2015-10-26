package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco on 3/10/15
 */
public class HashnestClient {

    /**
     * Singleton instance
     */
    private static HashnestClient mInstance;

    private CurrencyServiceHandler currencyServiceHandler;
    private AccountInfoServiceHandler accountInfoServiceHandler;
    private HashRateServiceHandler hashRateServiceHandler;
    private TradingOrderServiceHandler tradingOrderServiceHandler;
    private OpenMarketServiceHandler openMarketServiceHandler;

    public static HashnestClient getInstance(){
        if(mInstance == null){
            mInstance = new HashnestClient();
        }

        return mInstance;
    }

    public static void init(String userName,String apiKey,String secret){
        NetworkManager.init(userName,apiKey,secret);
        mInstance = new HashnestClient();
        mInstance.currencyServiceHandler     = new CurrencyServiceHandler();
        mInstance.accountInfoServiceHandler  = new AccountInfoServiceHandler();
        mInstance.hashRateServiceHandler     = new HashRateServiceHandler();
        mInstance.tradingOrderServiceHandler = new TradingOrderServiceHandler();
        mInstance.openMarketServiceHandler   = new OpenMarketServiceHandler();
    }

    /**
     * Private constructor
     */
    private HashnestClient(){
    }

    public CurrencyServiceHandler getCurrencyServiceHandler() {
        return currencyServiceHandler;
    }

    public AccountInfoServiceHandler getAccountInfoServiceHandler() {
        return accountInfoServiceHandler;
    }

    public HashRateServiceHandler getHashRateServiceHandler() {
        return hashRateServiceHandler;
    }

    public TradingOrderServiceHandler getTradingOrderServiceHandler() {
        return tradingOrderServiceHandler;
    }

    public OpenMarketServiceHandler getOpenMarketServiceHandler() {
        return openMarketServiceHandler;
    }
}
