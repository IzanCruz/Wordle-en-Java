/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Prueba;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
public class Prueba {           

    public static void main(String[] args) {
        
       try {
            BufferedReader br = 
            new BufferedReader(new FileReader("C:/Users/santi/OneDrive - Universidad Rey Juan Carlos/3º Curso/POO/PracticaWordle/PalabrasWordleDefinitivo.txt"));
            String texto = br.readLine();
            while(texto != null) {
                System.out.println(texto);
                texto = br.readLine();
            }                
        } catch (IOException e) {
            System.err.println("No se ha encontrado el archivo");
        }
    }
    
}
