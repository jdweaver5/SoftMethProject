package drinkSmart;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import drinkSmart.R;

import static android.content.Context.ALARM_SERVICE;

public class navigation_activity extends AppCompatActivity implements View.OnClickListener{
        
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_activity);

        //set onclick listenrr
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //EditText editText = findViewById(R.id.editText);
        //TimePicker timePicker = findViewById(R.id.timePicker);

        //set notification id and text
        Intent intent = new Intent(navigation_activity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", intent);
        
        intent.putExtra("todo","test");
        //getBroadcast
        PendingIntent alarmIntent = PendingIntent.getBroadcast(navigation_activity.this,
                0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        switch (view.getId()){
            case R.id.setBtn:

                //get current time int hour and int minute need to be added to current time.

                long currTime = Calendar.getInstance().getTimeInMillis();

                existingUser user = new existingUser();

              int hours = (int) Math.round(user.getLimitHours());
              int minutes = (int) Math.round(user.getLimitMinutes());




                //create time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hours);
                startTime.set(Calendar.MINUTE, minutes);
                startTime.set(Calendar.SECOND, 0 );
                long alarmStartTime = startTime.getTimeInMillis();

                //set alarm
                alarm.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Canceled",Toast.LENGTH_SHORT).show();
                break;


        }





    }
}

