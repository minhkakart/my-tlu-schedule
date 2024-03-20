package com.example.tluschedule.ui.main.tluFunctionFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TluFunctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TluFunctionFragment extends Fragment {

    public TluFunctionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HelperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TluFunctionFragment newInstance() {
        return new TluFunctionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tlu_function, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rvFunctionButton);
        FunctionButtonAdapter functionButtonAdapter = new FunctionButtonAdapter();
        recyclerView.setAdapter(functionButtonAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}