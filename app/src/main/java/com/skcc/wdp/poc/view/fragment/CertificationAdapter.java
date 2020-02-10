package com.skcc.wdp.poc.view.fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.skcc.wdp.poc.R;

import java.util.List;

public class CertificationAdapter extends RecyclerView.Adapter<CertificationAdapter.CertificationViewHolder> {
    List<Person> personList;


    public CertificationAdapter(List<Person> personList) {
        this.personList = personList;

    }

    @NonNull
    @Override
    public CertificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_input_rrn_select_item, parent, false);

        return new CertificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificationViewHolder holder, int position) {
        holder.certification_name.setText(personList.get(position).getName());
        holder.expiry_date.setText(personList.get(position).getExpiry_date());
        holder.bank.setText(personList.get(position).getBank());
        holder.certification_img.setImageResource(personList.get(position).getPerson_img());


    }


    @Override
    public int getItemCount() {
        return personList.size();
    }

    class CertificationViewHolder extends RecyclerView.ViewHolder {
        TextView certification_name;
        TextView expiry_date;
        TextView bank;
        ImageView certification_img;
        MaterialCardView cardView;
        boolean state = false;

        public CertificationViewHolder(@NonNull View itemView) {
            super(itemView);
            certification_name = itemView.findViewById(R.id.certification_name);
            expiry_date = itemView.findViewById(R.id.certification_expirydate);
            bank = itemView.findViewById(R.id.certification_sign);
            certification_img = itemView.findViewById(R.id.certification_img);
            cardView = itemView.findViewById(R.id.item_cardView);

            cardView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_UP:
                            if (state == false) {
                                //터치가 눌렸을 경우 선택표시 설정
                                cardView.setBackgroundResource(R.drawable.ic_rrn_certification_select);
                                state = true;
                            } else {
                                //선택된 후 다시 재선택 할 경우 선택표시 해제
                                cardView.setBackgroundResource(R.drawable.ic_rrn_certification);
                                state = false;
                            }
                            break;

                    }
                    return true;
                }

            });
        }
    }

}

