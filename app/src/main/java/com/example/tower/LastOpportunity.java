package com.example.tower;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LastOpportunity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent lo = new Intent(context.getApplicationContext(), RequestForNotification.class);
        lo.putExtra("TITLE", "低电量");
        lo.putExtra("CONTENT_TEXT", "快要没电关机了，这是最后的充值机会");
        context.getApplicationContext().startService(lo);
    }
}
