package com.example.benefits_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskItem> {
    public TaskAdapter(Context context, List<TaskItem> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskItem taskItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView taskIcon = convertView.findViewById(R.id.taskIcon);
        TextView taskText = convertView.findViewById(R.id.taskText);
        CheckBox taskCheckBox = convertView.findViewById(R.id.taskCheckBox);

        taskIcon.setImageResource(taskItem.getIconResId());
        taskText.setText(taskItem.getText());
        taskCheckBox.setChecked(taskItem.isChecked());

        taskCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            taskItem.setChecked(isChecked);
        });

        return convertView;
    }
}
