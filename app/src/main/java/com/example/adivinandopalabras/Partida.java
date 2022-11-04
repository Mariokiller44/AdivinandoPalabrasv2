package com.example.adivinandopalabras;


import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Partida extends AppCompatActivity {

    private ArrayList<String> palabras;
    private int numIntentos;
    private int posicionLetra;
    private int indice;
    private String palabraSeleccionada;
    private char[] letrasDisponibles;
    private boolean[] posicionesOcupadas;
    private boolean finPartida;
    private String palabra;

    public Partida() {
      this.numIntentos=5;
      this.palabras= new ArrayList<>();
      cargaPalabras();
      this.palabraSeleccionada="";
      palabraSeleccionada=getPalabraSeleccionada();
      this.finPartida=false;
      this.posicionesOcupadas = new boolean[palabraSeleccionada.length()];
      posicionesOcupadas=getPosicionesOcupadas();
      this.letrasDisponibles = new char[palabraSeleccionada.length()];
      letrasDisponibles=getLetrasDisponibles();
      this.palabra="";
    }
    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void cargaPalabras(){
        palabras.add("Mente");
        palabras.add("Inteligencia");
        palabras.add("Sabiduria");
        palabras.add("Capacidad");
    }

    public void setPalabras(ArrayList palabras) {
        this.palabras = palabras;
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(int numIntentos) {
        this.numIntentos = numIntentos;
    }

    public int getPosicionLetra() {
        return posicionLetra;
    }

    public void setPosicionLetra(int posicionLetra) {
        this.posicionLetra = posicionLetra;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getPalabra() {
        if (palabra!=""){
            palabra="";
        }
        return mostrarPalabra();
    }

    public void setPalabra(String palabra1) {
        this.palabra = palabra1;
    }

    public char[] getLetrasDisponibles() {
        letrasDisponibles=getPalabraSeleccionada().toCharArray();

        return letrasDisponibles;
    }

    public void setLetrasDisponibles(char[] letrasDisponibles) {
        this.letrasDisponibles = letrasDisponibles;
    }

    public boolean[] getPosicionesOcupadas() {
        for (int i=0;i<palabraSeleccionada.length();i++){
            posicionesOcupadas[i]=false;
        }
        return posicionesOcupadas;
    }

    public void setPosicionesOcupadas(boolean[] posicionesOcupadas) {
        this.posicionesOcupadas = posicionesOcupadas;
    }

    public int posicionPalabraAleatoria(){
        int posicion=(int) Math.floor(Math.random()*palabras.size());
        return posicion;
    }

    public String getPalabraSeleccionada() {
        if (palabraSeleccionada==""){
            palabraSeleccionada=palabras.get(posicionPalabraAleatoria()).toString();
        }
        return palabraSeleccionada;
    }

    public void setPalabraSeleccionada(String palabraMuestra) {
        this.palabraSeleccionada = palabraMuestra;
    }
    public void setFinPartida(boolean finPartida) {
        this.finPartida = finPartida;
    }
    public String mostrarPalabra(){
        for (int i = 0; i < posicionesOcupadas.length; i++) {
            if (i == posicionesOcupadas.length) {
                break;
            }
            if (posicionesOcupadas[i] == false) {
                char guion = '_';
                palabra = palabra.concat(guion + " ");
            } else {
                palabra = palabra.concat(letrasDisponibles[i] + " ");
            }
        }
        return palabra;
    }


    public void adivinar(char letra) {
        if (palabraSeleccionada.contains(String.valueOf(Character.toLowerCase(letra)))|| palabraSeleccionada.contains(String.valueOf(Character.toUpperCase(letra)))){
            for (int i = 0; i < letrasDisponibles.length; i++) {
                if (letrasDisponibles[i] == Character.toLowerCase(letra) || letrasDisponibles[i] == Character.toUpperCase(letra)) {
                    posicionesOcupadas[i] = true;
                }
            }
        }else{
            numIntentos--;
        }
    }
    public boolean finPartida(String palabra){
        String palabraSinEspacio=palabra.replace(" ","");
        if (palabraSeleccionada.equals(palabraSinEspacio)){
            finPartida=true;
        }else{
            finPartida=false;
        }
        return finPartida;
    }
    public boolean finPartida(int intentos){
        if (intentos==0){
            finPartida=true;
        }else{
            finPartida=false;
        }
        return finPartida;
    }
}