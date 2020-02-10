package com.skcc.wdp.poc.view.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skcc.wdp.poc.R;

import java.util.ArrayList;
import java.util.List;

public class RnnLoginPagerAdapter extends RecyclerView.Adapter<RnnLoginPagerAdapter.PagerViewHolder> {
    Context context;
    List<Integer> list;
    List<Person> personList;
    public static final int VIEW_TYPE_A = 0;
    public static final int VIEW_TYPE_B = 1;
    public static final int VIEW_TYPE_C = 2;

    public RnnLoginPagerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_A) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_input_rrn_login, parent, false);
        } else if (viewType == VIEW_TYPE_B) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_input_rrn_select, parent, false);
        } else if (viewType == VIEW_TYPE_C) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_input_rrn_pw, parent, false);
        }
        return new PagerViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {

        if (holder.viewType == VIEW_TYPE_A) {
            //포커스가 주어질 시 배경화면 변경
            holder.rrn_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus == true) {
                        v.findViewById(R.id.et_rrn_input).setBackgroundResource(R.drawable.ic_rrn_input_select);
                    }
                }
            });
            //주민번호 입력시 처리할 코드 작성


        } else if (holder.viewType == VIEW_TYPE_B) {
            RecyclerView recyclerView = holder.recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            personList = new ArrayList<Person>();
            personList.add(0, new Person("윤동근(Yun Dong Geun)", "yessign", "2021-01-02", R.drawable.ic_img_security));
            personList.add(1, new Person("홍길동(Hong Gil Dong)", "yessign", "2021-03-12", R.drawable.ic_img_security));
            personList.add(2, new Person("홍길동(Hong Gil Dong)", "yessign", "2021-03-12", R.drawable.ic_img_security));
            personList.add(3, new Person("홍길동(Hong Gil Dong)", "yessign", "2021-03-12", R.drawable.ic_img_security));
            recyclerView.setAdapter(new CertificationAdapter(personList));
            recyclerView.addItemDecoration(new CerificationDeco(context));
        }


    }

    @Override
    public int getItemViewType(int position) {
        int result = 0;
        if (list.get(position) == R.layout.fragment_input_rrn_login) {
            result = VIEW_TYPE_A;
        } else if (list.get(position) == R.layout.fragment_input_rrn_select) {
            result = VIEW_TYPE_B;
        } else if (list.get(position) == R.layout.fragment_input_rrn_pw) {
            result = VIEW_TYPE_C;
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PagerViewHolder extends RecyclerView.ViewHolder {
        AppCompatEditText rrn_input;
        RecyclerView recyclerView;
        AppCompatEditText cert_input;
        int viewType;

        public PagerViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType == VIEW_TYPE_A) {
                rrn_input = itemView.findViewById(R.id.et_rrn_input);
            } else if (viewType == VIEW_TYPE_B) {
                recyclerView = itemView.findViewById(R.id.recyclerView);
            } else if (viewType == VIEW_TYPE_C) {
                cert_input = itemView.findViewById(R.id.et_rrn_pw);
            }

        }
    }


}

