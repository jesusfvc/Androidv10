package com.jfvc.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    // area = parte del cuerpo
    //tecnicas= tecnicas disponibles
    private String[][] tecnicas = new String[3][4];

    private String area = "Piernas";
    private String tecnica="Articulación del codo";

    Metodos metodo = new Metodos("txt.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tecnicas[0][0] = "Articulación de la rodilla";
        tecnicas[0][1] = "90"; //rotacion / flexion  inicial
        tecnicas[0][2] = "135";//Flexion
        tecnicas[0][3] = "-1"; //distancia -1= sin limite

        tecnicas[1][0]= "Articulación del codo";
        tecnicas[1][1]="140";
        tecnicas[1][2]="140";
        tecnicas[1][3] = "-1";


        Spinner spareas = findViewById(R.id.spArea);//se crea el objeto spinner.
        final Spinner sptecnicas = findViewById(R.id.spTecnica);//objeto spinner para tecnicas


        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spareas.setAdapter(adaptador);

        //añade el evento al seleccionar un elemento del spinner
        spareas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //crea un adaptador para obtener valores de las tecnicas disponibles del archivo strings.xml
                ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(parent.getContext(), R.array.Tbrazos, android.R.layout.simple_spinner_item);
                //obtiene el elemento seleccionado y lo guarda en el string area
                area = parent.getItemAtPosition(position).toString();
                //determina que valores obtendra dependiendo del valor seleccionado en el spinner
                if (area.equals("Brazos")) {
                    adaptador=  ArrayAdapter.createFromResource(parent.getContext(), R.array.Tbrazos, android.R.layout.simple_spinner_item);
                }else if(area.equals("Piernas")){
                    adaptador=  ArrayAdapter.createFromResource(parent.getContext(), R.array.Tpiernas, android.R.layout.simple_spinner_item);
                }
                //convierte los datos a una vista que reconosca el spinner
                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //se asignan los valores de las tecnicas al spinner
                sptecnicas.setAdapter(adaptador);
            }

            //si no se selecciona ningun valor se manda por defecto el area "manos"
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                area = "Brazos";
            }
        });

        sptecnicas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tecnica = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tecnica = "Articulacion del codo";
            }
        });
    }

    public void Aceptar(View v){
        Intent actividad = new Intent(v.getContext(),Informacion.class);
        actividad.putExtra("area",area);
        actividad.putExtra("tecnica",tecnica);

        startActivityForResult(actividad,0);
    }
}
