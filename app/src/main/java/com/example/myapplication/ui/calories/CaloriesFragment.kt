package com.example.myapplication.ui.calories

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCaloriesBinding

class CaloriesFragment : Fragment() {
    private var result: TextView? = null
    private var weight = 0.0
    private var height = 0.0
    private var age = 0
    private var sex: String? = null
    private var binding: FragmentCaloriesBinding? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCaloriesBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        result = binding!!.caloriesResult
        binding!!.weight.addTextChangedListener(weightInKgWatcher)
        binding!!.height.addTextChangedListener(heightInCmWatcher)
        binding!!.age.addTextChangedListener(ageWatcher)
        binding!!.sex.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.male) {
                sex = "MALE"
            } else if (checkedId == R.id.female) {
                sex = "FEMALE"
            }
        }
        binding!!.calculate.setOnClickListener {
            if (weight > 0 && height > 0 && !sex!!.isEmpty() && age > 0) {
                calculate()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        weight = 0.0
        height = 0.0
        age = 0
        sex = null
    }

    private fun calculate() {
        val res = resources
        var kcal: Double? = null
        if (sex == "MALE") {
            kcal = 66.5 + 13.75 * weight + 5.003 * height - 6.775 * age
        } else if (sex == "FEMALE") {
            kcal = 655.1 + 9.563 * weight + 1.85 * height - 4.676 * age
        }
        val text = String.format(res.getString(R.string.calories_result), kcal)
        result!!.text = text
    }

    private val weightInKgWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            weight = try {
                charSequence.toString().toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {}
    }
    private val heightInCmWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            height = try {
                charSequence.toString().toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {}
    }
    private val ageWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            age = try {
                charSequence.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {}
    }
}