package com.example.tluschedule.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tluschedule.R;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;

import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    private final List<Course> coursesData;

    public SectionsPagerAdapter(Context context, FragmentManager fm, List<Course> coursesData) {
        super(fm);
        this.coursesData = coursesData;
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment.
        Log.e("section", "getItem: " + position + " data: " + getCoursesData());
        return PlaceholderFragment.newInstance(position + 1, getCoursesData());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    public List<Course> getCoursesData() {
        return coursesData;
    }
}