package com.example.listaobecnoci;

public class Osoba {

    private Long nr;
    private String imie;
    private String nazwisko;
    private String zajecie;

    public Osoba() {
    }

    public Osoba(String imie, String nazwisko, String zajecie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.zajecie = zajecie;
    }

    public Long getNr() {
        return nr;
    }

    public void setNr(Long nr) {
        this.nr = nr;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getZajecie() {
        return zajecie;
    }

    public void setZajecie(String zajecie) {
        this.zajecie = zajecie;
    }

}
