/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

/**
 *
 * @author GRUPO 19
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
    public boolean obtenerPista(Jugador j,Palabra c,  Intento intent, boolean[] b) {
        if (j.getPuntos() >= COSTE) {
            int posLetra = 0;
            do {
                posLetra = (int) Math.random()*4;
            } while (b[posLetra] == true); //Cuando sea false, parará. 
            mostrarPista(posLetra, c.getPalabra());
            setLetra(c.getPalabra()[posLetra]); //Esto es para que no te de una pista de una letra que ya se ha encontrado
            return true;
            }                     
            else{
                System.out.println("Puntos insuficientes");
                return false;
            }
    }

    public void mostrarPista(int n, char[] c) {
        System.out.println("La letra concedida como pista es la " + 
        "\"" + c[n] + "\"" + " y se encuentra en la posición " + (n+1));
    }

}