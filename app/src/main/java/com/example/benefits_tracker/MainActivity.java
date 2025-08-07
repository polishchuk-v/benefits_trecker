package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private LinearLayout topRightMenu;

    private final String[] menuTitles = {"Вийти", "Інше"};
    private final int[] menuIds = {R.id.menu_exit, R.id.menu_other};
    private final int menuLogoutIcon = R.drawable.ic_exit;

    public void initViews() {
        //Ініціалязація кнопок
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

        listPopupWindow.setAdapter(new BaseAdapter() {
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

                TextView title = convertView.findViewById(R.id.text_menu);
                ImageView icon = convertView.findViewById(R.id.ic_exit);

                title.setText(menuTitles[position]);

                if (menuIds[position] == R.id.menu_exit) {
                    icon.setVisibility(View.VISIBLE);
                    icon.setImageResource(menuLogoutIcon);
                    icon.clearColorFilter();
                } else {
                    icon.setVisibility(View.INVISIBLE);
                }

                return convertView;
            }
        });

        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            int itemId = menuIds[position];
            if (itemId == R.id.menu_exit) {

                // Вихід з Firebase
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            } else if (itemId == R.id.menu_other) {
                Toast.makeText(MainActivity.this, "Інше", Toast.LENGTH_SHORT).show();
            }
            listPopupWindow.dismiss();
        });

        listPopupWindow.show();
    }
}