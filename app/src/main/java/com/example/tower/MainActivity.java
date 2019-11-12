package com.example.tower;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();

        // 侧边栏菜单项的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu1:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu2:
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });


//        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
//        nav_header = headerView.findViewById(R.id.nav_header);
//
//        nav_header.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
//                startActivity(intent);
//            }
//        });

        // 监听底部导航栏变化
//        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
//        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        // 显示第一页
//                        frameLayout1.setVisibility(View.VISIBLE);
//                        frameLayout2.setVisibility(View.GONE);
//                        frameLayout3.setVisibility(View.GONE);
//                        return true;
//                    case R.id.navigation_dashboard:
//                        // 显示第二页
//                        frameLayout2.setVisibility(View.VISIBLE);
//                        frameLayout1.setVisibility(View.GONE);
//                        frameLayout3.setVisibility(View.GONE);
//                        return true;
//                    case R.id.navigation_notifications:
//                        // 显示第三页
//                        frameLayout1.setVisibility(View.GONE);
//                        frameLayout2.setVisibility(View.GONE);
//                        frameLayout3.setVisibility(View.VISIBLE);
//                        return true;
//                }
//                return false;
//            }
//        };
//    }
    }

    private void initActivity() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // 使用Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}

