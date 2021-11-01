package com.example.caclulator;

import org.mariuszgromada.math.mxparser.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;

import com.example.caclulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.input.setShowSoftInputOnFocus(false);

        binding.input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Enter A Value".equals(binding.input.getText().toString())) {
                    binding.input.setText("");
                }
            }
        });
        binding.backspaceBTN.setOnClickListener(this::backspaceBTN);

    }

    private void updateText(String strToAdd) {
        String oldString = binding.input.getText().toString();
        int cursorPos = binding.input.getSelectionStart();
        String leftStr = oldString.substring(0, cursorPos);
        String rightStr = oldString.substring(cursorPos);
        if ("Enter A Value".equals(binding.input.getText().toString())) {
            binding.input.setText(strToAdd);
            binding.input.setSelection(cursorPos + 1);
        } else {
            binding.input.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            binding.input.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view) {

        updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");

    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");

    }

    public void fourBTN(View view) {
        updateText("4");

    }

    public void fiveBTN(View view) {
        updateText("5");

    }

    public void sixBTN(View view) {
        updateText("6");

    }

    public void sevenBTN(View view) {
        updateText("7");

    }

    public void eightBTN(View view) {
        updateText("8");

    }

    public void nineBTN(View view) {
        updateText("9");

    }

    public void addBTN(View view) {
        updateText("+");

    }

    public void subtractBTN(View view) {
        updateText("-");

    }

    public void divideBTN(View view) {
        updateText("÷");

    }

    public void multiplyBTN(View view) {
        updateText("×");

    }

    public void equalBTN(View view) {

        String userExp = binding.input.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression((userExp));
        String result = String.valueOf(exp.calculate());
        binding.input.setText(result);
        binding.input.setSelection(result.length());

    }

    public void decimalBTN(View view) {
        updateText(".");

    }

    public void plusMinusBTN(View view) {
        updateText("-");

    }

    public void clearBTN(View view) {
        binding.input.setText("");

    }

    public void perensBTN(View view) {
        int cursorPos = binding.input.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLength = binding.input.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (binding.input.getText().toString().substring(i, i + 1).equals("(")) {
                openPar++;
            }
            if (binding.input.getText().toString().substring(i, i + 1).equals(")")) {
                closedPar++;
            }
        }
        if (openPar == closedPar || binding.input.getText().toString().substring(textLength - 1, textLength).equals("(")) {
            updateText("(");
        } else if (closedPar < openPar && !binding.input.getText().toString().substring(textLength - 1, textLength).equals("(")) {
            updateText(")");
        }
        binding.input.setSelection(cursorPos + 1);

    }

    public void expoBTN(View view) {
        updateText("^");

    }

    public void backspaceBTN(View view) {
        int cursorPos = binding.input.getSelectionStart();
        int textLength = binding.input.getText().length();
        if (cursorPos != 0 && textLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) binding.input.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            binding.input.setText(selection);
            binding.input.setSelection(cursorPos - 1);

        }
    }


}

