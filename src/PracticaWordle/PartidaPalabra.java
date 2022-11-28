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
public class PartidaPalabra {
    //Atributes
    private Jugador jugador;    
    private boolean ganada;
    private Palabra palabraOculta;
    private Intento intento;
    private int puntos;
 

    //Constructors
    public PartidaPalabra(Jugador j, Palabra p) {
        if (j!= null) jugador = j;
        if (p != null) palabraOculta = p;        
    }
        
    //Methods           
    public Jugador getJugador() {
        return jugador;
    }

    public boolean isGanada() {
        return ganada;
    }

    public Palabra getPalabra() {
        return palabraOculta;
    }

    public Intento getIntento() {
        return intento;
    }
    
    //Methods
    
}