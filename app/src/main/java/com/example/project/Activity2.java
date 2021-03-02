package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Activity2 extends AppCompatActivity {

    private EditText input, output;
    private Button convertB, resetB;
    private Spinner sp1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String INPUT = "text3";
    public static final String OUTPUT = "text4";

    private String inputF2;
    private String outputF2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        input = findViewById(R.id.temp_input);
        output = findViewById(R.id.temp_output);

        convertB = findViewById(R.id.convert_btn);
        resetB = findViewById(R.id.reset_btn);

        sp1 = findViewById(R.id.spinner1);

        //put string items in the spinner
        String[] temp_spinner = {"Km", "M", "mm"};
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
        inputF2 = sharedPreferences.getString(INPUT, "");
        outputF2 = sharedPreferences.getString(OUTPUT, "");
    }

    public void updateViews(){
        input.setText(inputF2);
        output.setText(outputF2);
    }

    void convert(View sender){
        //conversion
        try {
            String inputStr = input.getText().toString();
            Float inputValue = Float.parseFloat(inputStr);

            if(sp1.getSelectedItem().toString() == "Km"){
                Float outputValue = inputValue*100000;
                String outputStr = String.format("%.2f", outputValue);
                output.setText(outputStr);
                output.setTextColor(Color.BLACK);
            }
            else if(sp1.getSelectedItem().toString() == "M"){
                Float outputValue = inputValue*100;
                String outputStr = String.format("%.2f", outputValue);
                output.setText(outputStr);
                output.setTextColor(Color.BLACK);
            }
            else if(sp1.getSelectedItem().toString() == "mm"){
                Float outputValue = inputValue*0.1f;
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

        String inputStr2 = sharedPreferences.getString("INPUT", "");
        input.setText(inputStr2);

        String outputStr2 = sharedPreferences.getString("OUTPUT", "");
        output.setText(outputStr2);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(INPUT);
        editor.remove(OUTPUT);
        editor.apply();
    }
}