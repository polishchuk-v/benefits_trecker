package com.example.benefits_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryItem> {
    public CategoryAdapter(Context context, List<CategoryItem> categoryList) {
        super(context, 0, categoryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item_with_icon, parent, false
            );
        }

        ImageView imageViewIcon = convertView.findViewById(R.id.image_view_icon);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        CategoryItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewIcon.setImageResource(currentItem.getCategoryIcon());
            textViewName.setText(currentItem.getCategoryName());
        }

        return convertView;
    }
}
