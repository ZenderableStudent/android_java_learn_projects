package com.example.roznekomponenty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CheckBox checkBox = findViewById(R.id.checkBox);
        final Button button1 = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button2);
        final RadioButton rButton = findViewById(R.id.radioButton);
        final RadioButton rButton2 = findViewById(R.id.radioButton2);
        final TextView textView = findViewById(R.id.textView);
        final TextView textView2 = findViewById(R.id.textView2);
        final EditText editText = findViewById(R.id.editText);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                    editText.setEnabled(true);
                }
                else{
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    editText.setEnabled(false);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rButton.isChecked()){
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                    checkBox.setVisibility(View.INVISIBLE);
                }
                else if(rButton2.isChecked()){
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    checkBox.setVisibility(View.VISIBLE);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(textView.getText().toString());
                a++;
                textView.setText(Integer.toString(a));
                Toast.makeText(getApplicationContext(),editText.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(textView.getText().toString());
                a++;
                textView.setText(Integer.toString(a));
            }
        });

    }
}