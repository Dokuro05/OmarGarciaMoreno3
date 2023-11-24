package com.example.omargarciamoreno3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MostrarPalabras extends Activity {

    ArrayList<String> palabras;

    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palabras);

        Bundle datos = getIntent().getExtras();
        int longitud=datos.getInt("longitud");

        palabras= (ArrayList<String>) getIntent().getSerializableExtra("palabras");

        listView=(ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, palabras);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MostrarPalabras.this, VisorWeb.class);
                startActivity(intent);
            }
        });

    }

    public void salir(){
        finish();
    }

}
