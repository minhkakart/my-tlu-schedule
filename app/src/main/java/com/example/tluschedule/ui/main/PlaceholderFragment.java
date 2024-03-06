package com.example.tluschedule.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.config.StaticValues;
import com.example.tluschedule.data.model.TLUs.semester.SemesterContent;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.TLUs.studentCourse.CourseSubject;
import com.example.tluschedule.data.model.TLUs.studentCourse.TimeTable;
import com.example.tluschedule.databinding.FragmentMainBinding;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.supporter.caculator.CalendarCalculator;
import com.example.tluschedule.supporter.sorter.CourseItemSorter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentMainBinding tabFragmentBinding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tabFragmentBinding = FragmentMainBinding.inflate(inflater, container, false);
        View root = tabFragmentBinding.getRoot();
        RecyclerView recyclerView = tabFragmentBinding.recyclerView;

        assert getArguments() != null;
        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        CourseViewAdapter courseViewAdapter = new CourseViewAdapter(getContext());
        recyclerView.setAdapter(courseViewAdapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        SemesterContent currentSemesterContent = FileActions.readSingleObjectFromFile(requireContext(), StaticValues.CURRENT_SEMESTER_FILE_NAME, SemesterContent.class);
        Spinner spinner = tabFragmentBinding.spinner;

        if (index == 0) {
            courseViewAdapter.updateCourseDisplayModel(getCourseToday());
        } else if (index == 1) {

            if (currentSemesterContent != null) {
                int totalWeeks = CalendarCalculator.calculateWeeksBetweenTwoDates(new Date(currentSemesterContent.getSchoolYear().getStartDate()), new Date(currentSemesterContent.getSchoolYear().getEndDate()));
                List<String> weekNames = new ArrayList<>();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", getResources().getConfiguration().getLocales().get(0));
                for (int i = 1; i <= totalWeeks; i++) {
                    calendar.setTime(new Date(currentSemesterContent.getSchoolYear().getStartDate() + (long) (i - 1) * 7 * 24 * 60 * 60 * 1000));
                    Date lastWeekSunday = CalendarCalculator.findLastWeekSunday(calendar.getTime());
                    Date currentWeekMonday = CalendarCalculator.increaseDate(lastWeekSunday, 1);
                    Date currentWeekSunday = CalendarCalculator.findCurrentWeekSunday(calendar.getTime());
                    weekNames.add("Tuần " + i + " (" + simpleDateFormat.format(currentWeekMonday) + " - " + simpleDateFormat.format(currentWeekSunday) + ")");
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, weekNames);
                arrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        courseViewAdapter.updateCourseDisplayModel(getCourseInWeek(position, currentSemesterContent.getSchoolYear().getStartDate()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Do nothing
                    }
                });

                Date today = new Date();
                int initWeekIndex = 0;
                for (Date startDate = new Date(currentSemesterContent.getSchoolYear().getStartDate()); startDate.before(new Date(currentSemesterContent.getSchoolYear().getEndDate())); startDate.setTime(startDate.getTime() + 7 * 24 * 60 * 60 * 1000)) {
                    Date lastWeekSunday = CalendarCalculator.findLastWeekSunday(startDate);
                    Date currentWeekSunday = CalendarCalculator.findCurrentWeekSunday(startDate);
                    if (today.after(lastWeekSunday) && today.before(currentWeekSunday)) {
                        spinner.setSelection(initWeekIndex);
                        break;
                    } else {
                        initWeekIndex++;
                    }
                }

            }

        }


        if (index == 0 || currentSemesterContent == null) {
            spinner.setVisibility(View.GONE);
        }

        final TextView textView = tabFragmentBinding.sectionLabel;
        if (currentSemesterContent == null) {
            textView.setText(R.string.no_courses_available);
        } else {
            textView.setText("");
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabFragmentBinding = null;
    }

    private List<Course> getCoursesData() {
        Context context = getContext();
        assert context != null;
        return FileActions.readListFromJsonFile(context, StaticValues.COURSES_FILE_NAME, Course.class);
    }

    private List<CourseDisplayModel> getCourseToday() {
        List<Course> coursesData = getCoursesData();

        ArrayList<CourseDisplayModel> courseDisplayModels = new ArrayList<>();

        if (coursesData == null) {
            return courseDisplayModels;
        }

        // Get current date
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        for (Course course : coursesData) {
            CourseSubject courseSubject = course.getCourseSubject();
            List<TimeTable> timetables = courseSubject.getTimetables();
            for (TimeTable timetable : timetables) {
                Date startDate = new Date(timetable.getStartDate());
                Date endDate = new Date(timetable.getEndDate());
                int dayOfWeek = timetable.getWeekIndex();
                if (now.after(startDate) && now.before(endDate) && dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)) {
                    courseDisplayModels.add(
                            new CourseDisplayModel(course.getSubjectName(),
                                    timetable.getRoomName(),
                                    timetable.getStartHour(),
                                    timetable.getEndHour(),
                                    now
                            )
                    );
                }
            }

        }
        courseDisplayModels.sort(CourseItemSorter::sortCourseItems);
        return courseDisplayModels;
    }

    private List<CourseDisplayModel> getCourseInWeek(int weekIndex, long startSemesterDate) {
        List<Course> coursesData = getCoursesData();
        ArrayList<CourseDisplayModel> courseDisplayModels = new ArrayList<>();
        if (coursesData != null) {
            Calendar calendar = Calendar.getInstance();
            Date startWeek = new Date(startSemesterDate + (long) weekIndex * 7 * 24 * 60 * 60 * 1000);
            calendar.setTime(startWeek);

            Date lastWeekSunday = CalendarCalculator.findLastWeekSunday(startWeek);
            Date currentWeekSunday = CalendarCalculator.findCurrentWeekSunday(startWeek);

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
                            courseDisplayModels.add(
                                    new CourseDisplayModel(course.getSubjectName(),
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
        courseDisplayModels.sort(CourseItemSorter::sortCourseItems);
        return courseDisplayModels;
    }

}