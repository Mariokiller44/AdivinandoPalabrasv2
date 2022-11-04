package com.example.adivinandopalabras;

import static android.widget.TextView.BufferType.NORMAL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

 /*   public String[] palabras = {"Memoria", "Inteligencia", "Capacidad"};
    int intentos = 5;
    int posi = (int) Math.floor(Math.random() * palabras.length);
    String palabraNueva = palabras[posi];
    char[] caracteres = palabraNueva.toCharArray();
    public char guiones[] = new char[caracteres.length];
    public boolean[] comprobar = new boolean[caracteres.length];
    String nuevaP;
    TextView palabra;
*/
    Partida p = new Partida();
    TextView mostrarPalabra;
    TextView mostrarIntentos;
    Context contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //      mostrarJuego();
          mostrarIntentos();
          mostrarPalabra();
    }

    public void mostrarIntentos(){
        int intentos = p.getNumIntentos();
        String palabraCorrecta = p.getPalabraSeleccionada();
        if (p.finPartida(intentos)==true){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mala Suerte")
                    .setMessage("Has fallado; la palabra era: "+palabraCorrecta)
                    .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            p.setNumIntentos(5);
        }
        mostrarIntentos= (TextView) findViewById(R.id.intentos);
        mostrarIntentos.setText("Intentos: "+intentos);
//
    }

    public void mostrarPalabra(){
        String palabra = p.getPalabra();
        if(p.finPartida(palabra)==true){
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Enhorabuena")
                        .setMessage("Has acertado la palabra; felicitaciones!")
                        .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create();
                builder.show();
        }
        mostrarPalabra = (TextView)findViewById(R.id.palabra);
        mostrarPalabra.setText(palabra);
    }

    public void adivinar(View v){
          TextView letras =(TextView) findViewById(R.id.letra);
          if (letras.length()==0){
              Toast.makeText(this,"Introduzca letra",Toast.LENGTH_SHORT).show();
          }else {
              char letra = letras.getText().charAt(0);
              p.adivinar(letra);
              letras.setText("");
              int intentos = p.getNumIntentos();
              String palabraSeleccionada = p.getPalabraSeleccionada();
              String palabra = p.getPalabra();
              mostrarIntentos();
              mostrarPalabra();
          }
    }

    public void cambiarPalabra(View v){
          p.setPalabra("");
          p.setPalabraSeleccionada("");
          p.getPalabraSeleccionada();
          mostrarPalabra();
    }


/*    public void mostrarJuego() {
        mostrarIntentos();
        mostrarNuevaPalabra();
    }

    public void cambiarPalabra(View v){
            Button boton = (Button) findViewById(R.id.nuevo);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cambiarPalabraNueva();
                }
            });
    }
    public void cambiarPalabraNueva(){
                intentos=5;
                int nuevaWord= (int) Math.floor(Math.random()*palabras.length);
                palabraNueva= palabras[nuevaWord];
                caracteres=new char[]{};
                caracteres=palabraNueva.toCharArray();
                comprobar=new boolean[caracteres.length];
                mostrarIntentos();
                mostrarNuevaPalabra();
    }


    public void mostrarNuevaPalabra() {
        palabra = (TextView) findViewById(R.id.palabra);
        nuevaP = "";
        if (comprobar.toString().isEmpty()){
            cargaBuleanos();
        }else {
            for (int i = 0; i < palabraNueva.length(); i++) {
                if (comprobar[i] == false) {
                    char guion = '_';
                    nuevaP = nuevaP.concat(guion + " ");
                } else {
                    nuevaP = nuevaP.concat(caracteres[i] + " ");
                }
            }
            if (nuevaP.contains(String.valueOf('_'))==false){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Enhorabuena");
                builder.setMessage("Has acertado la palabra");
                builder.setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create();
                builder.show();
            }
        }
        palabra.setText(nuevaP);
    }

    public void cargaBuleanos() {
        for (int i=0;i<comprobar.length;i++){
            comprobar[i]=false;
        }
    }

    public void mostrarIntentos () {
        TextView textoIntentos = (TextView) findViewById(R.id.intentos);
        textoIntentos.setText("Intentos: " + intentos);
    }
    public void mostrarCaracter(View v) {
        TextView letraRecoger = (TextView) findViewById(R.id.letra);
        if (letraRecoger.length() == 0){
            Toast.makeText(this,"Letra por favor",Toast.LENGTH_SHORT).show();
        }else {
            char letra = Character.toUpperCase(letraRecoger.getText().charAt(0));
            if (existe(letra, palabraNueva) != false) {
                for (int i = 0; i < caracteres.length; i++) {
                    if (Character.toUpperCase(caracteres[i]) == letra) {
                        comprobar[i] = true;
                    }
                }
                letraRecoger.setText("");
                mostrarNuevaPalabra();
            } else {
                letraRecoger.setText("");
                intentos--;
                mostrarIntentos();
            }
            if (intentos == 0) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Vuelve a intentarlo");
                builder1.setMessage("No has acertado la palabra; la palabra era " + palabraNueva);
                builder1.setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intentos = 5;
                        cambiarPalabraNueva();
                        mostrarJuego();
                        dialog.dismiss();
                    }
                });
                builder1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder1.create();
                builder1.show();
            }
        }
    }

    private boolean existe(char letra, String palabraNueva) {
        boolean salida=false;
        if (palabraNueva.contains(String.valueOf(letra))){
            salida=true;
        }else if(palabraNueva.contains(String.valueOf(Character.toLowerCase(letra)))){
            salida=true;
        }
        return salida;
    }*/
}