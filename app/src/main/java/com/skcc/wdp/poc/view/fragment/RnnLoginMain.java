package com.skcc.wdp.poc.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.skcc.wdp.poc.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RnnLoginMain extends BaseFragment{
    List<Integer> list;
    @Override
    public int getLayoutResID() {
        return R.layout.fragment_input_rrn;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @org.jetbrains.annotations.Nullable ViewGroup container, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        list=new ArrayList<Integer>();
        list.add(0,R.layout.fragment_input_rrn_login);
        list.add(1,R.layout.fragment_input_rrn_select);
        list.add(2,R.layout.fragment_input_rrn_pw);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 pager2=view.findViewById(R.id.rrn_viewpager);
        pager2.setAdapter(new RnnLoginPagerAdapter(getContext(),list));

    }




}
