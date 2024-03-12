package com.example.tluschedule.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tluschedule.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelperFragment extends Fragment {

    public HelperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HelperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelperFragment newInstance() {
        return new HelperFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_helper, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}