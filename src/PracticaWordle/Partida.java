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
    private Jugador jugador1, jugador2, ganador;
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

    public Jugador getGanador() {
        return ganador;
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

    public PartidaPalabra[][] getListaPPalabras() {
        return listaPPalabras;
    }

    public int getPuntosJ1(){
        return puntosJug1;
    }

    public int getPuntosJ2(){
        return puntosJug2;
    }

    public int getCont1(){
        return cont1;
    }

    public int getCont2(){
        return cont2;
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

    public void setPuntosJ1(int n){
        puntosJug1 = n;
    }

    public void setPuntosJ2(int n){
        puntosJug2 = n;
    }

    public void setCont1(int n){
        cont1 = n;
    }

    public void setCont2(int n){
        cont2 = n;
    }

    public void setGanador(Jugador g) {
        ganador = g;
    }

    // Methods
    public void crearPartida(Jugador j, Palabra p) {
        if (j.equals(getJugador1()) && (getCont1() < numPalabras-1)){ 
            PartidaPalabra nueva = new PartidaPalabra(j, p); //Se crea para jugador1
            if (!partidaEncontrada(0, nueva))            
                listaPPalabras[0][cont1++] = nueva;
        }
        else if (j.equals(getJugador2()) && (getCont2() < numPalabras-1)){
            PartidaPalabra nueva = new PartidaPalabra(j, p); //Se crea para jugador2
            if (!partidaEncontrada(1, nueva))            
                listaPPalabras[1][cont2++] = nueva;
            
        }
    }    

    private boolean partidaEncontrada(int n, PartidaPalabra nueva) {
        boolean encontrado = false;
        int i=0;
        while(i < getCont1()-1 && !encontrado){
            encontrado = nueva.equals(listaPPalabras[n][i]);
            if (!encontrado)
            i++;
        }

        return encontrado;        
    }

    public void jugarPartida(PartidaPalabra partida) {
        partida.resolver();
        setPuntosJ1(getPuntosJ1()+ partida.getPuntos());
        if (partida.isGanada())
            setGanadosJug1(getGanadosJug1()+1);        
    }

    public void elegirGanador() {
        if (jugador1.getGanadas() > jugador2.getGanadas())        
            setGanador(jugador1);
        else setGanador(jugador2);
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
                (getGanadosJug2() == p.getGanadosJug2()) && (getListaPPalabras().equals(p.getListaPPalabras())));
    }

}