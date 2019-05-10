package com.jfvc.tesis;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Ejecucion extends AppCompatActivity implements SensorEventListener {
    String area;
    String tecnica;
    String numRep;
    String tiempo;
    TextView sensores;
    TextView tvtiempo;
    long time;
    Thread tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejecucion);

        Bundle params= getIntent().getExtras();

        area=params.getString("area");
        tecnica=params.getString("tecnica");
        numRep=params.getString("numrep");
        tiempo=params.getString("tiempo");


        TextView tvnumerr= findViewById(R.id.tvnumerr);
         tvtiempo= findViewById(R.id.tvtiempo);
        sensores= findViewById(R.id.sensores);


        //SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss", Locale.getDefault());
        //Date date = new Date();
        //String fecha = dateFormat.format(date);
        //time=System.currentTimeMillis();

        time =0;
        tarea=new Thread();

        tvnumerr.setText("Numero de errores: 0");
        tvtiempo.setText("Tiempo: 0 segundos");


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores;

        // = sensorManager.getSensorList(Sensor.TYPE_ALL);

        /*for(Sensor sensor: listaSensores) {

            log(sensor.getName());

        }*/


        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if (!listaSensores.isEmpty()) {

            Sensor orientationSensor = listaSensores.get(0);

            sensorManager.registerListener(this,orientationSensor,sensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {

            Sensor acelerometerSensor = listaSensores.get(0);

            sensorManager.registerListener(this, acelerometerSensor, SensorManager.SENSOR_DELAY_UI);}

        /*listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

        if (!listaSensores.isEmpty()) {

            Sensor magneticSensor = listaSensores.get(0);

            sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);

        if (!listaSensores.isEmpty()) {

            Sensor temperatureSensor = listaSensores.get(0);

            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_UI);}*/
    }



    private void log(String string) {

        sensores.append(string + "\n");

    }

    @Override
    public void onSensorChanged(SensorEvent evento) {
        long actual = System.currentTimeMillis() - time;
        long segundos= (actual/1000)%60;
        long minutos= (actual/1000/60)%60;
        long horas= actual/1000/60/60;

        tvtiempo.setText("Tiempo: "+ horas+" horas "+minutos+" minutos "+segundos + " segundos");
        //Cada sensor puede provocar que un thread principal pase por aquí

        //así que sincronizamos el acceso

        synchronized (this) {

            sensores.setText("");

            switch(evento.sensor.getType()) {

                case Sensor.TYPE_ORIENTATION:

                    for (int i=0 ; i<3 ; i++) {

                        log("Orientación "+i+": "+evento.values[i]);

                    }

                    break;

                case Sensor.TYPE_ACCELEROMETER:

                    for (int i=0 ; i<3 ; i++) {

                        log("Acelerómetro "+i+": "+evento.values[i]);

                    }

                    break;

                /*case Sensor.TYPE_MAGNETIC_FIELD:

                    for (int i=0 ; i<3 ; i++) {

                       // log("Magnetismo "+i+": "+evento.values[i]);

                    }

                    break;
                    */
                default:

                    //for (int i=0 ; i<evento.values.length ; i++) {

                       // log("Temperatura "+i+": "+evento.values[i]);

                    //}
                    sensores.setText("");
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
