package mobapde.edu.labnotificationactions;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    static int count = 0; // replace this with values from your DB
    final static int MA_PENDINGINTENT = 0;
    final static int SA_PENDINGINTENT = 0;
    final static int NOTIF_ID = 0;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        PendingIntent pendingIntent
                = PendingIntent.getActivity(context,
                    MA_PENDINGINTENT,
                    new Intent(context, MainActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent saPendingIntent
                = PendingIntent.getActivity(context,
                    SA_PENDINGINTENT,
                    new Intent(context, SecondActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder
                = new NotificationCompat.Builder(context)
                .setTicker("Ticker text")
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setNumber(count)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Go to next step", saPendingIntent);

        ((NotificationManager)context.getSystemService(Service.NOTIFICATION_SERVICE))
                .notify(NOTIF_ID, builder.build());
    }
}
