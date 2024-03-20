package com.example.tluschedule.ui.main.scheduleFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.data.observerData.ObserverData;

import java.util.List;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

    private final Context context;
    ObserverData<List<CourseDisplayModel>> courseDisplayModelObserver;

    public CourseViewAdapter(Context context) {
        this.context = context;
        courseDisplayModelObserver = new ObserverData<>();
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
        assert courseDisplayModelObserver.getData() != null;
        CourseDisplayModel courseDisplayModel = courseDisplayModelObserver.getData().get(position);
        holder.name.setText(courseDisplayModel.getName());
        holder.time.setText(courseDisplayModel.getStartTime().getStartString());
        holder.room.setText(courseDisplayModel.getRoom() + "   -   [ Tiết: " + courseDisplayModel.getStartTime().getIndexNumber() + " - " + courseDisplayModel.getEndTime().getIndexNumber() + " ]");
        holder.day.setText(courseDisplayModel.getDayString());
        holder.endTime.setText(courseDisplayModel.getEndTime().getEndString());
    }

    @Override
    public int getItemCount() {
        if (courseDisplayModelObserver.getData() == null) return 0;
        return courseDisplayModelObserver.getData().size();
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

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<CourseDisplayModel> newCourseDisplayModel) {
        courseDisplayModelObserver.update(newCourseDisplayModel);
        notifyDataSetChanged();
    }

}
