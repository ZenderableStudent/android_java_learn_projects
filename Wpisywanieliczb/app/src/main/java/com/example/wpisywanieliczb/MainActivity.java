package com.example.wpisywanieliczb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = findViewById(R.id.button_id1);
        final Button button2 = findViewById(R.id.button_id2);
        final Button button3 = findViewById(R.id.button_id3);
        final Button button4 = findViewById(R.id.button_id4);
        final Button button5 = findViewById(R.id.button_id5);
        final Button button6 = findViewById(R.id.button_id6);
        final Button button7 = findViewById(R.id.button_id7);
        final Button button8 = findViewById(R.id.button_id8);
        final Button button9 = findViewById(R.id.button_id9);
        final Button button0 = findViewById(R.id.button_id0);
        final Button buttonC = findViewById(R.id.button_idC);
        final Button buttonCofnij = findViewById(R.id.button_idCofnij);

        final TextView liczbaText = findViewById(R.id.liczbaView);

        liczbaText.setText("");
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                liczbaText.setText(text + "0");
            }
        });

        buttonCofnij.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();
                text = removeLastChar(text);
                liczbaText.setText(text);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = (String) liczbaText.getText();

                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

                liczbaText.setText("");
            }
        });

    }
}