package com.luismolina.acromine.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luismolina.acromine.R;
import com.luismolina.acromine.model.Dictionary;

public class WordViewHolder extends RecyclerView.ViewHolder {

    private final TextView mText, mIndex;
    private final Context mContext;

    private WordViewHolder(@NonNull View itemView, @NonNull Context context) {
        super(itemView);

        mText = itemView.findViewById(R.id.mText);
        mIndex = itemView.findViewById(R.id.mIndex);

        mContext = context;
    }

    public void bind(final Dictionary.Word word, final int position) {
        mText.setText(word.lf);
        mIndex.setText(""+(position+1));
    }

    public static WordViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new WordViewHolder(view, parent.getContext());
    }
}
