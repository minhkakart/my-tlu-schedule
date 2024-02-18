package com.example.tluschedule.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.data.model.TLUs.JsonModelBase;
import com.example.tluschedule.data.model.TLUs.semester.SemesterContent;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.supporter.caculator.CalendarCalculator;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.TLUs.studentCourse.CourseSubject;
import com.example.tluschedule.data.model.TLUs.studentCourse.TimeTable;
import com.example.tluschedule.databinding.FragmentMainBinding;
import com.example.tluschedule.supporter.sorter.CourseItemSorter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding tabFragmentBinding;
    RecyclerView recyclerView;
    CourseViewAdapter courseViewAdapter;
    public static List<Course> coursesData;

    public static PlaceholderFragment newInstance(int index, List<Course> data) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        coursesData = data;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        assert getArguments() != null;
        Log.e("tab", "create tab " + getArguments().getInt(ARG_SECTION_NUMBER));
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        assert getArguments() != null;
        Log.e("tab", "create view tab " + getArguments().getInt(ARG_SECTION_NUMBER));

        tabFragmentBinding = FragmentMainBinding.inflate(inflater, container, false);
        View root = tabFragmentBinding.getRoot();
        recyclerView = tabFragmentBinding.recyclerView;
        assert getArguments() != null;
        ArrayList<CourseEg> courseEgs = createCourseEgs(getArguments().getInt(ARG_SECTION_NUMBER));
        courseViewAdapter = new CourseViewAdapter(getContext(), courseEgs);
        recyclerView.setAdapter(courseViewAdapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        final TextView textView = tabFragmentBinding.sectionLabel;
        pageViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            if (courseEgs.size() == 0) {
                textView.setText(R.string.no_courses_available);
            } else {
                textView.setText("");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        assert getArguments() != null;
        Log.e("tab", "onDestroyView tab " + getArguments().getInt(ARG_SECTION_NUMBER));

        tabFragmentBinding = null;
    }

    private ArrayList<CourseEg> createCourseEgs(int sectionNumber) {
        ArrayList<CourseEg> courseEgs = new ArrayList<>();

        if (coursesData == null) {
            return courseEgs;
        }

        // Get current date
        // Xem trước 1 tuần sau do hiện tại chưa có lịch học
        // Thay thế CalendarCalculator.increaseDateByOneWeek(new Date()); bằng new Date() để lấy hiện tại
        Date now = CalendarCalculator.increaseDate(new Date(), 3);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        // Day
        if (sectionNumber == 1) {
            for (Course course : coursesData) {
                CourseSubject courseSubject = course.getCourseSubject();
                List<TimeTable> timetables = courseSubject.getTimetables();
                for (TimeTable timetable : timetables) {
                    Date startDate = new Date(timetable.getStartDate());
                    Date endDate = new Date(timetable.getEndDate());
                    int dayOfWeek = timetable.getWeekIndex();
                    if (now.after(startDate) && now.before(endDate) && dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)) {
                        courseEgs.add(
                                new CourseEg(course.getSubjectName(),
                                        timetable.getRoomName(),
                                        timetable.getStartHour(),
                                        timetable.getEndHour(),
                                        now
                                )
                        );
                    }
                }

            }
        }
        // Week
        else if (sectionNumber == 2) {
            Date lastWeekSunday = CalendarCalculator.findLastWeekSunday(now);
            Date currentWeekSunday = CalendarCalculator.findCurrentWeekSunday(now);

            for (Date dayInWeek = CalendarCalculator.findNextDay(lastWeekSunday); dayInWeek.before(currentWeekSunday); dayInWeek.setTime(dayInWeek.getTime() + 24 * 60 * 60 * 1000)) {
                calendar.setTime(dayInWeek);

                for (Course course : coursesData) {
                    CourseSubject courseSubject = course.getCourseSubject();
                    List<TimeTable> timetables = courseSubject.getTimetables();
                    for (TimeTable timetable : timetables) {
                        Date startDate = new Date(timetable.getStartDate());
                        Date endDate = new Date(timetable.getEndDate());
                        int dayOfWeek = timetable.getWeekIndex();
                        if (dayInWeek.after(startDate) && dayInWeek.before(endDate) && dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)) {
                            courseEgs.add(
                                    new CourseEg(course.getSubjectName(),
                                            timetable.getRoomName(),
                                            timetable.getStartHour(),
                                            timetable.getEndHour(),
                                            (Date) dayInWeek.clone()
                                    )
                            );
                        }
                    }
                }
            }

        }
        // All
        else if (sectionNumber == 3) {
            SemesterContent currentSemesterContent = FileActions.readSingleObjectFromFile(getContext(), "current_semester.txt", SemesterContent.class);
            if (currentSemesterContent != null) {
                Date startDate = new Date(currentSemesterContent.getStartDate());
                Date endDate = new Date(currentSemesterContent.getEndDate());

                for (; startDate.before(endDate); startDate.setTime(startDate.getTime() + 24 * 60 * 60 * 1000)) {
                    calendar.setTime(startDate);

                    for (Course course : coursesData) {
                        CourseSubject courseSubject = course.getCourseSubject();
                        List<TimeTable> timetables = courseSubject.getTimetables();
                        for (TimeTable timetable : timetables) {
                            Date startDate1 = new Date(timetable.getStartDate());
                            Date endDate1 = new Date(timetable.getEndDate());
                            int dayOfWeek = timetable.getWeekIndex();
                            if (startDate.after(startDate1) && startDate.before(endDate1) && dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)) {
                                courseEgs.add(
                                        new CourseEg(course.getSubjectName(),
                                                timetable.getRoomName(),
                                                timetable.getStartHour(),
                                                timetable.getEndHour(),
                                                (Date) startDate.clone()
                                        )
                                );
                            }
                        }
                    }
                }
            }
        }

        courseEgs.sort(CourseItemSorter::sortCourseItems);
        return courseEgs;
    }

}