package com.example.tluschedule.ui.main.tluFunctionFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.observerData.ObserverData;

import java.util.List;

public class FunctionButtonAdapter extends RecyclerView.Adapter<FunctionButtonAdapter.ViewHolder> {
    ObserverData<List<FunctionButtonModel>> listFunctionButtonModelObserver;

    public FunctionButtonAdapter() {
        this.listFunctionButtonModelObserver = new ObserverData<>();
        updateData(ConstantValues.getListFunctionButtonModel());
    }

    @NonNull
    @Override
    public FunctionButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tlu_function_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionButtonAdapter.ViewHolder holder, int position) {
        FunctionButtonModel functionButtonModel = listFunctionButtonModelObserver.getData().get(position);
        holder.bind(functionButtonModel);
    }

    @Override
    public int getItemCount() {
        if (listFunctionButtonModelObserver.getData() == null) return 0;
        return listFunctionButtonModelObserver.getData().size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<FunctionButtonModel> functionButtonModels) {
        listFunctionButtonModelObserver.update(functionButtonModels);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnFunction);
        }

        public void bind(FunctionButtonModel functionButtonModel) {
            button.setText(functionButtonModel.getContent());
            button.setOnClickListener(v -> {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, functionButtonModel.getTargetActivity());
                context.startActivity(intent);
            });
        }
    }
}
