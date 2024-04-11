package com.example.tluschedule.ui.lichthi;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.data.models.tluModels.lichThi.LichThi;

import java.util.ArrayList;
import java.util.List;

public class LichThiAdapter extends RecyclerView.Adapter<LichThiAdapter.LichThiViewHolder> {
    private List<LichThi> lichThiList;

    public LichThiAdapter() {
        lichThiList = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setLichThiList(List<LichThi> lichThiList) {
        this.lichThiList = lichThiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichThiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lict_thi_item, parent, false);
        return new LichThiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichThiViewHolder holder, int position) {
        LichThi lichThi = lichThiList.get(position);
        holder.bind(lichThi);
    }

    @Override
    public int getItemCount() {
        return lichThiList.size();
    }

    public static class LichThiViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvMonThi, tvNgayThi, tvStart, tvEnd, tvRoom, tvSBD, tvGD;

        public LichThiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonThi = itemView.findViewById(R.id.tvMonThi);
            tvNgayThi = itemView.findViewById(R.id.tvNgayThi);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvEnd = itemView.findViewById(R.id.tvEnd);
            tvRoom = itemView.findViewById(R.id.tvRoom);
            tvSBD = itemView.findViewById(R.id.tvSBD);
            tvGD = itemView.findViewById(R.id.tvGD);
        }

        public void bind(LichThi lichThi) {
            tvGD.setText(lichThi.getExamPeriodCode());
            tvMonThi.setText(lichThi.getSubjectName());
            tvNgayThi.setText(lichThi.getExamRoom().getExamDateString());
            tvStart.setText(lichThi.getExamRoom().getExamHour().getStartString());
            tvEnd.setText(lichThi.getExamRoom().getExamHour().getEndString());
            tvRoom.setText(lichThi.getExamRoom().getRoom().getCode());
            tvSBD.setText(lichThi.getExamCode());
        }

    }
}
