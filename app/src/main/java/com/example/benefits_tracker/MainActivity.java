package com.example.benefits_tracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout topRightMenu;

    private final int[] menuIds = {R.id.menu_logout, R.id.menu_more};
    private final int menuLogoutIcon = R.drawable.ic_exit;
    private final String[] menuTitles = {"Вийти", "Інше"};

    public void initViews() {
        topRightMenu = findViewById(R.id.topRightMenu);
        topRightMenu.setOnClickListener(this::showPopupMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void showPopupMenu(View anchor) {
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAnchorView(anchor);
        listPopupWindow.setWidth(600);

        listPopupWindow.setAdapter(new android.widget.BaseAdapter() {
            @Override
            public int getCount() {
                return menuTitles.length;
            }

            @Override
            public Object getItem(int position) {
                return menuTitles[position];
            }

            @Override
            public long getItemId(int position) {
                return menuIds[position];
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(MainActivity.this)
                            .inflate(R.layout.menu_item_with_icon, parent, false);
                }

                TextView title = convertView.findViewById(R.id.title);
                ImageView icon = convertView.findViewById(R.id.icon);

                title.setText(menuTitles[position]);

                if (menuIds[position] == R.id.menu_logout) {
                    icon.setVisibility(View.VISIBLE);
                    icon.setImageResource(menuLogoutIcon);
                    icon.clearColorFilter();
                } else {
                    // Залишаємо місце під іконку, але приховуємо її
                    icon.setVisibility(View.INVISIBLE);
                }

                return convertView;
            }
        });

        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            int itemId = menuIds[position];
            if (itemId == R.id.menu_logout) {
                Toast.makeText(MainActivity.this, "Вихід", Toast.LENGTH_SHORT).show();
                finish();
            } else if (itemId == R.id.menu_more) {
                Toast.makeText(MainActivity.this, "Інше", Toast.LENGTH_SHORT).show();
            }
            listPopupWindow.dismiss();
        });

        listPopupWindow.show();
    }
}
