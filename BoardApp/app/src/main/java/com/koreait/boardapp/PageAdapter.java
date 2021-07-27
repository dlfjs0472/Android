package com.koreait.boardapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.koreait.boardapp.pages.ContentFragment;
import com.koreait.boardapp.pages.ListFragment;
import com.koreait.boardapp.pages.WriteFragment;

public class PageAdapter extends FragmentStateAdapter {
    Fragment[] pages= new Fragment[3];

    public PageAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        pages[0] = new ListFragment();
        pages[1] = new WriteFragment();
        pages[2] = new ContentFragment();
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