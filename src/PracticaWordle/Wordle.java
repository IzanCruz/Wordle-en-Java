/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

import java.util.ArrayList;
import PracticaWordle.Exepciones.JugadorExcepcion;
import java.io.Serializable;


/**
 *
 * @author USER
 */
public class Wordle implements Serializable {

    // Atributes
    private ArrayList<Partida> listaPartidas;
    private ArrayList<Jugador> listaJugadores;

    // Constructors
    public Wordle() {
        listaPartidas = new ArrayList<Partida>();
        listaJugadores = new ArrayList<Jugador>();
    }

    // Getters
    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    // Methods
    public void registrarJugador(Jugador j) throws JugadorExcepcion {
        if (!existeJugador(j.getNombre())) {
            listaJugadores.add(j);
            System.out.println(j.getNombre() + " has sido registrado con éxito!");            
        } else {
            throw new JugadorExcepcion("El jugador ya está registrado actualmente.");
        }
    }

    private String[] meterPalabras(Partida p) {
        int numPalabras = p.getNumPalabras() * 2;
        String[] palabrasPartida = new String[numPalabras];
        OperacionesFicheros of = new OperacionesFicheros();
        String[] palabrasObtenidas = of.obtenerPalabras();
        for (int i = 0; i < numPalabras; i++) {
            int aleatorio = (int) (Math.random() * of.getNUM_PALABRAS());
            palabrasPartida[i] = palabrasObtenidas[aleatorio];
        }
        return palabrasPartida;
    }

    public void iniciarPartida(Jugador j1, Jugador j2, int numPalabras) throws JugadorExcepcion {
        // Verifico si los jugadores con los que se quiere iniciar la partida están
        // registrados
        // Jugador jug1 = registrarJugador(j1);
        Partida p;
        // Creo la partida con los jugadores y el numero de palabras que se me indique
        if (j2 != null) {
            // Jugador jug2 = registrarJugador(j2);
            p = new Partida(j1, j2, numPalabras);
        } else
            p = new Partida(j1, numPalabras);

        // Se obtienen las palabras de manera aleatoria accediendo a un fichero con
        // miles de palabras
        String[] palabras = meterPalabras(p);
        // Se crean todas las partidasPalabras y finalmente se juega la partida
        p.crearPartidasPalabra(palabras);
        p.jugarPartida();
        // Se añade la partida a la lista de partidas
        anadirPartida(p);
        System.out.println("Fin de partida");
    }

    public void anadirPartida(Partida p) {
        if (!listaPartidas.contains(p)) {
            listaPartidas.add(p);
            System.out.println("La partida se ha guardado correctamente.");
        } else
            System.err.println("Error: Esta partida ya se ha guardado anteriormente.");
    }

    /*
     * private Jugador encontrarJugador(String nombre) {
     * Jugador aux = new Jugador(nombre);
     * int pos = listaJugadores.indexOf(aux);
     * return listaJugadores.get(pos);
     * }
     */

    private boolean existeJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        return listaJugadores.contains(jugador);
    }

    /*
     * private boolean existeJugador(Jugador j){
     * int i = 0;
     * boolean estado = false;
     * do{
     * estado = getListaJugadores().get(i).equals(j);
     * i++;
     * }
     * while((i < (getListaJugadores().size() - 1)) && (estado == false));
     * return estado;
     * }
     */

    public String rankingJugadores(ArrayList<Jugador> list) {
        list.sort(new ComparadorJugador());
        return imprimirListaJ(list);
    }

    public String ordenAlfabetico(ArrayList<Jugador> list) {
        list.sort(new ComparadorAlfabetico());
        return imprimirListaJ(list);
    }

    public String partidas(ArrayList<Partida> part) {
        return imprimirListaP(part);
    }

    private String imprimirListaJ(ArrayList<Jugador> list) {
        String aux = "";
        for (int i = 0; i <= (list.size() - 1); i++) {
            aux += list.get(i).toString();
        }
        return aux;
    }

    private String imprimirListaP(ArrayList<Partida> list) {
        String aux = "";
        for (int i = 0; i <= (list.size() - 1); i++) {
            aux += list.get(i).toString();
        }
        return aux;
    }

}
