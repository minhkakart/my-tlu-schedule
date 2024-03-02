package com.example.tluschedule.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;

import java.util.ArrayList;
import java.util.List;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

    private final Context context;
    CourseDisplayModelObserver courseDisplayModelObserver;

    public CourseViewAdapter(Context context) {
        this.context = context;
        courseDisplayModelObserver = new CourseDisplayModelObserver();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View courseView = inflater.inflate(R.layout.course_item, parent, false);
        return new ViewHolder(courseView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        assert courseDisplayModelObserver.getListCourseDisplayModel() != null;
        CourseDisplayModel courseDisplayModel = courseDisplayModelObserver.getListCourseDisplayModel().get(position);
        holder.name.setText(courseDisplayModel.getName());
        holder.time.setText(courseDisplayModel.getStartTime().getStartString());
        holder.room.setText(courseDisplayModel.getRoom() + "   -   [ Tiết: " + courseDisplayModel.getStartTime().getIndexNumber() + " - " + courseDisplayModel.getEndTime().getIndexNumber() + " ]");
        holder.day.setText(courseDisplayModel.getDayString());
        holder.endTime.setText(courseDisplayModel.getEndTime().getEndString());
    }

    @Override
    public int getItemCount() {
        if (courseDisplayModelObserver.getListCourseDisplayModel() == null) return 0;
        return courseDisplayModelObserver.getListCourseDisplayModel().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView time;
        private final TextView room;
        private final TextView day;
        private final TextView endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_name);
            time = itemView.findViewById(R.id.course_time);
            room = itemView.findViewById(R.id.course_room);
            day = itemView.findViewById(R.id.course_day);
            endTime = itemView.findViewById(R.id.course_end_time);
        }
    }

    private class CourseDisplayModelObserver implements Observer<List<CourseDisplayModel>> {
        private List<CourseDisplayModel> courseDisplayModel;

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onChanged(List<CourseDisplayModel> newCourseDisplayModel) {
            courseDisplayModel = newCourseDisplayModel;
            notifyDataSetChanged();
        }

        public void update(List<CourseDisplayModel> newCourseDisplayModel) {
            onChanged(newCourseDisplayModel);
        }

        public List<CourseDisplayModel> getListCourseDisplayModel() {
            return courseDisplayModel;
        }
    }

    public void updateCourseDisplayModel(List<CourseDisplayModel> newCourseDisplayModel) {
        courseDisplayModelObserver.update(newCourseDisplayModel);
    }

}
