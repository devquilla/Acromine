package com.luismolina.acromine;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends Application {

    public static Context MainContext;
    private static Gson sGsonObject = new Gson();

    @Override
    public void onCreate() {
        super.onCreate();
        MainContext = this;
    }
    public static Gson getGsonObject() {
        if (sGsonObject == null) {
            sGsonObject = new GsonBuilder().disableHtmlEscaping().create();
        }
        return sGsonObject;
    }
}
