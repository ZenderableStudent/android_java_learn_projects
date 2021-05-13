package com.example.listaobecnoci;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String [] nazwyGrup = {"Zajęcie A", "Zajęcie B", "Zajęcie C"};
    public List<String> osobyA = new ArrayList<String>();
    public List<String> osobyB = new ArrayList<String>();
    public List<String> osobyC = new ArrayList<String>();
    public String wybraneZajecieDoListy = "";
    KontrolerDB kontroler;
    Spinner grupyZajeciowe;
    CheckBox person1;
    CheckBox person2;
    CheckBox person3;
    CheckBox person4;
    CheckBox person5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kontroler = new KontrolerDB(this);
        setContentView(R.layout.activity_main);

        grupyZajeciowe = (Spinner) findViewById(R.id.spinner);
        person1 = (CheckBox) findViewById(R.id.checkBoxPerson1);
        person2 = (CheckBox) findViewById(R.id.checkBoxPerson2);
        person3 = (CheckBox) findViewById(R.id.checkBoxPerson3);
        person4 = (CheckBox) findViewById(R.id.checkBoxPerson4);
        person5 = (CheckBox) findViewById(R.id.checkBoxPerson5);

        ArrayAdapter<String> adapterNames= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nazwyGrup);
        grupyZajeciowe.setAdapter(adapterNames);

        kontroler.czyscRekordy();//czysci poprzednie dane, mozna pominąć
        //dodawanie osób do bazy
        kontroler.dodajOsobe(new Osoba("John", "Doe", "Zajęcie A"));
        kontroler.dodajOsobe(new Osoba("Bobby", "Fisher", "Zajęcie A"));
        kontroler.dodajOsobe(new Osoba("Greta", "Piotrowski", "Zajęcie A"));
        kontroler.dodajOsobe(new Osoba("Samantha", "Jackowski", "Zajęcie A"));
        kontroler.dodajOsobe(new Osoba("Tomasz", "Kazimierski", "Zajęcie A"));

        kontroler.dodajOsobe(new Osoba("Andrzej", "Purek", "Zajęcie B"));
        kontroler.dodajOsobe(new Osoba("Angela", "Bigetti", "Zajęcie B"));
        kontroler.dodajOsobe(new Osoba("Pam", "Rumek", "Zajęcie B"));
        kontroler.dodajOsobe(new Osoba("Dwight", "Karakan", "Zajęcie B"));
        kontroler.dodajOsobe(new Osoba("Mariusz", "Popkowski", "Zajęcie B"));

        kontroler.dodajOsobe(new Osoba("Kevin", "Malone", "Zajęcie C"));
        kontroler.dodajOsobe(new Osoba("Tony", "Stark", "Zajęcie C"));
        kontroler.dodajOsobe(new Osoba("Mackie", "Bomba", "Zajęcie C"));
        kontroler.dodajOsobe(new Osoba("Tom", "Trouble", "Zajęcie C"));
        kontroler.dodajOsobe(new Osoba("Harry", "Kowalski", "Zajęcie C"));

        List<Osoba> wybrane;
        String wybrane_zajecie = grupyZajeciowe.getSelectedItem().toString();
        wybraneZajecieDoListy = wybrane_zajecie;
        wybrane = kontroler.pobierzWszystkich();
        dodajDaneCheckBoxy(wybrane, wybrane_zajecie);

    }

    private void dodajDoListy(CheckBox person) {
        if (wybraneZajecieDoListy == "Zajęcie A") {
            osobyA.add(person.getText().toString());
        }
        else if (wybraneZajecieDoListy == "Zajęcie B") {
            osobyB.add(person.getText().toString());
        }
        else if (wybraneZajecieDoListy == "Zajęcie C") {
            osobyC.add(person.getText().toString());
        }
    }

    public void potwierdzObecnosc(View view) {
        Context context = getApplicationContext();
        List<String> osobyObecne = new ArrayList<String>();

        if (wybraneZajecieDoListy == "Zajęcie A") {
            if (!osobyA.isEmpty()) {
                osobyA.clear();
            }
        }
        else if (wybraneZajecieDoListy == "Zajęcie B") {
            if (!osobyB.isEmpty()) {
                osobyB.clear();
            }
        }
        else if (wybraneZajecieDoListy == "Zajęcie C") {
            if (!osobyC.isEmpty()) {
                osobyC.clear();
            }
        }

        int count = 0;
        if (person1.isChecked()) {
            count++;
            osobyObecne.add(person1.getText().toString());
            dodajDoListy(person1);
        }
        if (person2.isChecked()) {
            count++;
            osobyObecne.add(person2.getText().toString());
            dodajDoListy(person2);
        }
        if (person3.isChecked()) {
            count++;
            osobyObecne.add(person3.getText().toString());
            dodajDoListy(person3);
        }
        if (person4.isChecked()) {
            count++;
            osobyObecne.add(person4.getText().toString());
            dodajDoListy(person4);
        }
        if (person5.isChecked()) {
            count++;
            osobyObecne.add(person5.getText().toString());
            dodajDoListy(person5);
        }

        CharSequence text = "Ilość osób obecnych: " + count;

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void uncheck_checkbox(CheckBox person) {
        if (person.isChecked()) {
            person.toggle();
        }
    }

    public void wykonajKlik(View view){
        List<Osoba> wybrane;
        String wybrane_zajecie = grupyZajeciowe.getSelectedItem().toString();
        wybraneZajecieDoListy = wybrane_zajecie;
        wybrane = kontroler.pobierzWszystkich();
        dodajDaneCheckBoxy(wybrane, wybrane_zajecie);

        uncheck_checkbox(person1);
        uncheck_checkbox(person2);
        uncheck_checkbox(person3);
        uncheck_checkbox(person4);
        uncheck_checkbox(person5);
    }

    private void openDialog(List<String> lista){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Lista obecności");
        CharSequence text = "Lista obecności:\n" + lista;
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    public void sprawdzListeA(View view){
        openDialog(osobyA);
    }

    public void sprawdzListeB(View view){
        openDialog(osobyB);
    }

    public void sprawdzListeC(View view){
        openDialog(osobyC);
    }

    private void dodajDaneCheckBoxy(List<Osoba> wyniki, String wybrane_zajecie)  // procedura wyswietlająca listę z bazy danych na ekranie
    {
        List<String> osobyZajecie = new ArrayList<String>(5);
        String name;
        for(Osoba o: wyniki){
            if (o.getZajecie().equals(wybrane_zajecie)) {
                name = o.getImie() + " " + o.getNazwisko();
                System.out.println(name);
                osobyZajecie.add(name);
            }
        }

        person1.setText(osobyZajecie.get(0));
        person2.setText(osobyZajecie.get(1));
        person3.setText(osobyZajecie.get(2));
        person4.setText(osobyZajecie.get(3));
        person5.setText(osobyZajecie.get(4));
    }
}