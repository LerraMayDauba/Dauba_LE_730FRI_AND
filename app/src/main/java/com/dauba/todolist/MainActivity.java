package com.dauba.todolist;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);

        // Sample data
        int[] images = {
                R.drawable.tomato,
                R.drawable.saluyot,
                R.drawable.malunggay,
                R.drawable.eggplant,
                R.drawable.mais,
                R.drawable.okra,
                R.drawable.tangkong,
                R.drawable.tanglad,
                // Add more images
        };

        String[] texts = {
                "kamatis",
                "Saluyot",
                "Malunggay",
                "Talong",
                "Mais",
                "Okra",
                "Tangkong",
                "Tanglad",
                // Add more text
        };

        CustomAdapter adapter = new CustomAdapter(this, images, texts);
        listView.setAdapter(adapter);
    }
}
