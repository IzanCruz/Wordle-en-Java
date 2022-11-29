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
    public PistaLetra(char a){
        super(COSTE);
        letra = a;
    }

    public void obtenerPista(PartidaPalabra p) {
        int posLetra = 0;
        do {
            posLetra = (int) Math.random()+4;
        } while (p.getLetrasEncontradas()[posLetra] == true); //Cuando sea false, parará. Esto es para que no te de una pista de
                                                              // de una letra que ya se ha encontrado 
         

    }

    /*Deberíamos usar lo que dimos en ED de un Array de booleans    
     *o algo parecido de manera que podamos saber si la letra que
     *se encuentra en la posición 3 ha sido acertada.
     O inclso puede usarse un array de tipo enumerado, en el que tengamos
     ENCONTRADA
     MAL_UBICADA
     NO_ENCONTRADA
     y así podemos controlar que pista de letra podemos dar, para no dar
     una pista de una letra y ubicación que ya se tienen.
    */
    
}