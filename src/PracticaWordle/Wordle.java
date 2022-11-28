/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class Wordle {

    //Atributes
    private ArrayList <Partida> listaPartidas;
    private ArrayList <Jugador> listaJugadores;

    //Constructors
    public Wordle(){
        listaPartidas = new ArrayList <Partida> ();
        listaJugadores = new ArrayList <Jugador> ();
    }

    //Getters
    public ArrayList <Partida> getListaPartidas(){
        return listaPartidas;
    }

    public ArrayList <Jugador> getListaJugadores(){
        return listaJugadores;
    }

    //Methods
    public String registrarJugador(String n){
        Jugador aux = new Jugador(n);
        if (!existeJugador(aux)){
            getListaJugadores().add(aux);
            return ("Jugador registrado con exito");
        }
        else
            return ("El jugador ya está registrado actualmente");
    }

    private boolean existeJugador(Jugador j){
        int i = 0;
        boolean estado = false;
        do{
            estado = getListaJugadores().get(i).equals(j);
            i++;
        }
        while((i < (getListaJugadores().size() - 1)) && (estado == false));
        return estado;
    }
}
