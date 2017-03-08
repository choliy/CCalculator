package com.choliy.igor.ccalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import me.grantland.widget.AutofitTextView;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mEnterText;
    private TextView mEqualsText;

    private double mFirstDouble = CalcConstants.DOUBLE_NULL;
    private double mSecondDouble = CalcConstants.DOUBLE_NULL;
    private String mFirstString = CalcConstants.STRING_NULL;
    private String mSecondString = CalcConstants.STRING_NULL;
    private String mEquals = CalcConstants.STRING_NULL;
    private String mSing = CalcConstants.STRING_NULL;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalcUtils.setTheme(this);
        CalcUtils.setSpinner(this);
        setUi();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(CalcConstants.KEY_ENTER_TEXT, mEnterText.getText().toString());
        edit.putString(CalcConstants.KEY_EQUALS_TEXT, mEqualsText.getText().toString());
        edit.putString(CalcConstants.KEY_FIRST_DOUBLE, String.valueOf(mFirstDouble));
        edit.putString(CalcConstants.KEY_SECOND_DOUBLE, String.valueOf(mSecondDouble));
        edit.putString(CalcConstants.KEY_FIRST_STRING, mFirstString);
        edit.putString(CalcConstants.KEY_SECOND_STRING, mSecondString);
        edit.putString(CalcConstants.KEY_EQUALS, mEquals);
        edit.putString(CalcConstants.KEY_SIGN, mSing);
        edit.apply();
    }

    @Override
    public void onClick(View v) {
        numberPress(v);
        toolPress(v);
    }

    public void pointButton(View v) {

        // logic for firstNumber
        if (mFirstString.length() > 0 && mSing.length() == 0 && mSecondString.length() == 0) {
            if (!mFirstString.contains(".")) {
                mFirstString += ".";
                mFirstDouble = Double.parseDouble(mFirstString);
                mEnterText.setText(mFirstString);
            } else if (mFirstString.contains(".")) {
                mEnterText.setText(mFirstString);
            }
        }

        // logic for sing
        else if (mFirstString.length() > 0 && mSing.length() > 0 && mSecondString.length() == 0) {
            String text = mFirstString + mSing;
            mEnterText.setText(text);
        }

        // logic for secondNumber
        else if (mFirstString.length() > 0 && mSing.length() > 0 && mSecondString.length() > 0) {
            if (!mSecondString.contains(".")) {
                mSecondString += ".";
                mSecondDouble = Double.parseDouble(mSecondString);
                String text = mFirstString + mSing + mSecondString;
                mEnterText.setText(text);
            } else if (mSecondString.contains(".")) {
                String text = mFirstString + mSing + mSecondString;
                mEnterText.setText(text);
            }
        }
    }

    public void clearButton(View v) {

        // logic for firstNumber
        if (mFirstString.length() > 0 && mSing.length() == 0 && mSecondString.length() == 0) {
            mFirstString = mFirstString.substring(0, mFirstString.length() - 1);
            if (mFirstString.length() > 0) {
                mFirstDouble = Double.parseDouble(mFirstString);
                mEnterText.setText(mFirstString);
            } else if (mFirstString.length() == 0) {
                mFirstDouble = CalcConstants.DOUBLE_NULL;
                mEnterText.setText(CalcConstants.STRING_NULL);
            }
        }

        // logic for sing
        else if (mFirstString.length() > 0 && mSing.length() > 0 && mSecondString.length() == 0) {
            mSing = mSing.substring(0, mSing.length() - 1);
            String text = mFirstString + mSing;
            mEnterText.setText(text);
        }

        // logic for secondNumber
        else if (mFirstString.length() > 0 && mSing.length() > 0 && mSecondString.length() > 0) {
            mSecondString = mSecondString.substring(0, mSecondString.length() - 1);
            if (mSecondString.length() > 0) {
                mSecondDouble = Double.parseDouble(mSecondString);
                String text = mFirstString + mSing + mSecondString;
                mEnterText.setText(text);
            } else if (mSecondString.length() == 0) {
                mSecondDouble = CalcConstants.DOUBLE_NULL;
                String text = mFirstString + mSing;
                mEnterText.setText(text);
            }
        }
    }

    private void numberPress(View v) {
        switch (v.getId()) {
            case R.id.zero_button:
                setNumber((byte) 0);
                break;
            case R.id.one_button:
                setNumber((byte) 1);
                break;
            case R.id.two_button:
                setNumber((byte) 2);
                break;
            case R.id.three_button:
                setNumber((byte) 3);
                break;
            case R.id.four_button:
                setNumber((byte) 4);
                break;
            case R.id.five_button:
                setNumber((byte) 5);
                break;
            case R.id.six_button:
                setNumber((byte) 6);
                break;
            case R.id.seven_button:
                setNumber((byte) 7);
                break;
            case R.id.eight_button:
                setNumber((byte) 8);
                break;
            case R.id.nine_button:
                setNumber((byte) 9);
                break;
        }
    }

    private void toolPress(View v) {
        switch (v.getId()) {

            // c button
            case R.id.c_button:
                mEnterText.setText(CalcConstants.STRING_NULL);
                mEqualsText.setText(CalcConstants.STRING_NULL);
                mFirstDouble = CalcConstants.DOUBLE_NULL;
                mSecondDouble = CalcConstants.DOUBLE_NULL;
                mFirstString = CalcConstants.STRING_NULL;
                mSecondString = CalcConstants.STRING_NULL;
                mEquals = CalcConstants.STRING_NULL;
                mSing = CalcConstants.STRING_NULL;
                break;

            // equals button
            case R.id.equals_button:
                if (mFirstString.length() > 0 && mSecondString.length() > 0) {
                    switch (mSing) {
                        case "÷":
                            setEquals('÷');
                            break;
                        case "×":
                            setEquals('×');
                            break;
                        case "-":
                            setEquals('-');
                            break;
                        case "+":
                            setEquals('+');
                            break;
                    }
                }
                break;

            // divide button
            case R.id.divide_button:
                setSign("÷");
                break;

            // multiply button
            case R.id.multiply_button:
                setSign("×");
                break;

            // minus button
            case R.id.minus_button:
                setSign("-");
                break;

            // plus button
            case R.id.plus_button:
                setSign("+");
                break;
        }
    }

    private void setNumber(byte number) {
        if (mSing.length() == 0) {
            // first number
            if (mFirstString.length() == 0
                    || mFirstString.startsWith("0.")
                    || mFirstString.charAt(0) != '0') {
                mFirstString += number;
                mFirstDouble = Double.parseDouble(mFirstString);
                mEnterText.setText(mFirstString);
            } else {
                mEnterText.setText(mFirstString);
            }

        } else if (mSing.length() > 0) {
            // second number
            if (mSecondString.length() == 0
                    || mSecondString.startsWith("0.")
                    || mSecondString.charAt(0) != '0') {
                mSecondString += number;
                mSecondDouble = Double.parseDouble(mSecondString);
                String text = mFirstString + mSing + mSecondString;
                mEnterText.setText(text);
            } else {
                String text = mFirstString + mSing + mSecondString;
                mEnterText.setText(text);
            }
        }
    }

    private void setSign(String sign) {

        // before first number
        if (mFirstString.length() == 0) {
            mFirstString = sign;
            if (mFirstString.equals("-")) {
                mFirstDouble = Double.parseDouble(mFirstString + "0");
                mEnterText.setText(mFirstString);
            } else {
                mFirstDouble = CalcConstants.DOUBLE_NULL;
                mFirstString = CalcConstants.STRING_NULL;
                mEnterText.setText(CalcConstants.STRING_NULL);
            }
        } else if (mFirstString.startsWith("-") && mFirstString.length() == 1) {
            mEnterText.setText(mFirstString);
        }

        // after first number
        else if (mFirstString.length() > 0 && mSing.length() == 0) {
            if (mFirstString.endsWith(".")) {
                mFirstString = mFirstString.replace(".", CalcConstants.STRING_NULL);
                mSing = sign;
                String text = mFirstString + mSing;
                mEnterText.setText(text);
            } else {
                mSing = sign;
                mEnterText.append(mSing);
            }
        }

        // after sing
        else if (mFirstString.length() > 0 && mSing.length() > 0 && mSecondString.length() == 0) {
            mSecondString = sign;
            if (mSecondString.equals("-")) {
                mSecondDouble = Double.parseDouble(mSecondString + "0");
                String text = mFirstString + mSing + mSecondString;
                mEnterText.setText(text);
            } else {
                mSecondDouble = CalcConstants.DOUBLE_NULL;
                mSecondString = CalcConstants.STRING_NULL;
                String text = mFirstString + mSing;
                mEnterText.setText(text);
            }
        }

        // after second number
        else if (mSecondString.length() > 0) {
            switch (mSing) {
                case "÷":
                    mSing = sign;
                    setAction('÷');
                    partOfSetSing();
                    break;
                case "×":
                    mSing = sign;
                    setAction('×');
                    partOfSetSing();
                    break;
                case "-":
                    mSing = sign;
                    setAction('-');
                    partOfSetSing();
                    break;
                case "+":
                    mSing = sign;
                    setAction('+');
                    partOfSetSing();
                    break;
            }
        }
    }

    private void setAction(char sign) {
        double equalsDouble = CalcConstants.DOUBLE_NULL;
        long equalsLong;
        if (sign == '÷') {
            equalsDouble = mFirstDouble / mSecondDouble;
        } else if (sign == '×') {
            equalsDouble = mFirstDouble * mSecondDouble;
        } else if (sign == '-') {
            equalsDouble = mFirstDouble - mSecondDouble;
        } else if (sign == '+') {
            equalsDouble = mFirstDouble + mSecondDouble;
        }

        mEquals = Double.toString(equalsDouble);
        if (mEquals.endsWith(".0")) {
            equalsLong = Math.round(equalsDouble);
            mEquals = Long.toString(equalsLong);
        } else {
            mEquals = String.valueOf(equalsDouble);
        }
    }

    private void setEquals(char sign) {
        BigDecimal firstVariable = BigDecimal.valueOf(mFirstDouble);
        BigDecimal secondVariable = BigDecimal.valueOf(mSecondDouble);
        BigDecimal result = null;
        switch (sign) {
            case '÷':
                result = firstVariable.divide(secondVariable, 20, BigDecimal.ROUND_HALF_UP);
                break;
            case '×':
                result = firstVariable.multiply(secondVariable);
                break;
            case '-':
                result = firstVariable.subtract(secondVariable);
                break;
            case '+':
                result = firstVariable.add(secondVariable);
                break;
        }
        String stringResult = String.valueOf(result);
        stringResult = !stringResult.contains(".") ? stringResult :
                stringResult.replaceAll("0*$", "").replaceAll("\\.$", "");

        mEquals = String.valueOf(stringResult);
        String text = "=" + mEquals;
        mEqualsText.setText(text);
    }

    private void setUi() {

        // add text
        mEnterText = (AutofitTextView) findViewById(R.id.enter_text);
        mEqualsText = (AutofitTextView) findViewById(R.id.equals_text);

        // add numbers
        Button zeroButton = (Button) findViewById(R.id.zero_button);
        Button oneButton = (Button) findViewById(R.id.one_button);
        Button twoButton = (Button) findViewById(R.id.two_button);
        Button threeButton = (Button) findViewById(R.id.three_button);
        Button fourButton = (Button) findViewById(R.id.four_button);
        Button fiveButton = (Button) findViewById(R.id.five_button);
        Button sixButton = (Button) findViewById(R.id.six_button);
        Button sevenButton = (Button) findViewById(R.id.seven_button);
        Button eightButton = (Button) findViewById(R.id.eight_button);
        Button nineButton = (Button) findViewById(R.id.nine_button);

        // add tools
        Button cButton = (Button) findViewById(R.id.c_button);
        Button equalsButton = (Button) findViewById(R.id.equals_button);
        Button divideButton = (Button) findViewById(R.id.divide_button);
        Button multiplyButton = (Button) findViewById(R.id.multiply_button);
        Button minusButton = (Button) findViewById(R.id.minus_button);
        Button plusButton = (Button) findViewById(R.id.plus_button);

        // add listeners to numbers
        zeroButton.setOnClickListener(this);
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);

        // add listeners to tools
        cButton.setOnClickListener(this);
        equalsButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);

        // restore data
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String enterText = preferences
                .getString(CalcConstants.KEY_ENTER_TEXT, CalcConstants.STRING_NULL);
        mEnterText.setText(enterText);
        String equalsText = preferences
                .getString(CalcConstants.KEY_EQUALS_TEXT, CalcConstants.STRING_NULL);
        mEqualsText.setText(equalsText);
        String firstDouble = preferences
                .getString(CalcConstants.KEY_FIRST_DOUBLE, CalcConstants.STRING_NULL);
        if (!firstDouble.equals(CalcConstants.STRING_NULL)) {
            mFirstDouble = Double.valueOf(firstDouble);
        }
        String secondDouble = preferences
                .getString(CalcConstants.KEY_SECOND_DOUBLE, CalcConstants.STRING_NULL);
        if (!secondDouble.equals(CalcConstants.STRING_NULL)) {
            mSecondDouble = Double.valueOf(secondDouble);
        }
        mFirstString = preferences
                .getString(CalcConstants.KEY_FIRST_STRING, CalcConstants.STRING_NULL);
        mSecondString = preferences
                .getString(CalcConstants.KEY_SECOND_STRING, CalcConstants.STRING_NULL);
        mEquals = preferences
                .getString(CalcConstants.KEY_EQUALS, CalcConstants.STRING_NULL);
        mSing = preferences
                .getString(CalcConstants.KEY_SIGN, CalcConstants.STRING_NULL);
    }

    private void partOfSetSing() {
        mFirstString = mEquals;
        mFirstDouble = Double.parseDouble(mEquals);
        mSecondString = CalcConstants.STRING_NULL;
        mSecondDouble = CalcConstants.DOUBLE_NULL;
        String text = mFirstString + mSing;
        mEnterText.setText(text);
    }
}