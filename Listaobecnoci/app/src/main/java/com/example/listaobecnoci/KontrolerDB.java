package com.example.listaobecnoci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class KontrolerDB extends SQLiteOpenHelper { //rozszerzenie klasy pomagającej zarządzaniem baz SQLite

    public KontrolerDB(Context context) {
        super(context, "osoby.db", null, 1);// nazwa pliku bazy, zmiana tej nazwy musi pociągnąć konsekwencje zmian w kodzie

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.inicjuj(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { // ta metoda musi być, chocby pusta, gdyz jest wymuszona przez dziedziczenie z SQLiteOpenHelper
    }

    private void inicjuj(SQLiteDatabase db)
    {
        db.execSQL(  //sformułowanie polecenia SQL tworzacego tabele w postaci sklejanego łańcucha znaków
                "create table osoby(" +
                        "nr integer primary key autoincrement," +
                        "imie text," +
                        "nazwisko text," +
                        "zajecie text);" + "");
    }

    public void czyscRekordy()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DROP TABLE IF EXISTS osoby "); //usuniecie poprzedniej zawartosci bazy,
        inicjuj(db); // ponowne zainicjowanie
    }

    public void dodajOsobe(Osoba osoba){ //metoda odczytująca z klasy osoba pola danych i wstawiająca te dane do bazy
        SQLiteDatabase db = getWritableDatabase();
        ContentValues zawartosc = new ContentValues();
        zawartosc.put("imie", osoba.getImie());
        zawartosc.put("nazwisko",osoba.getNazwisko());
        zawartosc.put("zajecie", osoba.getZajecie());
        db.insertOrThrow("osoby",null, zawartosc);
    }
    public void usunOsobe(int id){ //usuwanie jednego rekordu z bazy danych po numerze id
        SQLiteDatabase db = getWritableDatabase();
        String[] argumenty={""+id};
        db.delete("osoby", "nr=?", argumenty);
    }
    public void aktualizujOsobe(Osoba osoba){ // aktualizuje dane osoby oprócz nr
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("imie", osoba.getImie());
        wartosci.put("nazwisko",osoba.getNazwisko());
        wartosci.put("zajecie", osoba.getZajecie());
        String args[]={osoba.getNr()+""};
        db.update("osoby", wartosci,"nr=?",args);
    }

    public List<Osoba> pobierzWszystkich(){ //pobranie z bazy wszystkich rekordów jako listy
        List<Osoba> osoby = new LinkedList<Osoba>();
        String[] kolumny={"nr","imie","nazwisko","zajecie"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("osoby",kolumny,null,null,null,null,null); // ustawienie kursora na poczatku bazy danych z wybranym/sformatowanym dostepem do bazy
        while(kursor.moveToNext()){ // przemieszczanie kursora po kolejnych rekordach bazy danych i dodawanie danych jako obiektów klasy Osoba do listy
            Osoba osoba = new Osoba();
            osoba.setNr(kursor.getLong(0));
            osoba.setImie(kursor.getString(1));
            osoba.setNazwisko(kursor.getString(2));
            osoba.setZajecie(kursor.getString(3));
            osoby.add(osoba);
        }
        return osoby;
    }



    public List<Osoba> pobierzNazwiska(String nazwisko){ //pobranie danych z ustalonym polem nazwisko posortowanych według imion
        List<Osoba> osoby = new LinkedList<Osoba>();
        String[] kolumny={"nr","imie","nazwisko","zajecie"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.rawQuery("select nr,imie,nazwisko,zajecie from osoby where nazwisko='"
                +nazwisko+"' order by imie asc", null);
        // takie zapytanie SQL mozna sformułować na kilka sposobów
        while(kursor.moveToNext()){
            Osoba osoba = new Osoba();
            osoba.setNr(kursor.getLong(0));
            osoba.setImie(kursor.getString(1));
            osoba.setNazwisko(kursor.getString(2));
            osoba.setZajecie(kursor.getString(3));
            osoby.add(osoba);
        }
        return osoby;
    }

    public Osoba pobierzOsobe(int nr){
        Osoba osoba=new Osoba();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny={"nr","imie","nazwisko","zajecie"};
        String args[]={nr+""};
        Cursor kursor=db.query("osoby",kolumny," nr=?",args,null,null,null,null);
        if(kursor!=null){
            kursor.moveToFirst();
            osoba.setNr(kursor.getLong(0));
            osoba.setImie(kursor.getString(1));
            osoba.setNazwisko(kursor.getString(2));
            osoba.setZajecie(kursor.getString(3));
        }
        return osoba;
    }

}
