package com.dauba.todolist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> texts;
    private ArrayList<Integer> images;  // You can add default image if needed
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ListView and input fields
        ListView listView = findViewById(R.id.listview);
        EditText inputText = findViewById(R.id.input_text);
        Button addButton = findViewById(R.id.add_button);

        // Sample data (can add a default image for new items if required)
        images = new ArrayList<>();
        texts = new ArrayList<>();

        images.add(R.drawable.tomato);
        texts.add("Kamatis");

        images.add(R.drawable.saluyot);
        texts.add("Saluyot");

        // Initialize adapter
        adapter = new CustomAdapter(this, images, texts);
        listView.setAdapter(adapter);

        // Set listener for add button
        addButton.setOnClickListener(v -> {
            String newItem = inputText.getText().toString().trim();
            // Add a default image or allow user to choose
            if (!newItem.isEmpty()) {
                texts.add(newItem);
                images.add(R.drawable.eggplant);
                images.add(R.drawable.malunggay);
                images.add(R.drawable.mais);
                images.add(R.drawable.okra);
                images.add(R.drawable.onion);
                images.add(R.drawable.repolyo);
                images.add(R.drawable.mais);
                images.add(R.drawable.tangkong);
                images.add(R.drawable.tanglad);
                images.add(R.drawable.upo);
                images.add(R.drawable.potato);
                images.add(R.drawable.baguiobeans);
                images.add(R.drawable.carrot);
                images.add(R.drawable.ampalaya);
                images.add(R.drawable.garlic);
                images.add(R.drawable.ginger);
                images.add(R.drawable.squash);

                adapter.notifyDataSetChanged(); // Update ListView
                inputText.setText(""); // Clear the input field
                Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
            }
        });
    }
}