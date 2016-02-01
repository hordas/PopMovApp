package com.dyomin.udatraining.popmovapp.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Authenticator bind service.
 * Created by Admin on 10.01.2016.
 */
public class PopMovAppAuthenticatorService extends Service {

    private PopMovAppAuthenticator popMovAppAuthenticator;

    @Override
    public void onCreate() {
        popMovAppAuthenticator = new PopMovAppAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return popMovAppAuthenticator.getIBinder();
    }
}
