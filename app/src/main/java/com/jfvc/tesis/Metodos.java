package com.jfvc.tesis;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Metodos {
    //crea el archivo y el lector del archivo
    FileReader archivo;
    BufferedReader lector;

    //crea el escritor de archivos
    FileWriter archivow;
    BufferedWriter escritor;

    //constructor que recibe la ruta del archivo
    Metodos(String ruta) {
        try {
            archivo = new FileReader(ruta);
            archivow = new FileWriter(ruta);
        } catch (IOException e) {
            //si hay un error, manda un mensaje con el texto del error.
            //Toast t = Toast.makeText(, "Error de lectura/escritura del archivo:\n" + e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void inicializarTecnicas(String[][] Tecnicas) {
        //crea string para guardar la tecnica leida
        String tec;
        //inicializa el lector con el archivo ingresado
        lector = new BufferedReader(archivo);

        try {
            //lea cada linea del archivo hasta el final.
            while ((tec = lector.readLine()) != null) {

            }
        } catch (IOException e) {
            //si hay un error, manda un mensaje con el texto del error.
            Toast t = Toast.makeText(null, "Error al leer las tecnicas:\n" + e.getMessage(), Toast.LENGTH_LONG);
        }

    }

    public void crearArchivo() {
        escritor=new BufferedWriter(archivow);

        try{
            escritor.write("texto");
        }catch (IOException e){
            Toast t = Toast.makeText(null, "Error al escribir el archivo:\n" + e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
