package com.example.dwaspinnery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public String [] imiona = {"Andrzej", "Piotr", "Maciej", "Krzysztof", "Filip", "Patryk"};
    public String [] dni = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
    Spinner spinnerNames;
    Spinner spinnerDays;
    EditText Table;

    public void addText(View view)
    {
        String name = spinnerNames.getSelectedItem().toString();
        String day = spinnerDays.getSelectedItem().toString();
        String currentText = Table.getText().toString();
        String expression;
        expression = "|---------------------------------------|\n";
        expression += "| " + name + " | " + day + "\n";
        expression += "|---------------------------------------|";
        Table.setText(currentText + "\n"+ expression);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign items to spinner for names
        spinnerNames = (Spinner) findViewById(R.id.spinnerImiona);
        ArrayAdapter<String> adapterNames= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, imiona);
        spinnerNames.setAdapter(adapterNames);

        //assign items to spinner for days
        spinnerDays = (Spinner) findViewById(R.id.spinnerDni);
        ArrayAdapter<String> adapterDays= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dni);
        spinnerDays.setAdapter(adapterDays);

        Table = (EditText) findViewById(R.id.Table);
    }
}