package com.jfvc.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Informacion extends AppCompatActivity {

    TextView numeroRep;
    TextView tiempo;
    String area;
    String tecnica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);


        //=======Recibe los datos pasados por la activity principal=====
        Bundle parametros = this.getIntent().getExtras();
        area = parametros.getString("area");
        tecnica = parametros.getString("tecnica");
        //==============================================================


        numeroRep = findViewById(R.id.etnum);
        tiempo = findViewById(R.id.ettiempo);


    }

    public void Aceptar(View v) {
        String numRep=(numeroRep.getText().toString());
        String minutos=(tiempo.getText().toString());

        Intent ejecucion = new Intent(v.getContext(),Ejecucion.class);
        ejecucion.putExtra("area",area);
        ejecucion.putExtra("tecnica",tecnica);
        ejecucion.putExtra("numrep",numRep);
        ejecucion.putExtra("tiempo",minutos);

        startActivity(ejecucion);
    }
}
