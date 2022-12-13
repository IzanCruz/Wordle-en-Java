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
    
    public String obtenerPalabra(){
        int aleatorio = (int) (Math.random()*4896+1);
        int i = 0;
        String palabra = null;
        try {            
            do {
                palabra = br.readLine();                
                i++;
            } while (i <= aleatorio);
            return palabra;               
        } catch (IOException e) {
            System.err.println("No se ha encontrado el archivo");
        }
        return null;
    }

    public void cerrarLector(){
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Error: No se ha podido cerrar el lector. " + e.getMessage());
        }
    }
}
    

