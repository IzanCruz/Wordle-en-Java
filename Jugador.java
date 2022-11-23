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
public class Jugador {
    
    private String nombreUsuario;
    private int puntos;
    private int ganadas;
    private int empatadas;
    private int perdidas;
    
    //Cinstructores
    public Jugador(String n){
        if (!(n.equals(" "))){
            nombreUsuario = n;
        }
    }
    
    //Get Set
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
        puntos = i;
    }
    
    public void setGanadas(int i){
        ganadas = i;
    } 
    
    public void setEmpatadas(int i){
        empatadas = i;
    }
    
    public void setPerdidas(int i){
        perdidas = i;
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
    
}
