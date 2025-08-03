package com.example.benefits_tracker;

public class TaskItem {
    private String text;
    private int iconResId;
    private boolean isChecked;

    public TaskItem(String text, int iconResId, boolean isChecked) {
        this.text = text;
        this.iconResId = iconResId;
        this.isChecked = isChecked;
    }

    public String getText() {
        return text;
    }

    public int getIconResId() {
        return iconResId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
