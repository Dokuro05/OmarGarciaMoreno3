package com.example.omargarciamoreno3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Partida{

    String palabra;

    public Partida(String palabra) {
        this.palabra = palabra;
    }



}

public class MainActivity extends AppCompatActivity {

    ArrayList<String> palabras;
            //{"memoria", "oceano", "montaña", "carro"};
    String palabraGenerada="";
    StringBuilder pista;
    int contador;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        palabras=new ArrayList<>();
        palabras.add("memoria");
        palabras.add("oceano");
        palabras.add("montaña");
        palabras.add("carro");

    }

    @Override public boolean onCreateOptionsMenu(Menu mimenu) {
        getMenuInflater().inflate(R.menu.menu, mimenu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id=opcion_menu.getItemId();
        if(id==R.id.verpalabra){

            if(palabraGenerada!=""){
                Toast.makeText(this, palabraGenerada, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No hay ninguna palabra", Toast.LENGTH_SHORT).show();
            }

        }
        if(id==R.id.agregarpalabra){

            agregarPalabra(null);



        }
        if(id==R.id.mostrarpalabras){

            Intent intent = new Intent(MainActivity.this, MostrarPalabras.class);
            int contador=0;
            intent.putExtra("palabras", palabras);
            startActivity(intent);
        }
        if(id==R.id.salir){
            finish();
        }
        return super.onOptionsItemSelected(opcion_menu);
    }

    public void generarPalabra(View view){
        contador=5;
        int aleatorio=(int) (Math.random()*palabras.size());
        palabraGenerada=palabras.get(aleatorio);
        pista= new StringBuilder("");
        for(int j=0;j<palabraGenerada.length();j++){
            pista.append("_");
        }
        TextView mostrar = (TextView) findViewById(R.id.palabra);
        mostrar.setText(pista);
        TextView vidas=(TextView) findViewById(R.id.intentos);
        vidas.setText("Intentos: " + contador);
    }

    public void adivinarLetra(View view){

        EditText letraIntroducida=(EditText) findViewById(R.id.letra);
        String letra= letraIntroducida.getText().toString();

        boolean encontrado=false;

        for(int i=0;i<palabraGenerada.length();i++){
            String coincidencia=Character.toString(palabraGenerada.charAt(i));
            if(letra.equals(coincidencia)){
                encontrado=true;
                pista.setCharAt(i,letra.charAt(0));
            }
        }
        TextView mostrar = (TextView) findViewById(R.id.palabra);
        mostrar.setText(pista);
        if (encontrado==false){
            contador--;
            TextView vidas=(TextView) findViewById(R.id.intentos);
            vidas.setText("Intentos: " + contador);
        }
        encontrado=false;
        if(contador==0){
            derrota(view);
        }

        String comprobar= String.valueOf(pista);
        if(!comprobar.contains("_")){
            victoria(view);
        }
    }

    public AlertDialog derrota(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Derrota")
                .setMessage("¡Has perdido!")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        builder.show();
        return builder.create();

    }

    public AlertDialog victoria(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Victoria")
                .setMessage("¡Has ganado!")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        builder.show();
        return builder.create();

    }

    public AlertDialog agregarPalabra(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.alertdialog, null))
                .setTitle("Añade una palabra")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText textoResultado=(EditText) findViewById(R.id.palabranueva);
                        String nueva=textoResultado.toString();
                        palabras.add(nueva);
                    }
                });

        builder.show();
        return builder.create();
    }

}