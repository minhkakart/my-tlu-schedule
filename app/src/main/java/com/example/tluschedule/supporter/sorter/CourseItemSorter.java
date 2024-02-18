package com.example.tluschedule.supporter.sorter;

import com.example.tluschedule.ui.main.CourseEg;

public class CourseItemSorter {

    public static int sortCourseItems(CourseEg courseEg1, CourseEg courseEg2) {
        if (courseEg1.getDay().before(courseEg2.getDay())) {
            return -1;
        } else if (courseEg1.getDay().equals(courseEg2.getDay())) {
            if (courseEg1.getStartTime().getStart() < courseEg2.getStartTime().getStart()) {
                return -1;
            }
        }
        return 1;
    }
}
