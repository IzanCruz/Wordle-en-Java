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

        Jugador j1 = new Jugador("Santiago");
        Jugador j2 = new Jugador("David");

        
        w.registrarJugador(j1);
        w.registrarJugador(j2);

        //w.iniciarPartida("David", "Santiago", 1);
        w.iniciarPartida(j1, j2, 1);

        System.out.println(w.getListaPartidas().get(0).toString());

        /*PartidaPalabra [][] aux = w.getListaPartidas().get(0).getListaPPalabras();
        for (int j = 0; j<=1; j++){    
            for(int i = 0; i<=w.getListaPartidas().get(0).getNumPalabras()-1; i++){
                System.out.println(aux[j][i].getPalabraOculta().toString());
            }
        }*/
    }
    
}