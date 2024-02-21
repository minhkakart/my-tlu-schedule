package com.example.tluschedule;

import android.content.Intent;
import android.os.Bundle;

import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tluschedule.ui.main.SectionsPagerAdapter;
import com.example.tluschedule.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());

        String fileName = "courses.txt";
        List<Course> coursesData = FileActions.readListFromJsonFile(this, fileName, Course.class);
//        Toast.makeText(this, coursesData, Toast.LENGTH_SHORT).show();
//        Log.e("MAIN", "onCreate has result" + getIntent().hasExtra("result"));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), coursesData);
        ViewPager viewPager = mainActivityBinding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = mainActivityBinding.tabs;
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = mainActivityBinding.fab;
        fab.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}