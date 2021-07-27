package com.koreait.boardapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.koreait.boardapp.pages.ContentFragment;
import com.koreait.boardapp.pages.ListFragment;
import com.koreait.boardapp.pages.WriteFragment;

public class PageAdapter extends FragmentStateAdapter {
    MainActivity mainActivity;
    Fragment[] pages;
    public PageAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.mainActivity=(MainActivity) fragmentActivity;
        this.pages=this.mainActivity.pages;

    }

    @Override
    public int getItemCount() {
        return pages.length;
    }

    @Override
    public Fragment createFragment(int position) {
        return pages[position];
    }

}