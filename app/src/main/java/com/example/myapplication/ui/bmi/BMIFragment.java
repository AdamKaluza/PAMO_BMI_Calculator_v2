package com.example.myapplication.ui.bmi;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentBmiBinding;

public class BMIFragment extends Fragment {

    private TextView result;
    private double weight;
    private double height;
    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView weightInKg = (EditText) binding.weightInKg;
        TextView heightInCm = (EditText) binding.heightInCm;
        result = (TextView) binding.result;
        Button confirm = (Button) binding.confirm;

        weightInKg.addTextChangedListener(weightInKgWatcher);
        heightInCm.addTextChangedListener(heightInCmWatcher);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        weight = 0.0;
        height = 0.0;
    }

    private void calculate() {
        Resources res = getResources();
        Double bmi = weight / Math.pow(height / 100, 2);
        String text = String.format(res.getString(R.string.result), bmi);
        result.setText(text);
    }

    private final TextWatcher weightInKgWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                weight = Double.parseDouble(charSequence.toString());
            } catch (NumberFormatException e) {
                weight = 0.0;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher heightInCmWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                height = Double.parseDouble(charSequence.toString());
            } catch (NumberFormatException e) {
                height = 0;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}