package com.matsdevelopsolutions.downloadmanagersample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created on 7/13/2016.
 */
public class DownloadClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent activityIntent = new Intent(intent);
        activityIntent.setClass(context, NotificationActivity.class);
        context.startActivity(activityIntent);
    }
}
