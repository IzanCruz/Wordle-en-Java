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
public class Jugador implements Serializable{
    
    private String nombreUsuario;
    private int puntos;
    private int ganadas;
    private int empatadas;
    private int perdidas;    
    
    //Cinstructores
    public Jugador(String n){
        if (!(n.equals(""))){
            nombreUsuario = n;
        }
    }
    
    public Jugador (Jugador j){
        nombreUsuario = j.getNombre();
        puntos = j.getPuntos();
        ganadas = j.getGanadas();
        empatadas = j.getEmpatadas();
        perdidas = j.getPerdidas();
    }
    
    //Get incrementar
    public String getNombre(){
        return nombreUsuario;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public int getGanadas(){
        return ganadas;
    }
    
    public int getEmpatadas(){
        return empatadas;
    }
    
    public int getPerdidas(){
        return perdidas;
    }
    
    public void setPuntos(int i){
        puntos+= i;
    }
    
    public void incrementarGanadas(){
        ganadas++;
    } 
    
    public void incrementarEmpatadas(){
        empatadas++;
    }
    
    public void incrementarPerdidas(){
        perdidas++;
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
        Jugador j = (Jugador) o;
        return (getNombre().equals(j.getNombre()));
    }

    @Override
    public String toString() {
        return (getNombre() + "\n" + getPuntos() + " pts\n" + getGanadas() + " ganadas\n" + getEmpatadas() + " empatadas\n" + getPerdidas() + " perdidas");
    }
    
}