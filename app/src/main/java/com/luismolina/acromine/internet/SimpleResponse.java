package com.luismolina.acromine.internet;

public interface SimpleResponse {
    void onResponse(String response);
    void onError(String error);
}
