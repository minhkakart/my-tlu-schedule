package com.example.tluschedule.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;

import java.util.ArrayList;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<CourseDisplayModel> courseDisplayModels;

    public CourseViewAdapter(Context context, ArrayList<CourseDisplayModel> courseDisplayModels) {
        this.context = context;
        this.courseDisplayModels = courseDisplayModels;
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
        CourseDisplayModel courseDisplayModel = courseDisplayModels.get(position);
        holder.name.setText(courseDisplayModel.getName());
        holder.time.setText(courseDisplayModel.getStartTime().getStartString());
        holder.room.setText(courseDisplayModel.getRoom() + "   -   [ Tiết: " + courseDisplayModel.getStartTime().getIndexNumber() + " - " + courseDisplayModel.getEndTime().getIndexNumber() + " ]");
        holder.day.setText(courseDisplayModel.getDayString());
        holder.endTime.setText(courseDisplayModel.getEndTime().getEndString());
    }

    @Override
    public int getItemCount() {
        return courseDisplayModels.size();
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
}
