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
    //Atributes
    private int numPalabras;
    private Jugador jugador1, jugador2;
    private int ganadosJug1, ganadosJug2;
    private PartidaPalabra[] listaPalabras;
    private final static int MAXPALABRAS = 10;
    //Constructores
    public Partida(Jugador j1, Jugador j2) {
        this(j1);
        if (j2 != null)
            jugador2 = j2;
    }    
    
    public Partida(Jugador j) {
        listaPalabras = new PartidaPalabra[MAXPALABRAS];
        numPalabras = -1; 
        if (j != null)
            jugador1 = j;
    }

    //Get Set
    public Jugador getJugador1(){
        return jugador1;
    }

    public Jugador getJugador2(){
        return jugador2;
    }

    public int getNumPalabras(){
        return numPalabras;
    }

    public int getGanadosJug1(){
        return ganadosJug1;
    }

    public int getGanadosJug2(){
        return ganadosJug2;
    }

    public PartidaPalabra [] getListaPalabras(){
        return listaPalabras;
    }

    public void setNumPalabras(int n){
        numPalabras = n;
    }

    public void setGanadosJug1(int n){
        ganadosJug1 = n;
    }

    public void setGanadosJug2(int n){
        ganadosJug2 = n;
    }
    
    //Metodos
    public void crearPartida(Palabra p) {
        crearIndividual(jugador1, p);
        crearIndividual(jugador2, p);
    }
    
    private void crearIndividual(Jugador j, Palabra p) {
        getListaPalabras()[(getNumPalabras() + 1)] = new PartidaPalabra(j,p);
        
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Partida p = (Partida) o;
        return ((getJugador1().equals(p.getJugador1())) && (getJugador2().equals(p.getJugador2())) && (getNumPalabras() == p.getNumPalabras()) && (getGanadosJug1() == p.getGanadosJug1()) && (getGanadosJug2() == p.getGanadosJug2()) && (getListaPalabras().equals(p.getListaPalabras())));
    }
    
}