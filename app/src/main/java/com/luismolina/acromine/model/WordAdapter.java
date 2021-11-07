package com.luismolina.acromine.model;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.luismolina.acromine.view.WordViewHolder;

import java.util.ArrayList;

public class WordAdapter extends ListAdapter<Dictionary.Word, WordViewHolder> {

    public static class DictionaryDiff extends DiffUtil.ItemCallback<Dictionary.Word> {
        @Override
        public boolean areItemsTheSame(@NonNull Dictionary.Word oldItem, @NonNull Dictionary.Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dictionary.Word oldItem, @NonNull Dictionary.Word newItem) {
            return oldItem.lf.equals(newItem.lf);
        }
    }

    public WordAdapter(@NonNull DiffUtil.ItemCallback<Dictionary.Word> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Dictionary.Word current = getItem(position);
        holder.bind(current, position);
    }


    public void initList(ArrayList<Dictionary.Word> words){
        submitList(words);
    }
}
