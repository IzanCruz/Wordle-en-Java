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
public class Palabra {
    //Atributos 
    private static final int MAX = 5; 
    private char[] palabra;
    
    //Constructor
    public Palabra(){
        palabra = new char[MAX];
    }

    //Get Set
    public char [] getPalabra(){
        return palabra;
    }
    
    //Metodos
    @Override
    public boolean equals (Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Palabra p = (Palabra) o;
        return ((getPalabra()[0] == p.getPalabra()[0]) && (getPalabra()[1] == p.getPalabra()[1]) && 
        (getPalabra()[2] == p.getPalabra()[2]) && (getPalabra()[3] == p.getPalabra()[3]) && 
        (getPalabra()[4] == p.getPalabra()[4]));
    }
    
}