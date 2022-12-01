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
        Jugador j1 = new Jugador("Santiago");
        Jugador j2 = new Jugador("Izan");

        Partida p1 = new Partida(j1, j2, 5);

        Palabra pa1 = new Palabra("cinco");
        Palabra pa2 = new Palabra("tapas");
        Palabra pa3 = new Palabra("patas");
        Palabra pa4 = new Palabra("aereo");
        Palabra pa5 = new Palabra("puros");

        p1. jugarPartida(j2, pa1);
        p1. jugarPartida(j2, pa2);
        p1. jugarPartida(j2, pa3);
        p1. jugarPartida(j2, pa4);
        p1. jugarPartida(j2, pa5);

    }
    
}