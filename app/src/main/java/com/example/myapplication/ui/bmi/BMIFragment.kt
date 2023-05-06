package com.example.myapplication.ui.bmi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBmiBinding

class BMIFragment : Fragment() {
    private var result: TextView? = null
    private var weight = 0.0
    private var height = 0.0
    private var binding: FragmentBmiBinding? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        result = binding!!.result
        binding!!.weightInKg.addTextChangedListener(weightInKgWatcher)
        binding!!.heightInCm.addTextChangedListener(heightInCmWatcher)
        binding!!.confirm.setOnClickListener { calculate() }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        weight = 0.0
        height = 0.0
    }

    private fun calculate() {
        val res = resources
        val bmi = weight / Math.pow(height / 100, 2.0)
        val text = String.format(res.getString(R.string.result), bmi)
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
}