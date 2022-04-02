package com.example.eaterydemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.eaterydemo.fragments.PhuongThucThanhToanFM;
import com.example.eaterydemo.fragments.VeChungToiFM;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new PhuongThucThanhToanFM();

            case 1: new VeChungToiFM();

            default: return new PhuongThucThanhToanFM();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
