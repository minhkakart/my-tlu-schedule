package com.example.tluschedule.ui.main.scheduleFragment;

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
import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.models.tluModels.semester.SemesterContent;
import com.example.tluschedule.data.models.tluModels.studentCourse.Course;
import com.example.tluschedule.data.models.tluModels.studentCourse.CourseSubject;
import com.example.tluschedule.data.models.tluModels.studentCourse.TimeTable;
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
public class ScheduleFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentMainBinding tabFragmentBinding;
    private CourseViewAdapter courseViewAdapter;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private SemesterContent currentSemesterContent;

    public static ScheduleFragment newInstance(int index) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tabFragmentBinding = FragmentMainBinding.inflate(inflater, container, false);
        View root = tabFragmentBinding.getRoot();
        recyclerView = tabFragmentBinding.recyclerView;
        courseViewAdapter = new CourseViewAdapter(getContext());
        spinner = tabFragmentBinding.spinner;

        // Set up the recycler view
        assert getArguments() != null;
        recyclerView.setAdapter(courseViewAdapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabFragmentBinding = null;
    }

    @Override
    public void onResume() {
        // Get the current semester
        currentSemesterContent = FileActions.readSingleObjectFromFile(requireContext(), ConstantValues.CURRENT_SEMESTER_FILE_NAME, SemesterContent.class);

        // Get the index of the current tab
        assert getArguments() != null;
        int index = getArguments().getInt(ARG_SECTION_NUMBER);

        // Update the course display model based on the tab index
        if (index == 0) {
            courseViewAdapter.updateData(getCourseToday());
            recyclerView.startLayoutAnimation();
        } else {
            if (currentSemesterContent != null) {
                // Get the total weeks in the semester
                int totalWeeks = CalendarCalculator.calculateWeeksBetweenTwoDates(new Date(currentSemesterContent.getSchoolYear().getStartDate()), new Date(currentSemesterContent.getSchoolYear().getEndDate()));
                // Create a list of week names
                List<String> weekNames = new ArrayList<>();
                // Create a calendar and a date format
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", getResources().getConfiguration().getLocales().get(0));
                // Add the week names to the list
                for (int i = 1; i <= totalWeeks; i++) {
                    calendar.setTime(new Date(currentSemesterContent.getSchoolYear().getStartDate() + (long) (i - 1) * 7 * 24 * 60 * 60 * 1000));
                    Date lastWeekSunday = CalendarCalculator.findLastWeekSunday(calendar.getTime());
                    Date currentWeekMonday = CalendarCalculator.increaseDate(lastWeekSunday, 1);
                    Date currentWeekSunday = CalendarCalculator.findCurrentWeekSunday(calendar.getTime());
                    weekNames.add("Tuần " + i + " (" + simpleDateFormat.format(currentWeekMonday) + " - " + simpleDateFormat.format(currentWeekSunday) + ")");
                }

                // Set up the spinner to display the weeks
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, weekNames);
                arrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);

                // Set up the spinner to update the course display model when the user selects a week
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    // Update the course display model when the user selects a week
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        courseViewAdapter.updateData(getCourseInWeek(position, currentSemesterContent.getSchoolYear().getStartDate()));

                        recyclerView.startLayoutAnimation();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Do nothing
                    }
                });

                // Set the spinner to the current week if the user has not selected a week
                boolean isSetPrevIndex = getArguments().getBoolean("isSetPrevIndex");
                if (isSetPrevIndex) {
                    spinner.setSelection(getArguments().getInt("prevIndex"));
                } else {
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

        }

        // Hide the spinner if the current tab is not the second tab
        if (index == 0) {
            spinner.setVisibility(View.GONE);
        } else {
            if (currentSemesterContent != null) {
                spinner.setVisibility(View.VISIBLE);
            } else {
                spinner.setVisibility(View.GONE);
            }
        }

        // Set the text view to display "No courses available" if there are no courses
        TextView textView = tabFragmentBinding.sectionLabel;
        if (currentSemesterContent == null) {
            textView.setText(R.string.no_courses_available);
        } else {
            textView.setText("");
        }
        if (index == 0 && getCourseToday().isEmpty()) {
            textView.setText(R.string.no_courses_available);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        assert getArguments() != null;
        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1 && currentSemesterContent != null) {
            int prevIndex = spinner.getSelectedItemPosition();
            getArguments().putBoolean("isSetPrevIndex", true);
            getArguments().putInt("prevIndex", prevIndex);
            spinner.setOnItemSelectedListener(null);
            spinner.setAdapter(null);
        }
        courseViewAdapter.updateData(new ArrayList<>());
    }

    private List<Course> getCoursesData() {
        Context context = getContext();
        assert context != null;
        return FileActions.readListFromJsonFile(context, ConstantValues.COURSES_FILE_NAME, Course.class);
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