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
<<<<<<< Updated upstream
=======
    private static final int NLINEAS = 990;
    private static final String FICHEROPALABRAS = "PalabrasWordleDefinitivo.txt";    
>>>>>>> Stashed changes
    
    //Constructors
    public OperacionesFicheros() {}
    
    public BufferedReader abrirLector(String nombre) {        
        try {
            br = new BufferedReader(new FileReader((nombre)));
            return br;
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado." + e.getMessage());            
        }
        return null;
    }
    
<<<<<<< Updated upstream
    public String obtenerPalabra(String n){
        abrirLector(n);
        int aleatorio = (int) (Math.random()*990+1);
        int i = 0;
        String palabra = null;
        try {            
            do {
                palabra = br.readLine();                
                i++;
            } while (i <= aleatorio);
            cerrarLector();
            return palabra;               
        } catch (IOException e) {
            System.err.println("No se ha encontrado el archivo");
        }
=======
    public String[] obtenerPalabras(){    
        String[] palabras = new String[NLINEAS];
        abrirLector();            
        try { 
            for(int i = 0; i < NLINEAS; i++) {
              palabras[0] = br.readLine();
            }        
        } catch (IOException ex) {
            System.err.println("Error al leer el fichero.");
        }        
>>>>>>> Stashed changes
        cerrarLector();
        return null;
    }

    public void cerrarLector(){
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Error: No se ha podido cerrar el lector. " + e.getMessage());
        }
    }
<<<<<<< Updated upstream
=======

    public int getNLINEAS() {
        return NLINEAS;
    }

    
>>>>>>> Stashed changes
}
    

