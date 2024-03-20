package com.example.tluschedule.supporter.sorter;

import com.example.tluschedule.ui.main.scheduleFragment.CourseDisplayModel;

public class CourseItemSorter {

    public static int sortCourseItems(CourseDisplayModel courseDisplayModel1, CourseDisplayModel courseDisplayModel2) {
        if (courseDisplayModel1.getDay().before(courseDisplayModel2.getDay())) {
            return -1;
        } else if (courseDisplayModel1.getDay().equals(courseDisplayModel2.getDay())) {
            if (courseDisplayModel1.getStartTime().getStart() < courseDisplayModel2.getStartTime().getStart()) {
                return -1;
            }
        }
        return 1;
    }
}
