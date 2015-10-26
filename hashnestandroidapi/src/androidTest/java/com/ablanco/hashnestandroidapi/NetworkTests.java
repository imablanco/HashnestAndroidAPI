package com.ablanco.hashnestandroidapi;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Álvaro Blanco Cabrero on 16/10/15
 * HashnestAndroidAPI-master
 */
public class NetworkTests extends AndroidTestCase{

    public static final String API_KEY = "SNbGfgNteKBxyiglbVPhDxxc8SDAFCLrUBtlvRHl";
    public static final String SECRET = "x3Xh9tzawtf6ur860173rzPymzxqdCODm45WREzq";
    public static final String USERNAME = "AlvJes";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        NetworkManager.init(USERNAME, API_KEY, SECRET);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCurrency() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = NetworkManager.getInstance().get(NetworkConstants.CURRENCY_PATH,null);
                    assert (response != null);
                    latch.countDown();
                } catch (HashnestClientException e) {
                    e.printStackTrace();
                    latch.countDown();
                }
            }
        }).start();
        latch.await();

    }
}
