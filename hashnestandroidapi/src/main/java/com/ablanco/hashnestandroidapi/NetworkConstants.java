package com.ablanco.hashnestandroidapi;

/**
 * Created by Álvaro Blanco Cabrero on 3/10/15
 * HashnestAndroidAPI
 */
class NetworkConstants {

    /**
     * HASHNEST PATHS
     */
    public final static String HASHNEST_BASE_URL      = "https://www.hashnest.com/api/v1/";
    public final static String CURRENCY_PATH          = "currency_accounts";
    public final static String ACCOUNT_PATH           = "account";
    public final static String HASH_ACCOUNT_PATH      = "hash_accounts";
    public final static String ACTIVE_ORDERS_PATH     = "orders/active";
    public final static String TRADING_ORDER_PATH     = "orders/history";
    public final static String ORDERS_PATH            = "orders";
    public final static String CANCEL_ORDER_PATH      = "orders/revoke";
    public final static String CANCEL_ALL_ORDER_PATH  = "orders/quick_revoke";
    public final static String OPEN_MARKETS_PATH      = "currency_markets";
    public final static String MARKET_HISTORY_PATH    = "currency_markets/order_history";


    /**
     * Param keys
     */
    public final static String PARAM_ACCESS_KEY        = "?access_key=";
    public final static String PARAM_NONCE             = "&nonce=";
    public final static String PARAM_SIGNATURE         = "&signature=";
    public final static String PARAM_MARKET_ID         = "&currency_market_id=";
    public final static String PARAM_AMOUNT            = "&amount=";
    public final static String PARAM_PPC               = "&ppc=";
    public final static String PARAM_CATEGORY          = "&category=";
    public final static String PARAM_ORDER_ID          = "&order_id=";
    public final static String PARAM_PAGE              = "&page=";
    public final static String PARAM_PAGE_PER_AMOUNT   = "&page_per_amount=";

}
