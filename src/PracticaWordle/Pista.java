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
public class Pista {
     
    private int coste;   
    //Constructores
    public Pista(int i){
        coste = i;
    }
    
    //Get Set
    public int getCoste(){
        return coste;
    }

    //Metodos
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Pista p = (Pista) o;
        return (getCoste() == p.getCoste());
    }
    
}