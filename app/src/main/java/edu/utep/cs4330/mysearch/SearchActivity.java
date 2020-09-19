package edu.utep.cs4330.mysearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private TextView textView;

    public enum SearchType {
        ALL(""),
        IMAGE("isch"),
        BOOK("bks"),
        NEWS("nws"),
        VIDEO("vid");

        public final String tbm;

        SearchType(String tbm) {
            this.tbm = tbm;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textView = findViewById(R.id.searchTextView);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // get intent and intent data
        Intent intent = getIntent();
        String text =  intent.getStringExtra("text");
        SearchType type = (SearchType) intent.getSerializableExtra("type");

        String formattedHtml = String.format("<b>Request</b><br/>Text: %s<br/>Type: %s", text, type.toString());

        if (Build.VERSION.SDK_INT >= 24) {
            textView.setText(Html.fromHtml(formattedHtml, Build.VERSION.SDK_INT));
        } else {
            textView.setText(Html.fromHtml(formattedHtml));
        }

        registerClickListener(R.id.proceedButton, text, type);
    }

    private void registerClickListener(int rid, String text, SearchType type) {
        Button button = findViewById(rid);
        button.setOnClickListener(v -> {
            clicked(text, type);
        });
    }

    // Initialize another intent and start the activity to search Google based on data parameters
    private void clicked(String text, SearchType type) {
        String uri = String.format("https://www.google.com/search?tbm=%s&q=%s", type.tbm, text);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}