/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
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
    public Jugador registrarJugador(String nombre){
        //CODIGO YULIANS:
        /*Jugador aux = new Jugador(n);
        if (!existeJugador(aux)){
            getListaJugadores().add(aux);
            System.out.println ("Jugador registrado con exito");
            return aux;
        }
        else
            System.out.println ("El jugador ya está registrado actualmente");
            return null;*/
        //CODIGO SANTI:
        if (!existeJugador(nombre)) {
            Jugador nuevo = new Jugador(nombre);
            listaJugadores.add(nuevo);
            System.out.println(nombre + " has sido registrado con éxito!");
            return nuevo;
        } else {
            System.out.println("Bienvenid@ de nuevo, " + nombre + "!");
            return encontrarJugador(nombre);
        }        
    }    

    private String[] meterPalabras(Partida p){
        int numPalabras = p.getNumPalabras()*2;
        String[] palabrasPartida = new String[numPalabras];
        OperacionesFicheros of = new OperacionesFicheros();

        String[] palabrasObtenidas = of.obtenerPalabras();
        for (int i = 0; i < numPalabras; i++) {
            int aleatorio = (int)Math.random()*of.getNLINEAS()+1;
            if (existePalabra(palabrasObtenidas[aleatorio], palabrasPartida, numPalabras)) {
               palabrasPartida[i]= palabrasObtenidas[aleatorio]; 
            }        
        }
        return palabrasPartida;
    }

    private boolean existePalabra(String palabra, String[] almacen, int nLineas) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && (i < nLineas)){
            encontrado = palabra.equals(almacen[i]);
            i++;
        }
        return encontrado;
    }

    public void iniciarPartida(String j1, String j2, int numPalabras) {          
        //Verifico si los jugadores con los que se quiere iniciar la partida están registrados
        Jugador jug1 = registrarJugador(j1);
        Jugador jug2 = registrarJugador(j2);    
        //Creo la partida con los jugadore y el numero de palabras que se me indique
        Partida p = new Partida(jug1, jug2, numPalabras);
 
        //Se obtienen las palabras de manera aleatoria accediendo a un fichero con miles de palabras
        String[] palabras = meterPalabras(p);
        //Se añade la partida a la lista de partidas
        anadirPartida(p);
        //Se crean todas las partidasPalabras y finalmente se juega la partida
        p.crearPartidasPalabra(palabras);

        p.jugarPartida();
        anadirPartida(p);
       System.out.println("Fin de partida");
    }

    private void anadirPartida(Partida p) {
        if (!listaPartidas.contains(p)) {
            listaPartidas.add(p);
            System.out.println("La partida se ha guardado correctamente.");
        } else System.err.println("Error: Esta partida ya se ha guardado anteriormente.");
    }

    private Jugador encontrarJugador(String nombre) {
        Jugador aux = new Jugador(nombre);
        int pos = listaJugadores.indexOf(aux);
        return listaJugadores.get(pos);
    }

    private boolean existeJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        return listaJugadores.contains(jugador);
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

    public String rankingJugadores(ArrayList <Jugador> list){
        list.sort(new ComparadorJugador());
        return imprimirListaJ(list);
    }

    public String ordenAlfabetico(ArrayList <Jugador> list){
        list.sort(new ComparadorAlfabetico());
        return imprimirListaJ(list);
    }

    public String partidas(ArrayList <Partida> part){
        return imprimirListaP(part);
    }
    
    private String imprimirListaJ(ArrayList <Jugador> list){
        String aux = "";
        for (int i = 0; i <= (list.size() - 1); i++){
            aux += list.get(i).toString();
        }
        return aux;
    }

    private String imprimirListaP(ArrayList <Partida> list){
        String aux = "";
        for (int i = 0; i <= (list.size() - 1); i++){
            aux += list.get(i).toString();
        }
        return aux;
    }

}
