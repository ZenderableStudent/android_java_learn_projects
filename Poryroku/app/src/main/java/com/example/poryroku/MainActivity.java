package com.example.poryroku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView poraRoku;

    List<String> wiosna = Arrays.asList("marzec", "kwiecień", "maj");
    List<String> lato = Arrays.asList("czerwiec", "lipiec", "sierpień");
    List<String> jesien = Arrays.asList("wrzesień", "październik", "listopad");
    List<String> zima = Arrays.asList("grudzień", "styczeń", "luty");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        poraRoku = (TextView) findViewById(R.id.textView);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("styczeń");
        arrayList.add("luty");
        arrayList.add("marzec");
        arrayList.add("kwiecień");
        arrayList.add("maj");
        arrayList.add("czerwiec");
        arrayList.add("lipiec");
        arrayList.add("sierpień");
        arrayList.add("wrzesień");
        arrayList.add("październik");
        arrayList.add("listopad");
        arrayList.add("grudzień");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String miesiac = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Meteorologiczna pora roku w miesiącu " + miesiac, Toast.LENGTH_LONG).show();
                if (wiosna.contains(miesiac)) {
                    poraRoku.setText("Wiosna!");
                }
                if (lato.contains(miesiac)) {
                    poraRoku.setText("Lato!");
                }
                if (jesien.contains(miesiac)) {
                    poraRoku.setText("Jesień!");
                }
                if (zima.contains(miesiac)) {
                    poraRoku.setText("Zima!");
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }
}