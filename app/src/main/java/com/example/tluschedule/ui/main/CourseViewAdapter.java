package com.example.tluschedule.ui.main;

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

    private Context context;
    private ArrayList<CourseEg> courseEgs;

    public CourseViewAdapter(Context context, ArrayList<CourseEg> courseEgs) {
        this.context = context;
        this.courseEgs = courseEgs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View courseView = inflater.inflate(R.layout.course_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(courseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseEg courseEg = courseEgs.get(position);
        holder.name.setText(courseEg.getName());
        holder.time.setText(courseEg.getTime());
        holder.room.setText(courseEg.getRoom());
    }

    @Override
    public int getItemCount() {
        return courseEgs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView time;
        private TextView room;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_name);
            time = itemView.findViewById(R.id.course_time);
            room = itemView.findViewById(R.id.course_room);
        }
    }
}
