package com.practice.olegtojgildin.recyclerview_meet_7;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyItemTouchHelper myItemTouchHelper;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    private Messenger mService;
    final Messenger mMessenger = new Messenger(new IncomingHandler());
    List<Worker> workers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        startService(MyIntentService.newIntent(MainActivity.this));
    }

    public void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        workers = WorkerGenerator.generateWorkers(0);
        mAdapter = new CustomAdapter(workers);
        mRecyclerView.setAdapter(mAdapter);
        myItemTouchHelper = new MyItemTouchHelper((MyItemTouchHelper.ItemTouchHelperAdapter) mAdapter);
        itemTouchHelper = new ItemTouchHelper(myItemTouchHelper);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBindService();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null, MyIntentService.MESSAGE_REGISTER_CLIENT);
            msg.replyTo = mMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    public void bindService() {
        bindService(MyIntentService.newIntent(MainActivity.this), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindService() {
        Message msg = Message.obtain(null, MyIntentService.MESSAGE_UNREGISTER_CLIENT);
        msg.replyTo = mMessenger;
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(mServiceConnection);
    }

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyIntentService.MESSAGE_STOPPING:
                    Toast.makeText(MainActivity.this, "Service stopped", Toast.LENGTH_LONG).show();
                    unBindService();
                    break;
                case MyIntentService.MESSAGE_INFO:
                    workers.add((Worker) msg.obj);
                    mAdapter = new CustomAdapter(workers);
                    mRecyclerView.setAdapter(mAdapter);
                    break;
            }
        }
    }
}
