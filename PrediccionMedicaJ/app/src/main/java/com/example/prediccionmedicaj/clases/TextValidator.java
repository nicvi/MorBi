package com.example.prediccionmedicaj.clases;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public abstract class TextValidator  implements TextWatcher {

    private final TextInputEditText textInputEditText;

    public TextValidator(TextInputEditText textInputEditText) {
        this.textInputEditText = textInputEditText;
    }

    public abstract void validate(TextInputEditText textInputEditText, String data);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = Objects.requireNonNull(textInputEditText.getText()).toString();
        validate(textInputEditText, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}
