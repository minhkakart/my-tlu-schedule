package com.example.tluschedule.supporter.sorter;

import com.example.tluschedule.ui.main.CourseEg;

public class CourseItemSorter {

    public static int sortCourseItems(CourseEg courseEg1, CourseEg courseEg2) {
        if (courseEg1.getDay().getDate() < courseEg2.getDay().getDate()) {
            return -1;
        }
        if (courseEg1.getDay().getDate() == courseEg2.getDay().getDate()) {
            if (courseEg1.getStartTime().getStart() < courseEg2.getStartTime().getStart()) {
                return -1;
            }
        }
        return 1;
    }
}
