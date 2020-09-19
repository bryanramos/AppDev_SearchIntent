package edu.utep.cs4330.mysearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.searchEditText);
        registerClickListener(R.id.imageButton, SearchActivity.SearchType.IMAGE);
        registerClickListener(R.id.videoButton, SearchActivity.SearchType.VIDEO);
        registerClickListener(R.id.newsButton, SearchActivity.SearchType.NEWS);
        registerClickListener(R.id.allButton, SearchActivity.SearchType.ALL);
    }

    private void registerClickListener(int rid, SearchActivity.SearchType type) {
        Button button = findViewById(rid);
        button.setOnClickListener(v -> {
            clicked(type);
        });
    }

    private void clicked(SearchActivity.SearchType type) {
        String text = edit.getText().toString();

        if (TextUtils.isEmpty(edit.getText())) {
            Toast.makeText(this, "Search text required.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra("text", text);
            intent.putExtra("hello", type);
            startActivity(intent);
        }
    }
}