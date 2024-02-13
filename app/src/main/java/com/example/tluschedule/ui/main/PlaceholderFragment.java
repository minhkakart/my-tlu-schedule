package com.example.tluschedule.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.TLUs.studentCourse.CourseSubject;
import com.example.tluschedule.data.model.TLUs.studentCourse.TimeTable;
import com.example.tluschedule.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (courseEgs.size() == 0) {
                    textView.setText("No courses available.");
                } else {
                    textView.setText("");
                }
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
        if (sectionNumber == 1) {
////            courseEgs.add(new CourseEg("7:30", "Math", "A101"));
//        } else if(sectionNumber == 2){
//            courseEgs.add(new CourseEg("7:30", "Math", "A101"));
//            courseEgs.add(new CourseEg("9:30", "Physics", "A102"));
//            courseEgs.add(new CourseEg("11:30", "Chemistry", "A103"));
//        } else if(sectionNumber == 3){
//            courseEgs.add(new CourseEg("7:30", "Math", "A101"));
//            courseEgs.add(new CourseEg("9:30", "Physics", "A102"));
//            courseEgs.add(new CourseEg("11:30", "Chemistry", "A103"));
//            courseEgs.add(new CourseEg("13:30", "Biology", "A104"));
//            courseEgs.add(new CourseEg("15:30", "English", "A105"));
//            courseEgs.add(new CourseEg("17:30", "History", "A106"));
//            courseEgs.add(new CourseEg("19:30", "Geography", "A107"));
//        }
            for (int i = 0; i < coursesData.size(); i++) {
                Course course = (Course) coursesData.get(i);
                CourseSubject courseSubject = course.getCourseSubject();
                List<TimeTable> timetables = courseSubject.getTimetables();
                for (TimeTable timetable : timetables) {
                    Date startDate = new Date(timetable.getStartDate());
                    Date endDate = new Date(timetable.getEndDate());
                    Date now = new Date();
                    if (now.after(startDate) && now.before(endDate)) {
                        courseEgs.add(new CourseEg(timetable.getStartHour().getStartString(),
                                course.getSubjectName(),
                                timetable.getRoomName()));
                    }
                }

            }
        } else if (sectionNumber == 2) {
            for (int i = 0; i < coursesData.size(); i++) {
                Course course = (Course) coursesData.get(i);
                CourseSubject courseSubject = course.getCourseSubject();
                List<TimeTable> timetables = courseSubject.getTimetables();
                for (TimeTable timetable : timetables) {
//                    Date startDate = new Date(timetable.getStartDate());
//                    Date endDate = new Date(timetable.getEndDate());
//                    Date now = new Date();
//                    if (now.after(startDate) && now.before(endDate)) {
                    courseEgs.add(new CourseEg(timetable.getStartHour().getStartString(),
                            course.getSubjectName(),
                            timetable.getRoomName()));
//                    }
                }

            }
        }

        return courseEgs;
    }

}