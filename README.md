# HashnestAndroidAPI
**HashnestAndroidAPI is an Android client for Hashnest API:**
https://www.hashnest.com/hashnest_api

**Usage**

First of all, you have to obtain API credentials from Hashnest's settings page.
Do not forget to check the different services availables you need:
Currency, Hash Rate and Trading.

If you generate your crendentials without one of them checked, the corresponding API won't be available for your user
and will throw an Authentication error.

Once obtained, you can init the client library:

```
  HashnestClient.init(this, {Username}, {APIkey}, {secret});

```

In order to use the different APIs, the library has different service layers.

**Account Info**

Query Account Info

```
HashnestClient.getInstance().getAccountInfoServiceHandler().getAccountInfo(this, new NetworkResponseListener<AccountInfoModel>() {
            @Override
            public void onResponse(AccountInfoModel response) {
                //do stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

**Currency account API**

Check users account balance

```
HashnestClient.getInstance().getCurrencyServiceHandler().getCurrency(this, new NetworkResponseListener<ArrayList<CurrencyModel>>() {
            @Override
            public void onResponse(ArrayList<CurrencyModel> response) {
                //do stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

**Hash rate account API**

Check user's hash rate account balance

```
HashnestClient.getInstance().getHashRateServiceHandler().getHashRate(this, new NetworkResponseListener<ArrayList<CurrencyModel>>() {
            @Override
            public void onResponse(ArrayList<CurrencyModel> response) {
                //do stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

**Trading order API**

Check user's active entrust order

```
HashnestClient.getInstance().getTradingOrderServiceHandler().getActiveEntrustOrder(this, marketId, new NetworkResponseListener<ArrayList<EntrustTradingOrderModel>>() {
            @Override
            public void onResponse(ArrayList<EntrustTradingOrderModel> response) {
                //do stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

Check user's trading order

```
HashnestClient.getInstance().getTradingOrderServiceHandler().getTradingOrder(this, orderId, page, resultsPerPage, new NetworkResponseListener<ArrayList<EntrustTradingOrderModel>>() {
            @Override
            public void onResponse(ArrayList<EntrustTradingOrderModel> response) {
                //do stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

You can skip page and resultsPerPage parameters in the call (default first page and default 20 records per page).

Create an entrust order

```
HashnestClient.getInstance().getTradingOrderServiceHandler().createEntrustOrder(this, amount, pricePerUnit, TradingOrderType.PURCHASE, new NetworkResponseListener<ArrayList<EntrustTradingOrderModel>>() {
            @Override
            public void onResponse(ArrayList<EntrustTradingOrderModel> response) {
                //recent order created
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

Cancel an entrust order

```
 HashnestClient.getInstance().getTradingOrderServiceHandler().cancelEntrusOrder(this, orderId, new NetworkResponseListener<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                //true or false
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

Cancel all entrust orders

```
HashnestClient.getInstance().getTradingOrderServiceHandler().cancelAllEntrustOrders(this, marketId, new NetworkResponseListener<Boolean>() {
            @Override
            public void onResponse(Boolean response) {

            }

            @Override
            public void onError(HashnestClientException error) {

            }
        });
```

**Open market API**

Obtain all opened markets

```
HashnestClient.getInstance().getOpenMarketServiceHandler().getOpenedMarkets(this, new NetworkResponseListener<ArrayList<OpenMarketModel>>() {
            @Override
            public void onResponse(ArrayList<OpenMarketModel> response) {
                //do stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```

Obtain the trading order list on pointed market

```
HashnestClient.getInstance().getOpenMarketServiceHandler().getTradingHistpry(this, marketId, TradingOrderType.SALE, new NetworkResponseListener<ArrayList<TradingOrderModel>>() {
            @Override
            public void onResponse(ArrayList<TradingOrderModel> response) {
                //doo stuff
            }

            @Override
            public void onError(HashnestClientException error) {
                //check error
            }
        });
```
