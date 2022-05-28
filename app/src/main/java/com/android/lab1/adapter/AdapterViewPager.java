package com.android.lab1.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.lab1.fragment.InfoFragment;
import com.android.lab1.fragment.ListFragment;
import com.android.lab1.fragment.SearchFragment;

public class AdapterViewPager extends FragmentPagerAdapter {
    private int numPage;

    public AdapterViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListFragment();
            case 1:
                return new InfoFragment();
            case 2:
                return new SearchFragment();
        }

        return null;
    }


    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "DANH SACH";
            case 1:
                return "THONG TIN";
            case 2:
                return "TIM KIEM VA THONG KE";
        }

        return null;
    }
}
