/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;
import java.util.Comparator;
/**
 *
 * @author GRUPO 19
 */
public class ComparadorAlfabetico implements Comparator <Jugador>{

    public int compare(Jugador j1, Jugador j2){
        return (j1.getNombre().compareTo(j2.getNombre()));
    }

}