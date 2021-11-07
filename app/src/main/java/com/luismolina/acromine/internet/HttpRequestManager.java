package com.luismolina.acromine.internet;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.luismolina.acromine.App;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpRequestManager {

    public static final String TAG = HttpRequestManager.class.getSimpleName();

    private RequestQueue mHttpLiveQueue;
    private static HttpRequestManager sSingleton;

    private AtomicInteger mHttpQueueCount = new AtomicInteger(0);
    private AtomicInteger mHttpFilesQueueCount = new AtomicInteger(0);

    public HttpRequestManager(){
    }

    public static synchronized HttpRequestManager getInstance(){
        if(sSingleton == null){
            sSingleton = new HttpRequestManager();
        }
        return sSingleton;
    }

    public RequestQueue getHttpLiveQueue() {
        if (mHttpLiveQueue == null) {
            mHttpLiveQueue = Volley.newRequestQueue(App.MainContext);
        }
        return mHttpLiveQueue;
    }
}
