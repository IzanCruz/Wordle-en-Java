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
public class Intento {

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
    
}