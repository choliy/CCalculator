package com.choliy.igor.ccalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

final class CalcUtils {

    static void setSpinner(final Context context) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Spinner spinner = (Spinner) ((AppCompatActivity) context).findViewById(R.id.spinner);

        String theme = context.getString(R.string.theme);
        String white = context.getString(R.string.white);
        String black = context.getString(R.string.black);
        String green = context.getString(R.string.green);
        String gold = context.getString(R.string.gold);
        String warm = context.getString(R.string.warm);
        String pink = context.getString(R.string.pink);

        String spinnerList[] = {theme, white, black, green, warm, pink, gold};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_text, spinnerList);
        switch (preferences.getInt(CalcConstants.KEY_THEME, 0)) {
            case 0:
                adapter.setDropDownViewResource(R.layout.spinner_white_menu);
                break;
            case 1:
                adapter.setDropDownViewResource(R.layout.spinner_white_menu);
                break;
            case 2:
                adapter.setDropDownViewResource(R.layout.spinner_black_menu);
                break;
            case 3:
                adapter.setDropDownViewResource(R.layout.spinner_green_menu);
                break;
            case 4:
                adapter.setDropDownViewResource(R.layout.spinner_warm_menu);
                break;
            case 5:
                adapter.setDropDownViewResource(R.layout.spinner_pink_menu);
                break;
            case 6:
                adapter.setDropDownViewResource(R.layout.spinner_gold_menu);
                break;
        }
        spinner.setAdapter(adapter);

        // set first position not clickable
        int firstPosition = spinner.getSelectedItemPosition();
        spinner.setSelection(firstPosition, false);

        // set listener to spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor edit = preferences.edit();
                switch (position) {
                    case 0:
                        edit.putInt(CalcConstants.KEY_THEME, 0);
                        break;
                    case 1:
                        edit.putInt(CalcConstants.KEY_THEME, 1);
                        break;
                    case 2:
                        edit.putInt(CalcConstants.KEY_THEME, 2);
                        break;
                    case 3:
                        edit.putInt(CalcConstants.KEY_THEME, 3);
                        break;
                    case 4:
                        edit.putInt(CalcConstants.KEY_THEME, 4);
                        break;
                    case 5:
                        edit.putInt(CalcConstants.KEY_THEME, 5);
                        break;
                    case 6:
                        edit.putInt(CalcConstants.KEY_THEME, 6);
                        break;
                }
                edit.apply();

                // reload activity
                Intent intent = new Intent(context, CalcActivity.class);
                context.startActivity(intent);
                ((AppCompatActivity) context).finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    static void setTheme(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int style = preferences.getInt(CalcConstants.KEY_THEME, 0);
        switch (style) {
            case 0:
                ((AppCompatActivity) context).setContentView(R.layout.activity_white);
                break;
            case 1:
                ((AppCompatActivity) context).setContentView(R.layout.activity_white);
                break;
            case 2:
                ((AppCompatActivity) context).setContentView(R.layout.activity_black);
                break;
            case 3:
                ((AppCompatActivity) context).setContentView(R.layout.activity_green);
                break;
            case 4:
                ((AppCompatActivity) context).setContentView(R.layout.activity_warm);
                break;
            case 5:
                ((AppCompatActivity) context).setContentView(R.layout.activity_pink);
                break;
            case 6:
                ((AppCompatActivity) context).setContentView(R.layout.activity_gold);
                break;
        }
    }
}