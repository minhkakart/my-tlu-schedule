package com.example.tluschedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
        List<Course> coursesData = FileActions.readJsonFile(this, fileName);
//        Toast.makeText(this, coursesData, Toast.LENGTH_SHORT).show();
        Log.e("MAIN", "onCreate has result" + getIntent().hasExtra("result"));

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

    @Override
    protected void onStart() {
        Log.e("MAIN", "onStart has result" + getIntent().hasExtra("result"));
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("MAIN", "onResume has result" + getIntent().hasExtra("result"));
        super.onResume();

    }

    @Override
    protected void onPause() {
        Log.e("MAIN", "onPause has result" + getIntent().hasExtra("result"));
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("MAIN", "onStop has result" + getIntent().hasExtra("result"));
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.e("MAIN", "onRestart has result" + getIntent().hasExtra("result"));
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.e("MAIN", "onDestroy has result" + getIntent().hasExtra("result"));
        super.onDestroy();
    }

    @Override
    public void finish() {
        Log.e("MAIN", "finish has result" + getIntent().hasExtra("result"));
        super.finish();
    }
}