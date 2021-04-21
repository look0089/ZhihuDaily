package org.jzs.mybaseapp.section.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.jzs.mybaseapp.section.tucao.TucaoFragment;
import org.jzs.mybaseapp.section.zhihu.ZhihuFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"知乎日报", "直播视频"};
    private ArrayList<Fragment> mFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new ZhihuFragment());
        mFragments.add(new TucaoFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}