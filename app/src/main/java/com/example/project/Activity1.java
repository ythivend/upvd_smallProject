package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity {

    private EditText input, output;
    private Button convertB, resetB;
    private Spinner sp1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String INPUT = "text";
    public static final String OUTPUT = "text2";

    private String inputF;
    private String outputF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        input = findViewById(R.id.temp_input);
        output = findViewById(R.id.temp_output);

        convertB = findViewById(R.id.convert_btn);
        resetB = findViewById(R.id.reset_btn);

        sp1 = findViewById(R.id.spinner1);

        //put string items in the spinner
        String[] temp_spinner = {"Fahrenheit", "Kelvin"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, temp_spinner);
        sp1.setAdapter(adapter);

        convertB.setOnClickListener(this::convert);
        resetB.setOnClickListener(this::reset);

        loadData();
        updateViews();
    }

    public void saveData(){
        //saving data to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(INPUT, input.getText().toString());
        editor.putString(OUTPUT, output.getText().toString());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        inputF = sharedPreferences.getString(INPUT, "");
        outputF = sharedPreferences.getString(OUTPUT, "");
    }

    public void updateViews(){
        input.setText(inputF);
        output.setText(outputF);
    }

    void convert(View sender) {
        //conversion
        try {
            String inputStr = input.getText().toString();
            Float inputValue = Float.parseFloat(inputStr);

            if (sp1.getSelectedItem().toString() == "Fahrenheit") {
                Float outputValue = (inputValue - 32) / 1.8f;
                String outputStr = String.format("%.2f", outputValue);
                output.setText(outputStr);
                output.setTextColor(Color.BLACK);

            } else if (sp1.getSelectedItem().toString() == "Kelvin") {
                Float outputValue = inputValue - 273.15f;
                String outputStr = String.format("%.2f", outputValue);
                output.setText(outputStr);
                output.setTextColor(Color.BLACK);
            }
            saveData();
        }
        catch (Exception e){
            output.setText(getApplicationContext().getResources().getString(R.string.invalid));
            output.setTextColor(Color.RED);
        }

    }

    void reset(View sender){
        //retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        String inputStr1 = sharedPreferences.getString("INPUT", "");
        input.setText(inputStr1);

        String outputStr1 = sharedPreferences.getString("OUTPUT", "");
        output.setText(outputStr1);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(INPUT);
        editor.remove(OUTPUT);
        editor.apply();
    }
}