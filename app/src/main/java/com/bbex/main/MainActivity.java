package com.bbex.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.bbex.R;
import com.bbex.base.BaseActivity;
import com.bbex.base.BaseFragment;
import com.bbex.widget.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private BottomNavigationViewEx mBottomNavigationView;
    private MainViewPagerAdapter<BaseFragment> mAdapter;
    private List<BaseFragment> mSubMainFragment = new ArrayList<>();

    private static final int COUNT = 2;
    public static int sTab = 0;
    private int[] mNormalIcon = new int[]{
            R.drawable.main_tab_icon_exchange_normal, R.drawable.main_tab_icon_me_normal};
    private int[] mPressedIcon = new int[]{
            R.drawable.main_tab_icon_exchange_press, R.drawable.main_tab_icon_me_press};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.main_container_viewpager);
        mSubMainFragment.add(WebFragment.newInstance());
        mSubMainFragment.add(MainMeFragment.newInstance());
        mAdapter = new MainViewPagerAdapter<>(getSupportFragmentManager(), mSubMainFragment);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setEnabled(false);
        mBottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.navigation);
        mBottomNavigationView.setupWithViewPager(mViewPager);
        mBottomNavigationView.enableAnimation(false);
        mBottomNavigationView.enableItemShiftingMode(false);
        mBottomNavigationView.enableShiftingMode(false);
        mBottomNavigationView.setItemIconTintList(null);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int current = mBottomNavigationView.getCurrentItem();
                for (int i = 0; i < COUNT; i++) {
                    BottomNavigationItemView itemView = mBottomNavigationView.getBottomNavigationItemView(i);
                    if (current == i) {
                        itemView.setIcon(ActivityCompat.getDrawable(MainActivity.this, mPressedIcon[i]));
                    } else {
                        itemView.setIcon(ActivityCompat.getDrawable(MainActivity.this, mNormalIcon[i]));
                    }
                }

                int id = item.getItemId();
                if(id == R.id.navigation_exchange){
                    sTab = 0;
                }else if(id == R.id.navigation_me){
                    sTab = 1;
                }
                mViewPager.setCurrentItem(sTab);
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
