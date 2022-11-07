package com.example.adivinandopalabras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Partida p = new Partida();
    TextView mostrarPalabra;
    TextView mostrarIntentos;
    Context contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          mostrarIntentos();
          mostrarPalabra();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            //Ejercicio 1
            case R.id.verPalabra:
                String palabraEscogida = p.getPalabraSeleccionada();
                Toast.makeText(this,"La palabra es: "+palabraEscogida,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aniadirNuevaPalabra:
                ArrayList<String> palabras = p.getPalabras();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Nueva Palabra")
                        .setMessage("Introduzca nueva palabra");
                final EditText palabra = new EditText(MainActivity.this);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                palabra.setLayoutParams(layout);
                builder.setView(palabra);
                builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        palabras.add(palabra.getText().toString());
                        p.setPalabras(palabras);
                        Toast.makeText(getApplicationContext(),"Correcta insercion",Toast.LENGTH_SHORT+1).show();
                    }
                });
                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create();
                builder.show();
                return true;
            case R.id.MuestraListaPalabras:
                Intent i = new Intent(this, mostrarPalabras.class);
                ArrayList<String> listaPalabras = p.getPalabras();
                i.putStringArrayListExtra("listaPalabras",listaPalabras);
                startActivity(i);
                return true;
                //Ejercicio 4
            case R.id.salir:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            builder.create();
            builder.show();
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
                                cambiarPalabra();
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

    public void cambiarPalabra() {
        p.setNumIntentos(5);
        p.setPalabra("");
        p.setLetrasDisponibles(new char[]{});
        p.setPalabraSeleccionada("");
        p.getLetrasDisponibles();
        p.setPosicionesOcupadas(new boolean[p.getPalabraSeleccionada().length()]);
        p.getPalabraSeleccionada();
        mostrarIntentos();
        mostrarPalabra();
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
          p.setNumIntentos(5);
          p.setPalabra("");
          p.setLetrasDisponibles(new char[]{});
          p.setPalabraSeleccionada("");
          p.getLetrasDisponibles();
          p.setPosicionesOcupadas(new boolean[p.getPalabraSeleccionada().length()]);
          p.getPalabraSeleccionada();
          mostrarIntentos();
          mostrarPalabra();
    }

}