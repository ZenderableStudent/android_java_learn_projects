package com.example.edytortekstu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    Button load;
    Button save;
    EditText editorPool;
    EditText editTitle;
    public String fileName;

    public void saveclick(View view){
        String text = editorPool.getText().toString();
        writeToFile(text,this);
    }
    public void loadclick(View view){
        String text = readFromFile(this);
        editorPool.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load = (Button)findViewById(R.id.button3);
        save = (Button)findViewById(R.id.button4);
        editorPool = (EditText)findViewById(R.id.editTextTextMultiLine);
        editTitle = (EditText)findViewById(R.id.editTextTextTitle);
    }
    private void writeToFile(String data,Context context) {
        try {
            fileName = editTitle.getText().toString();
            fileName = fileName.isEmpty() ? "temp" : fileName;
            fileName += ".txt";
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String returnText = "";

        try {
            fileName = editTitle.getText().toString();
            fileName = fileName.isEmpty() ? "temp" : fileName;
            fileName += ".txt";
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                returnText = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return returnText;
    }
}
