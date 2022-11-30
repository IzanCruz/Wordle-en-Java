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
public class PistaLetra extends Pista {
        
    private static final int COSTE = 2;
    private char letra;
    
    //Constructores
    public PistaLetra(){
        super(COSTE);
    }

    public void obtenerPista(PartidaPalabra p) {
        int posLetra = 0;
        do {
            posLetra = (int) Math.random()+4;
        } while (p.getLetrasEncontradas()[posLetra] == true); //Cuando sea false, parará. Esto es para que no te de una pista de
                                                              // de una letra que ya se ha encontrado 
        mostrarPista(posLetra, p);         
    }

    public void mostrarPista(int n, PartidaPalabra p) {
        System.out.println("La letra concedida como pista es la " + 
        p.getPalabraOculta().getPalabra()[n] + 
        "y se encuentra en la posición " + n+1);
    }

    //mejor que esto habria que darle al usuario un estado actual de la palabra
    //con la letra otorgada incluida en ese estado actual, impreso por pantalla.
}