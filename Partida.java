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
    private int ganadosJug1;
    private int ganadosJug2;
    private final static int MAX_PALABRAS = 10; 
    private PartidaPalabra[] ListaPalabras;  
    
    //Constructors
    public Partida(Jugador j1, Jugador j2) {
        this(j1);
        if (j2 != null) jugador2=j2;
    }    
    
    public Partida(Jugador j) {
        ListaPalabras = new PartidaPalabra[10]; 
        if (j != null) jugador1=j;
    }
    
    //Methods
    public void crearPartida(Palabra p) {
        crearIndividual(jugador1, p);
        crearIndividual(jugador2, p);
    }
    
    private void crearIndividual(Jugador j, Palabra p) {
        ListaPalabras[numPalabras++]= new PartidaPalabra(j,p);
        
    }
    
}
