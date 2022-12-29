package com.example.notesappjava.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappjava.R;
import com.example.notesappjava.data.entity.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<Word> wordList;

    public WordListAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        WordViewHolder wordViewHolder = new WordViewHolder(view);
        return wordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String word = wordList.get(position).getWord();
        holder.wordTitleTextView.setText(word);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public void submitList(List<Word> words) {
        this.wordList = words;
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordTitleTextView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTitleTextView = itemView.findViewById(R.id.wordTitleTextView);
        }
    }
}
