package com.example.benefits_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.benefits_tracker.R;

public class AddBenefitsActivity extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_habit, container, false);
    }
}
