package com.example.myapplication.ui.calories;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCaloriesBinding;

import java.util.Objects;

public class CaloriesFragment extends Fragment {

    private TextView result;
    private double weight;
    private double height;
    private int age;
    private String sex;

    private FragmentCaloriesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCaloriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView weightInKg = (EditText) binding.weight;
        TextView heightInCm = (EditText) binding.height;
        TextView ageEditText = (EditText) binding.age;
        result = (TextView) binding.caloriesResult;
        Button confirm = (Button) binding.calculate;
        RadioGroup sexRadioGroup = (RadioGroup) binding.sex;

        weightInKg.addTextChangedListener(weightInKgWatcher);
        heightInCm.addTextChangedListener(heightInCmWatcher);
        ageEditText.addTextChangedListener(ageWatcher);

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    sex = "MALE";
                } else if (checkedId == R.id.female) {
                    sex = "FEMALE";
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight > 0 && height > 0 && !sex.isEmpty() && age > 0) {
                    calculate();
                }
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
        age = 0;
        sex = null;
    }

    private void calculate() {
        Resources res = getResources();
        Double kcal = null;
        if (Objects.equals(sex, "MALE")) {
            kcal = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age);
        } else if (Objects.equals(sex, "FEMALE")) {
            kcal = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
        }
        String text = String.format(res.getString(R.string.calories_result), kcal);
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

    private final TextWatcher ageWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                age = Integer.parseInt(charSequence.toString());
            } catch (NumberFormatException e) {
                age = 0;
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