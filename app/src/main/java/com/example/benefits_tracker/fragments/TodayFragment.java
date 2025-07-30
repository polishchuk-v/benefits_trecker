package com.example.benefits_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.benefits_tracker.R;


public class TodayFragment extends Fragment {
    public TodayFragment(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Підключення XML-розмітки фрагмента
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // Тут ініціалізуються елементи UI (кнопки, списки і т.д.)
        // Наприклад:
        // TextView textView = view.findViewById(R.id.text_today);
    }
}
