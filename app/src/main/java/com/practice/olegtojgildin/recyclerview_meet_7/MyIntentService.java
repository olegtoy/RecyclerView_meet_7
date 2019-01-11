package com.practice.olegtojgildin.recyclerview_meet_7;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyIntentService extends IntentService {

    public static final int MESSAGE_REGISTER_CLIENT = 0;
    public static final int MESSAGE_UNREGISTER_CLIENT = 1;
    public static final int MESSAGE_STOPPING = 2;
    public static final int MESSAGE_INFO = 3;
    public static final int MESSAGE_SET_VALUE = 4;

    private List<Messenger> mClient=new ArrayList<Messenger>();
    private Messenger mMessenger = new Messenger(new IncomingHandler());

    public MyIntentService() {
        super("MyService");
    }

    public static final Intent newIntent(Context context){
        return new Intent(context,MyIntentService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {
            for (int i = 0; i < 4; i++) {
                sendToClients(Message.obtain(null, MESSAGE_INFO, WorkerGenerator.generateWorker()));
                TimeUnit.SECONDS.sleep(1);
            }
            sendToClients(Message.obtain(null, MESSAGE_STOPPING));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void sendToClients(Message message) {
        for (Messenger messenger : mClient) {
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_REGISTER_CLIENT:
                    mClient.add(msg.replyTo);
                    break;
                case MESSAGE_UNREGISTER_CLIENT:
                    mClient.remove(msg.replyTo);
                    break;
                case MESSAGE_SET_VALUE:
                    break;
            }
        }
    }
}