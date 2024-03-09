package com.example.tluschedule.service;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.tluschedule.R;
import com.example.tluschedule.config.StaticValues;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.TLUs.studentCourse.CourseSubject;
import com.example.tluschedule.data.model.TLUs.studentCourse.TimeTable;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.supporter.converter.CalendarConverter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {
    private final Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Course> coursesData = FileActions.readListFromJsonFile(context, StaticValues.COURSES_FILE_NAME, Course.class);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

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
                Calendar calendarStartHour = Calendar.getInstance();
                calendarStartHour.set(Calendar.HOUR_OF_DAY, CalendarConverter.getHour(startHour));
                calendarStartHour.set(Calendar.MINUTE, CalendarConverter.getMinute(startHour));
                boolean isTimeToNotify = now.before(new Date(calendarStartHour.getTimeInMillis() - 20 * 60 * 1000)) && now.after(new Date(calendarStartHour.getTimeInMillis() - 25 * 60 * 1000 - 3 * 1000));
                if (now.after(startDate) && now.before(endDate) && calendar.get(Calendar.DAY_OF_WEEK) == timetable.getWeekIndex() && isTimeToNotify) {

                    // Create an explicit intent for an Activity in your app.
                    Intent intentMain = new Intent(Intent.ACTION_MAIN);
                    intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intentMain, PendingIntent.FLAG_IMMUTABLE);

                    NotificationCompat.Builder testNotify = new NotificationCompat.Builder(context.getApplicationContext(), StaticValues.CHANNEL_ID)
                            .setSmallIcon(R.drawable.eismall)
                            .setContentTitle("You have course today")
                            .setContentText(courseSubject.getDisplayName())
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setSound(uri)
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
                    notificationManager.notify(courseSubject.getId(), testNotify.build());
                }
            }
        }
    }
}
