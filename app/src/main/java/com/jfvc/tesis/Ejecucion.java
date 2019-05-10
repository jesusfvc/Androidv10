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

        time =0;
        tarea=new Thread();

        tvnumerr.setText("Numero de errores: 0");
        tvtiempo.setText("Tiempo: 0 segundos");


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores;

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
                default:
                    sensores.setText("");
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
