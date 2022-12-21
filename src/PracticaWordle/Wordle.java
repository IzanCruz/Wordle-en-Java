/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

import java.util.ArrayList;
import java.util.Scanner;

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
    private static final int PALABRAS_POR_DEFECTO = 1;
    private static final String USUARIO = "admin";
    private static final String PASSWD = "12345";      

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

    public void eliminarJugador(Jugador j) throws JugadorExcepcion {
        if (existeJugador(j.getNombre())) {
            listaJugadores.remove(j);
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

    public void iniciarPartida(Jugador j1, Jugador j2, int numPalabras, Scanner s) throws JugadorExcepcion {
        // Verifico si los jugadores con los que se quiere iniciar la partida están
        // registrados
        Jugador jug1 = encontrarJugador(j1);
        Partida p;
        // Creo la partida con los jugadores y el numero de palabras que se me indique
        if (j2 != null) {
            Jugador jug2 = encontrarJugador(j2);
            p = new Partida(jug1, jug2, numPalabras);
        } else
            p = new Partida(jug1, numPalabras);

        // Se obtienen las palabras de manera aleatoria accediendo a un fichero con
        // miles de palabras
        String[] palabras = meterPalabras(p);
        // Se crean todas las partidasPalabras y finalmente se juega la partida
        p.crearPartidasPalabra(palabras);
        p.jugarPartida(s);
        System.out.println("Fin de partida");
        if (j2 != null){
            // Se añade la partida a la lista de partidas
            anadirPartida(p);
        }
    }

    public void anadirPartida(Partida p) {
        if (!listaPartidas.contains(p)) {
            listaPartidas.add(p);
            System.out.println("La partida se ha guardado correctamente.");
        } else
            System.err.println("Error: Esta partida ya se ha guardado anteriormente.");
    }

    private boolean existeJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        return listaJugadores.contains(jugador);
    }

    private Jugador encontrarJugador(Jugador j){
        if (existeJugador(j.getNombre())){
            int i = 0;
            Jugador aux = new Jugador("");
            boolean encontrado = false;
            while(i < getListaJugadores().size() && !encontrado){
                aux = getListaJugadores().get(i);
                encontrado = j.equals(aux);
                i++;
            }
            return aux;
        }
        else{
            return j;
        }
    }

    public void rankingJugadores(ArrayList<Jugador> list) {
        ArrayList <Jugador> aux = list;
        aux.sort(new ComparadorJugador());
        OperacionesFicheros f = new OperacionesFicheros();
        f.crearRanking(aux);
    }

    public void rankingAlfabetico(ArrayList<Jugador> list) {
        ArrayList <Jugador> aux = list;
        aux.sort(new ComparadorAlfabetico());
        OperacionesFicheros f = new OperacionesFicheros();
        f.crearRankingAlfabetico(aux);
    }

    /*public String partidas(ArrayList<Partida> part) {
        return imprimirListaP(part);
    }


    private String imprimirListaP(ArrayList<Partida> list) {
        String aux = "";
        for (int i = 0; i <= (list.size() - 1); i++) {
            aux += list.get(i).toString();
        }
        return aux;
    }*/

    public void mostrarEstadisticas(Scanner s) {
        System.out.println("Escriba el nombre del jugador: ");
        String res = s.next();
        System.out.println("\n\n");
        if (existeJugador(res)){
            Jugador j = new Jugador(res);
            Jugador j2 = encontrarJugador(j);
            System.out.println(j2.toString());
        }
        else{
            System.out.println("El jugador no existe actualmente.\n");
        }
    }

    public void mostrarMenu(Scanner s) throws JugadorExcepcion {
        int eleccion;
        do {
            leyendaInicio();
            eleccion = s.nextInt();
            menu(s, eleccion);
        } while (eleccion != 4);
        
    }

    private void menu(Scanner s, int opcion) throws JugadorExcepcion { 
        int numPalabras = PALABRAS_POR_DEFECTO;     
        OperacionesFicheros of = new OperacionesFicheros();     
        switch(opcion) {
            case 1: MostrarOpcionesPartida();
                    opcion = s.nextInt();
                    if (opcion == 1) {
                        System.out.print("Jugador: ");
                        Jugador j1 = new Jugador(s.next());
                        iniciarPartida(j1, null, numPalabras, s);
                    }                        
                    else{
                        if (opcion == 2){
                            System.out.print("Jugador 1: ");
                            Jugador j1 = new Jugador(s.next());
                            System.out.print("\nJugador 2: ");
                            Jugador j2 = new Jugador(s.next());
                            iniciarPartida(j1, j2, numPalabras, s);
                        }
                        else{
                            if (opcion == 3)
                                break;
                        }
                    }
            break;            
            case 2: mostrarEstadisticas(s);
                    break;            
                case 3: if(comprobarAdministrador(s)){
                            mostrarConfigurarOpciones();
                            opcion = s.nextInt();
                            if (opcion == 1) {
                                do {
                                    System.out.print("Inserte un numero entre 1 y 10.\n"
                                    + "Número de palabras por partida: "
                                    );
                                    numPalabras = s.nextInt();
                                    System.out.println();
                                } while((numPalabras < 1) && (numPalabras > 10));
                                
                            } 
                            else if (opcion == 2) {
                                System.out.println("Introduzca el nombre: ");
                                registrarJugador(new Jugador(s.next()));
                            }   
                            else if (opcion == 3) {
                                String nombreJugador = null;                                                
                                do {
                                    System.out.println("\nPulse 0 si desea salir. 1 para continuar.");
                                    opcion = s.nextInt();
                                    if (opcion != 0) {                                                        
                                        System.out.println("Introduzca el nombre: "); 
                                        nombreJugador = s.next();                           
                                        if (existeJugador(nombreJugador)) {                               
                                            System.out.println("Seguro que desea eliminar a " + nombreJugador);
                                            System.out.println("1. Sí.\n"
                                            + "2. No.\n");
                                            opcion = s.nextInt();
                                            if (opcion == 1) {
                                                eliminarJugador(new Jugador(nombreJugador));
                                                System.out.println("El jugador \'" + nombreJugador + "\' ha sido eliminado.");
                                            }       
                                            
                                            System.out.println("Desea eliminar a otro jugador?\n\n"
                                            + "1. Sí.\n"                            
                                            + "2. No.\n"                            
                                            );

                                            opcion = s.nextInt();
                                        }
                                        else {
                                            System.out.println("El jugador \'" + nombreJugador + "\' no existe.\n" 
                                            + "Inserte un nombre válido.\n\n" 
                                            );    
                                                                        
                                        }     
                                    }                                                                            
                                    
                                } while ((opcion == 1) );                                                                        
                            }
                        }
            break;            
            case 4: System.out.println("Saliendo...");
                    of.guardarPartidas(this);
                    of.guardarJugadores(this);
            break;            
        }
    }

    private void leyendaInicio() {
        System.out.println("Hola! Bienvenido a Wordle.\n"
        + "Wordle es un juego de palabras en el que deberás adivinar la palabra oculta.\n\n"
        + "Selecciona una opción:\n\n"
        + "1. Jugar.\n"
        + "2. Consultar Estadísticas.\n"
        + "3. Configurar Opciones.\n"
        + "4. Salir.\n"        
        );
    }

    private void MostrarOpcionesPartida() {
        System.out.println("Seleccione modo de juego:\n\n"
        + "1. Modo entrenamiento.\n"
        + "2. 1 contra 1.\n"
        + "3. volver atras.\n"
        );        
    }    

    private void mostrarConfigurarOpciones() {
        System.out.println("Seleccione una opción:\n\n"
        + "1. Modificar número de palabras en las partidas.\n"
        + "2. Registrar jugador.\n"
        + "3. Eliminar jugador.\n"
        ); 
    }

    private boolean comprobarAdministrador(Scanner s){
        System.out.println("Usuario: ");
        String aux = s.next();
        System.out.println("\nContrasena: ");
        String aux2 = s.next();

        boolean b1 = aux.equals(USUARIO);
        boolean b2 = aux2.equals(PASSWD);

        if (b1 && b2)
            return true;
        else{
            System.out.println("Usuario o contrasena incorrectos.");
            return false;
        }
    }
    
}
