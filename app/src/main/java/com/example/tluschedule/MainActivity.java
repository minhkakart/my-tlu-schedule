package com.example.tluschedule;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.tluschedule.backgroundservice.AlarmReceiver;
import com.example.tluschedule.databinding.ActivityMainBinding;
import com.example.tluschedule.ui.login.LoginActivity;
import com.example.tluschedule.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainActivityBinding;
    public static final String CHANNEL_ID = "Course chanel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Main Activity", "onCreate: ");

        super.onCreate(savedInstanceState);
        mainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());
        FloatingActionButton fab = mainActivityBinding.fab;

        createNotificationChannel();

        // Create broadcast receiver to send notification
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        int BROAD_CAST_REQUEST_CODE = 0;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, BROAD_CAST_REQUEST_CODE, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
        long interval = 15 * 60 * 1000;
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), interval, pendingIntent);


        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = mainActivityBinding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = mainActivityBinding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

}