package com.example.notesappjava.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesappjava.R;
import com.example.notesappjava.data.entity.Word;
import com.example.notesappjava.databinding.ActivityMainBinding;
import com.example.notesappjava.ui.adapter.WordListAdapter;
import com.example.notesappjava.ui.viewmodel.WordViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ActivityMainBinding mBinding;
    private WordViewModel mWordViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.addNotes.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.recyclerView = mBinding.recyclerView;
        this.layoutManager = new LinearLayoutManager(this);

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observeForever(listObserver);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addNotes) {
            addNotes();
        }
    }

    Observer<List<Word>> listObserver = new Observer<List<Word>>() {
        @Override
        public void onChanged(List<Word> words) {
            adapter = new WordListAdapter(words);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);

//            adapter.submitList(words);
        }
    };

    private void addNotes() {
        Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
        }
    }
}