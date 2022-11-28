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
public class Partida {
    // Atributes
    private int numPalabras; // Numero de palabras que se juega
    private Jugador jugador1, jugador2;
    private int ganadosJug1, ganadosJug2;
    private PartidaPalabra[][] listaPPalabras;
    private final static int MAXPALABRAS = 10;
    private final static int NUMJUGADORES = 2;
    private int puntosJug1, puntosJug2;
    private int cont1, cont2;

    // Constructores
    public Partida(Jugador j1, Jugador j2, int n) { // Modo multijugador
        this(j1, n);
        if (j2 != null)
            jugador2 = j2;
    }

    public Partida(Jugador j, int n) { // Modo entrenamiento
        listaPPalabras = new PartidaPalabra[NUMJUGADORES][MAXPALABRAS];
        numPalabras = n;
        if (j != null)
            jugador1 = j;
    }

    // Getters
    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public int getGanadosJug1() {
        return ganadosJug1;
    }

    public int getGanadosJug2() {
        return ganadosJug2;
    }

    public PartidaPalabra[][] getListaPalabras() {
        return listaPPalabras;
    }

    public void setNumPalabras(int n) {
        numPalabras = n;
    }

    public void setGanadosJug1(int n) {
        ganadosJug1 = n;
    }

    public void setGanadosJug2(int n) {
        ganadosJug2 = n;
    }

    // Resto de Metodos
    public void crearPartida(Jugador j, Palabra p) {
        if (j.equals(jugador1) && (cont1 < MAXPALABRAS -1)) 
            listaPPalabras[0][(cont1++)] = new PartidaPalabra(j, p);
        else if (j.equals(jugador2) && (cont2 < MAXPALABRAS -1))
            listaPPalabras[1][(cont2++)] = new PartidaPalabra(j, p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Partida p = (Partida) o;
        return ((getJugador1().equals(p.getJugador1())) && (getJugador2().equals(p.getJugador2())) &&
                (getNumPalabras() == p.getNumPalabras()) && (getGanadosJug1() == p.getGanadosJug1()) &&
                (getGanadosJug2() == p.getGanadosJug2()) && (getListaPalabras().equals(p.getListaPalabras())));
    }

}