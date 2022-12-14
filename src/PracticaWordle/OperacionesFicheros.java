/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaWordle;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class OperacionesFicheros {    
    
    //Atributes
    private BufferedReader br;
    private static final int NUM_PALABRAS = 990;
    private static final String FICHEROPALABRAS = "PalabrasWordleDefinitivo.txt";    
    
    //Constructors
    public OperacionesFicheros() {}
    
    private void abrirLector() {        
        try {
            br = new BufferedReader(new FileReader((FICHEROPALABRAS)));            
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado." + e.getMessage());            
        }
    }
    
    public String[] obtenerPalabras(){    
        String[] palabras = new String[NUM_PALABRAS];
        abrirLector();            
        try { 
            for(int i = 0; i < NUM_PALABRAS; i++) {
              palabras[0] = br.readLine();
            }        
        } catch (IOException ex) {
            System.err.println("Error al leer el fichero.");
        }        
        cerrarLector();
        return palabras;
    }

    private void cerrarLector(){
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Error: No se ha podido cerrar el lector. " + e.getMessage());
        }
    }

    public int getNumPalanbrasFich() {
        return NUM_PALABRAS;
    }

    
}
    

