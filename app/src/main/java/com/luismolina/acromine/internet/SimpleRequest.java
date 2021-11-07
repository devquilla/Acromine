package com.luismolina.acromine.internet;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class SimpleRequest {

    public static final String TAG = SimpleRequest.class.getSimpleName();
    private SimpleResponse simpleResponse;

    public SimpleRequest(SimpleResponse simpleResponse){
        this.simpleResponse = simpleResponse;
    }

    public void newGetRequest(final String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    Log.e(this.TAG, "Response: " + response);
                    try{
                        simpleResponse.onResponse(response);
                        return;
                    }catch (Exception e){
                        e.printStackTrace();
                        simpleResponse.onError(e.getMessage());
                    }
                },
                error -> {
                    error.printStackTrace();
                    simpleResponse.onError(error.toString());
                })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

        };

        HttpRequestManager.getInstance().getHttpLiveQueue().add(stringRequest);

    }
}
