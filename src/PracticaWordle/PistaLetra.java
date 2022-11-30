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

    //Getters
    public char getLetra(){
        return letra;
    }

    public void setLetra(char a){
        letra = a;
    }

    //Methods
    public void obtenerPista(PartidaPalabra p) {
        int posLetra = 0;
        do {
            posLetra = (int) Math.random()*4;
        } while (p.getLetrasEncontradas()[posLetra] == true); //Cuando sea false, parará. 
        mostrarPista(posLetra, p);
        setLetra(p.getPalabraOculta().getPalabra()[posLetra]);                           //Esto es para que no te de una pista de una letra que ya se ha encontrado
    }

    public void mostrarPista(int n, PartidaPalabra p) {
        System.out.println("La letra concedida como pista es la " + 
        p.getPalabraOculta().getPalabra()[n] + 
        "y se encuentra en la posición " + n+1);
    }

    //mejor que esto habria que darle al usuario un estado actual de la palabra
    //con la letra otorgada incluida en ese estado actual, impreso por pantalla.
}