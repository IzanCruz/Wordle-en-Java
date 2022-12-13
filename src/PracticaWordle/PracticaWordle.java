/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

/**
 *
 * @author USER
 */
public class PracticaWordle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OperacionesFicheros of = new OperacionesFicheros();
        of.obtenerPalabra("C:/Users/santi/OneDrive - Universidad Rey Juan Carlos/3º Curso/POO/PracticaWordle/PalabrasWordleDefinitivo.txt");
        
        Wordle w = new Wordle();
        
        w.registrarJugador("Santiago");
        w.registrarJugador("David");

        w.iniciarPartida("David", "Santiago", 2);
    }
    
}