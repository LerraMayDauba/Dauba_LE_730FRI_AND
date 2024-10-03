package com.dauba.menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class CustomDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(view);

        // Plus button for navigating to AboutFragment
        ImageButton plusButton = view.findViewById(R.id.plus_button);
        plusButton.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new AboutFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            dismiss();
        });

        // Minus button for showing the "More" options
        ImageButton minusButton = view.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(v -> {
            // Get reference to the toolbar's more options button
            ImageView moreOptionsButton = requireActivity().findViewById(R.id.moreOptions);

            // Simulate a click on the moreOptions button
            PopupMenu popup = new PopupMenu(requireActivity(), moreOptionsButton);
            popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.Help) {
                    // Handle Help click (you can add more functionality here)
                    return true;
                } else if (item.getItemId() == R.id.Exit) {
                    // Handle Exit click
                    requireActivity().finishAffinity();
                    return true;
                }
                return false;
            });
            popup.show();
        });

        return builder.create();
    }
}
