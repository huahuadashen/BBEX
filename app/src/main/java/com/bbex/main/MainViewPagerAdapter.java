package com.bbex.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author: kutear.guo
 * @create: 2017/5/18 15:25
 * <p>
 * 主页ViewPager适配器
 */

public class MainViewPagerAdapter<T extends Fragment> extends FragmentStatePagerAdapter {
    private List<T> mSubMainFragment;

    public MainViewPagerAdapter(FragmentManager fm, List<T> subMainFragments) {
        super(fm);
        this.mSubMainFragment = subMainFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mSubMainFragment.get(position);
    }

    @Override
    public int getCount() {
        return mSubMainFragment.size();
    }
}
