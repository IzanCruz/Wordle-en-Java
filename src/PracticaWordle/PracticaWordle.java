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
        Wordle w = new Wordle();
        
        w.registrarJugador("Santiago");
        w.registrarJugador("David");

        w.iniciarPartida("David", "Santiago", 2);
    }
    
}