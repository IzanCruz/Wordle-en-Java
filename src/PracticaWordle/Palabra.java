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
    public Palabra(String s){
        palabra = new char[MAX];
        rellenarPalabra(s);
    }

    //Get Set
    public char [] getPalabra(){
        return palabra;
    }
    
    public String getPalabraString() {
        //Hacer que devuelva la versión de String de la palabra.
        String res = "";
        for (int i = 0; i <= (MAX-1); i++){
            res += getPalabra()[i];
        }
        return res;
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

    private void rellenarPalabra(String s){
        char[] aux = s.toCharArray();
        for (int i = 0; i <= 4; i++){
            getPalabra()[i] = aux[i];
        }
    }

    @Override
    public String toString() {
        return getPalabraString();
    }
    
}