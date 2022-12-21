/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Intento implements Serializable{

    private int numIntento;
    private static final int MAXNUMINTENTOS = 5;
    
    //Constructores
    public Intento(){
        numIntento = MAXNUMINTENTOS;
    }
    
    //Get Set
    public int getNumIntento(){
        return numIntento;
    }

    public int getMaxIntentos() {
        return MAXNUMINTENTOS;        
    }

    public void setIntento(int n){
        numIntento = n;
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
        Intento i = (Intento) o;
        return (getNumIntento() == i.getNumIntento());
    }

    public void actualizarIntento(){
        if (getNumIntento() > 0)
            setIntento(getNumIntento() - 1);
    }

    public String toString(){
        return ("" + getNumIntento());
    }

    
}