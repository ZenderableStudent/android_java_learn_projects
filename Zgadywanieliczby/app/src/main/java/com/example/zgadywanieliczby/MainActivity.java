package com.example.zgadywanieliczby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int random;
    public int minimumNumber;
    public int maximumNumber;
    public int tryAmount=0;
    public void generateRandom()
    {
        random= new Random().nextInt((maximumNumber-minimumNumber)+1)+minimumNumber;
    }
    public int returnRandom()
    {
        return random;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button generateBtn= findViewById(R.id.generateBtn);
        final Button checkBtn= findViewById(R.id.checkBtn);
        final TextView maxNumber= findViewById(R.id.maxNumber);
        final TextView minNumber= findViewById(R.id.minimumNumber);
        final TextView guessNumber= findViewById(R.id.guessNumber);
        final TextView hint= findViewById(R.id.hint);
        final TextView tryAmountText=findViewById(R.id.tryAmountText);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get numbers from text field and parse them
                final String minNumberString=minNumber.getText().toString();
                final String maxNumberString=maxNumber.getText().toString();
                minimumNumber= Integer.parseInt(minNumberString);
                maximumNumber= Integer.parseInt(maxNumberString);
                if(maximumNumber< minimumNumber)
                {
                    Toast.makeText(getApplicationContext(),"Liczba minimalna jest większa od maksymalnej!",Toast.LENGTH_SHORT).show();
                } else
                {
                    generateRandom();
                    tryAmount=0;
                    hint.setText("Tutaj pojawi się podpowiedź!");
                    tryAmountText.setText("Próby: "+ Integer.valueOf(tryAmount));
                }
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String guessNumberString= guessNumber.getText().toString();
                final int guessNumberInt= Integer.parseInt(guessNumberString);
                if(guessNumberInt <minimumNumber || guessNumberInt> maximumNumber)
                {
                    Toast.makeText(getApplicationContext(),"Liczba poza zakresem!",Toast.LENGTH_SHORT).show();
                }
                else if (guessNumberInt<random)
                {
                    tryAmount++;
                    hint.setText("Liczba zbyt mała!");
                    tryAmountText.setText("Próby: "+Integer.valueOf(tryAmount));
                } else if(guessNumberInt== random)
                {
                    tryAmount++;
                    Toast.makeText(getApplicationContext(),"Brawo! Zgadłeś w:" + Integer.valueOf(tryAmount) + " próbach!",Toast.LENGTH_SHORT).show();
                    hint.setText("Brawo! Zgadłeś w: "+ Integer.valueOf(tryAmount)+ " próbach!");
                    tryAmountText.setText("Próby: "+Integer.valueOf(tryAmount));

                }
                else if(guessNumberInt> random)
                {
                    tryAmount++;
                    hint.setText("Liczba za duża!");
                    tryAmountText.setText("Próby: "+Integer.valueOf(tryAmount));
                }
            }
        });
    }
}
