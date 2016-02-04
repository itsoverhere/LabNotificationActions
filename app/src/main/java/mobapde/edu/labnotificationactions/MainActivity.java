package mobapde.edu.labnotificationactions;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etNumber;
    Button buttonNotify;
    final static int BC_PENDINGINTENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = (EditText) findViewById(R.id.et_number);
        buttonNotify = (Button) findViewById(R.id.button_notify);

        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seconds = Integer.parseInt(etNumber.getText().toString());

                Intent broadcastIntent = new Intent(getBaseContext(), AlarmReceiver.class);
                PendingIntent pendingIntent
                        = PendingIntent.getBroadcast(getBaseContext(),
                            BC_PENDINGINTENT,
                            broadcastIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                ((AlarmManager)getSystemService(Service.ALARM_SERVICE))
                        .set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                SystemClock.elapsedRealtime() + (seconds *1000),
                                pendingIntent);
            }
        });
    }
}
