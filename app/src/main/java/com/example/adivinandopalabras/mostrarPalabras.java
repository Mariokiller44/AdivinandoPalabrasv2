package com.example.adivinandopalabras;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class mostrarPalabras extends Activity {

    Partida p = new Partida();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muestra_palabras);
        muestraLista();
    }
    public void muestraLista (){
        ArrayList<String> palabras;
        Bundle datos = getIntent().getExtras();
        palabras=datos.getStringArrayList("listaPalabras");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,palabras);
        ListView listView = (ListView) findViewById(R.id.listaPalabras);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri wikipedia = Uri.parse("https://es.wikipedia.org/wiki/Wikipedia:Portada");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, wikipedia);
                startActivity(webIntent);
            }
        });
    }
    public void salirMostrarPalabras(View v){
        finish();
    }

}
