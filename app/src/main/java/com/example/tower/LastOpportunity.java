package com.example.tower;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LastOpportunity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent lo = new Intent(context.getApplicationContext(), RequestForNotification.class);
        lo.putExtra("TITLE", "低电量的最后充值机会");
        lo.putExtra("CONTENT_TEXT", "余额所剩无多，珍惜充值机会");
        context.getApplicationContext().startService(lo);
    }
}
