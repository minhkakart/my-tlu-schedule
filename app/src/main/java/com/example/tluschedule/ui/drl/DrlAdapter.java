package com.example.tluschedule.ui.drl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.data.models.tluModels.drl.DrlDisplay;

import java.util.ArrayList;
import java.util.List;

public class DrlAdapter extends RecyclerView.Adapter<DrlAdapter.ViewHolder> {
    private List<DrlDisplay> drlDisplays;

    public DrlAdapter() {
        this.drlDisplays = new ArrayList<>();
    }

    public void update(List<DrlDisplay> drlDisplays){
        this.drlDisplays = drlDisplays;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drl_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(drlDisplays.get(position));
    }

    @Override
    public int getItemCount() {
        return drlDisplays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvYear, tvSemester, tvDrl, tvSort;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvSemester = itemView.findViewById(R.id.tvSemester);
            tvDrl = itemView.findViewById(R.id.tvDrl);
            tvSort = itemView.findViewById(R.id.tvSort);
        }

        public void bind(DrlDisplay drlDisplay){
            tvYear.setText(drlDisplay.getYear());
            tvSemester.setText(drlDisplay.getSemester());
            tvDrl.setText(drlDisplay.getDrl());
            tvSort.setText(drlDisplay.getSort());
        }

    }
}
