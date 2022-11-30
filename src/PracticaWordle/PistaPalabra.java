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
public class PistaPalabra extends Pista {

    private final static int COSTE = 5;

    //Constructores
    public PistaPalabra(){
        super(COSTE);
    }

    public void obtenerPista(PartidaPalabra p) {
        mostrarPista(p);
        System.out.println("Se te restarán 5 puntos del cómputo total de esta partida.");
        p.setPuntos(p.getPuntos()-5);
        p.setGanada(true);    
    }
    
    public void mostrarPista(PartidaPalabra p) {
        System.out.println("La palabra oculta es: " + p.getPalabraOculta());        
    }
}