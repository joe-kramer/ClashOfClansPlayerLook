package com.joekramer.clashofclansplayerlook.textValidation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import com.joekramer.clashofclansplayerlook.ui.MainActivity;


public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable s) {

        String filtered_str = s.toString();

        if (filtered_str.matches(".*[^A-Z^0-9^#].*")) {

            filtered_str = filtered_str.replaceAll("[^a-z^0-9]", "");

            s.clear();

            // s.insert(0, filtered_str);
            //TODO set length validation
            Toast.makeText(textView.getContext(),
                    "Hash mark, with combination of uppercase letters and numbers",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}