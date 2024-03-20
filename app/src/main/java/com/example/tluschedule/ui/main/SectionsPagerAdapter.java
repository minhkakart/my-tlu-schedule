package com.example.tluschedule.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tluschedule.ui.main.scheduleFragment.ScheduleFragment;
import com.example.tluschedule.ui.main.tluFunctionFragment.TluFunctionFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0 || position == 1) {
            return ScheduleFragment.newInstance(position);
        } else {
            return TluFunctionFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}