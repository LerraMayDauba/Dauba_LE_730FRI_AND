package com.dauba.menu;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fragmentContainer;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainer), (v, insets) -> {
            WindowInsetsCompat systemBars = insets;
            return insets;
        });

        // Initialize fragment container
        fragmentContainer = findViewById(R.id.fragmentContainer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inflate custom toolbar
        getLayoutInflater().inflate(R.layout.custom_action_bar, toolbar, true);

        ImageView button = toolbar.findViewById(R.id.moreOptions);
        button.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, button);
            popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
            popup.setForceShowIcon(true);
            popup.setOnMenuItemClickListener(item -> handleMenuItemClick(item));
            popup.show();
        });
    }

    // Handle menu item click logic
    private boolean handleMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.gotofragment) {
            // Load the fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new AboutFragment())
                    .commit();
            return true;
        } else if (itemId == R.id.openDialog) {
            // Show custom dialog
            CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getSupportFragmentManager(), null);
            return true;
        } else if (itemId == R.id.Exit) {
            // Quit the app
            finishAffinity();
            return true;
        } else {
            return false;
        }
    }


    // Inflate the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
}
