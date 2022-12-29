package com.example.notesappjava.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesappjava.databinding.ActivityNewWordBinding;

public class NewWordActivity extends AppCompatActivity {

    private ActivityNewWordBinding mBinding;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityNewWordBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        EditText wordEditText = mBinding.notesEditTextView;


        mBinding.saveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditText.getText().toString();
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(word)){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}