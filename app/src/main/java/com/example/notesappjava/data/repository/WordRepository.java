package com.example.notesappjava.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesappjava.data.entity.Word;
import com.example.notesappjava.data.Dao.WordDao;
import com.example.notesappjava.data.WordRoomDatabase;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase roomDatabase = WordRoomDatabase.getDatabase(application);
        mWordDao = roomDatabase.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutors.execute(() -> {
            mWordDao.insert(word);
        });
    }

}
