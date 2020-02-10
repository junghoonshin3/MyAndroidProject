package com.skcc.wdp.poc.view.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CerificationDeco extends RecyclerView.ItemDecoration {
    private int size10;

    public CerificationDeco(Context context) {
        this.size10 = dpToPx(context, 10);

    }

    private int dpToPx(Context context, int dp) {

        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //각 아이템 포지션
        int position=parent.getChildAdapterPosition(view);
        //전체 아이템 개수
        int itemCount=state.getItemCount();

        outRect.top=size10;
        outRect.bottom=size10;



    }
}
