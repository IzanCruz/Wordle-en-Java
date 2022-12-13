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
    
    public String obtenerPalabra(String nombre){
        int aleatorio = (int) (Math.random()*4896+1);
        int i = 0;
        String texto = null;
        try {
            BufferedReader br = 
            new BufferedReader(new FileReader((nombre)));
            do {
                texto = br.readLine();                
                i++;
            } while (i <= aleatorio);
            System.out.println(texto);
            return texto;               
        } catch (IOException e) {
            System.err.println("No se ha encontrado el archivo");
        }
        return null;
    }
}
    

