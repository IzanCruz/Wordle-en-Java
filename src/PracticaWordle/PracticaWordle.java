/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

import PracticaWordle.Exepciones.JugadorExcepcion;

/**
 *
 * @author USER
 */
public class PracticaWordle {

    /**
     * @param args the command line arguments
     * @throws JugadorExcepcion
     */
    public static void main(String[] args) throws JugadorExcepcion {
        
        
        Wordle w = new Wordle();

        OperacionesFicheros f = new OperacionesFicheros();

        
        f.leerJugadores(w);
        f.leerPartidas(w);

        
        f.guardarJugadores(w);
        f.guardarPartidas(w);

    }
    
}