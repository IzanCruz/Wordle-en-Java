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
    private Palabra palabra;

    //Constructores
    public PistaPalabra(){
        super(COSTE);
    }

    public boolean obtenerPista(Jugador j, Palabra c, Intento intent, boolean[] b) {
        if (j.getPuntos() >= COSTE){
            mostrarPista(c);
            System.out.println("Se te restarán 5 puntos.");
            j.setPuntos(j.getPuntos()-5);            
            intent.setIntento(0);
            return true;
        }
        else{
            System.out.println("Puntos insuficientes");
            return false;
        }    
    }
    
    public void mostrarPista(Palabra p) {
        System.out.println("La palabra oculta es: " + p.toString());        
    }
}