package com.nimapinfotech.machinetask.singleton;

import android.app.Application;

import com.nimapinfotech.machinetask.connectivity.ConnectivityReceiver;

public class Singleton extends Application {

    private static Singleton mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized Singleton getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
