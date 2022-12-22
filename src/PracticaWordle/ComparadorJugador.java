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
public class ComparadorJugador implements Comparator <Jugador>{

    public int compare(Jugador j1, Jugador j2){
        int aux;
        if ((j1.getGanadas() == j2.getGanadas())){
            if (j1.getPerdidas() != j2.getPerdidas())
                aux = ((j1.getPerdidas() - j2.getPerdidas()));
            else{
                aux = 0;
            }
        }
        else{
            aux = j2.getGanadas() - j1.getGanadas();
        }
        return aux;
    }

}
