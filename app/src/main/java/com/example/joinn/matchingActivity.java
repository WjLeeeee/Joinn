package com.example.joinn;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.joinn.chatfragment.ChatFragment;
import com.example.joinn.communityfragment.CommunityFragment;
import com.example.joinn.homefragment.HomeFragment;
import com.example.joinn.mapfragment.MapFragment;
import com.example.joinn.mypagefragment.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class matchingActivity extends AppCompatActivity {
    com.example.joinn.communityfragment.CommunityFragment CommunityFragment;
    com.example.joinn.mapfragment.MapFragment MapFragment ;
    com.example.joinn.homefragment.HomeFragment HomeFragment;
    com.example.joinn.chatfragment.ChatFragment ChatFragment;
    com.example.joinn.mypagefragment.MyPageFragment MyPageFragment;

    BottomNavigationView bottom_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        CommunityFragment = new CommunityFragment();
        MapFragment = new MapFragment();
        HomeFragment = new HomeFragment();
        ChatFragment = new ChatFragment();
        MyPageFragment = new MyPageFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.community:


                        getSupportFragmentManager().beginTransaction().replace(R.id.container,  CommunityFragment).commit();
                        return true;
                    case R.id.map:


                        getSupportFragmentManager().beginTransaction().replace(R.id.container,MapFragment).commit();
                        return true;
                    case R.id.home:


                        getSupportFragmentManager().beginTransaction().replace(R.id.container,HomeFragment).commit();
                        return true;
                    case R.id.chat:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container, ChatFragment).commit();
                        return true;
                    case R.id.Mypage:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container, MyPageFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }
}