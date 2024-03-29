package com.luismolina.acromine.custom;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinearSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public LinearSpacesItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.right = spacing;
        outRect.left = spacing;
        outRect.bottom = spacing;

        if (parent.getChildLayoutPosition(view) == 0) {
            //outRect.top = spacing;
        }
    }
}
