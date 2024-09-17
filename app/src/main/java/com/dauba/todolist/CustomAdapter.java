package com.dauba.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Integer> images;
    private final ArrayList<String> texts;
    private final LayoutInflater inflater;

    // Constructor
    public CustomAdapter(Context context, ArrayList<Integer> images, ArrayList<String> texts) {
        this.context = context;
        this.images = images;
        this.texts = texts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView itemImage = convertView.findViewById(R.id.item_image);
        TextView itemText = convertView.findViewById(R.id.item_text);
        CheckBox itemCheckbox = convertView.findViewById(R.id.item_checkbox); // Not used, but retained for possible future use

        // Set image and text from ArrayList
        itemImage.setImageResource(images.get(position));
        itemText.setText(texts.get(position));

        // Handle double-click event
        convertView.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick(View v) {
                showEditDeleteDialog(position);
            }
        });

        return convertView;
    }

    // Edit or delete dialog
    private void showEditDeleteDialog(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View dialogView = inflater.inflate(R.layout.dialog_edit_delete, null);
        dialogBuilder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.edit_text);
        editText.setText(texts.get(position));

        dialogBuilder.setTitle("Edit or Delete")
                .setPositiveButton("Edit", (dialog, which) -> {
                    // Edit item logic
                    String newText = editText.getText().toString();
                    texts.set(position, newText);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Item updated", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Delete", (dialog, which) -> {
                    // Delete item logic
                    removeItem(position);
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Cancel", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    // Remove item from the list
    private void removeItem(int position) {
        texts.remove(position);
        images.remove(position);
        notifyDataSetChanged();
    }
}