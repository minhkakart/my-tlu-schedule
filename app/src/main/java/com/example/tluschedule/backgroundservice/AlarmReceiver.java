package com.example.tluschedule.backgroundservice;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.tluschedule.MainActivity;
import com.example.tluschedule.R;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.TLUs.studentCourse.CourseSubject;
import com.example.tluschedule.data.model.TLUs.studentCourse.TimeTable;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.supporter.converter.CalendarConverter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {
    String fileName = "courses.txt";

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Course> coursesData = FileActions.readListFromJsonFile(context, fileName, Course.class);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        DateFormat simpleDateFormat = DateFormat.getDateTimeInstance();

        if (coursesData == null || coursesData.isEmpty()) {
            return;
        }
        for (Course course : coursesData) {
            CourseSubject courseSubject = course.getCourseSubject();
            List<TimeTable> timetables = courseSubject.getTimetables();
            for (TimeTable timetable : timetables) {
                Date startDate = new Date(timetable.getStartDate());
                Date endDate = new Date(timetable.getEndDate());
                Date startHour = new Date(timetable.getStartHour().getStart());
                if (now.after(startDate) && now.before(endDate) && calendar.get(Calendar.DAY_OF_WEEK) == timetable.getWeekIndex() && CalendarConverter.getHour(now) == CalendarConverter.getHour(startHour) - 1) {

                    // Create an explicit intent for an Activity in your app.
                    Intent intentMain = new Intent(Intent.ACTION_VIEW, null, context.getApplicationContext(), MainActivity.class);
                    intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intentMain, PendingIntent.FLAG_IMMUTABLE);

                    NotificationCompat.Builder testNotify = new NotificationCompat.Builder(context.getApplicationContext(), MainActivity.CHANNEL_ID)
                            .setSmallIcon(R.drawable.eismall)
                            .setContentTitle("You have course today")
                            .setContentText(courseSubject.getDisplayName())
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());
                    if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    notificationManager.notify(1, testNotify.build());
                }
            }
        }
    }
}
