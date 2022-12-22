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

    private void eliminarJugador(Jugador j) throws JugadorExcepcion {
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

    private void iniciarPartida(Jugador j1, Jugador j2, int numPalabras, Scanner s) throws JugadorExcepcion {
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
            System.out.println("\nLa partida se ha guardado correctamente.");
        } else
            System.err.println("Error: Esta partida ya se ha guardado anteriormente.");
    }

    private boolean existeJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        return listaJugadores.contains(jugador);
    }

    private Jugador encontrarJugador(Jugador j) { // Revisa si el jugador que se le ha pasado está en la lista de
                                                  // jugadores.
        if (existeJugador(j.getNombre())) { // Si no está, se devuelve la misma instancia de jugador que se le ha pasado
                                            // por parámetro
            int i = 0; // Si está, se pasa dicha instancia de jugador (que se encuentra dentro de la
                       // lista).
            Jugador aux = new Jugador("");
            boolean encontrado = false;
            while (i < getListaJugadores().size() && !encontrado) {
                aux = getListaJugadores().get(i);
                encontrado = j.equals(aux);
                i++;
            }
            return aux;
        } else {
            return j;
        }
    }

    private void rankingJugadores(ArrayList<Jugador> list) {
        ArrayList<Jugador> aux = list;
        aux.sort(new ComparadorJugador());
        OperacionesFicheros f = new OperacionesFicheros();
        f.crearRanking(aux);
    }

    private void rankingAlfabetico(ArrayList<Jugador> list) {
        ArrayList<Jugador> aux = list;
        aux.sort(new ComparadorAlfabetico());
        OperacionesFicheros f = new OperacionesFicheros();
        f.crearRankingAlfabetico(aux);
    }

    private void mostrarEstadisticas(Scanner s) {
        System.out.println("Escriba el nombre del jugador: ");
        String res = s.next();
        System.out.println("\n\n");
        if (existeJugador(res)) {
            Jugador j = new Jugador(res);
            Jugador j2 = encontrarJugador(j);
            System.out.println(j2.toString());
        } else {
            System.out.println("El jugador no existe actualmente.\n");
        }
    }

    public void mostrarMenu(Scanner s) throws JugadorExcepcion {
        String eleccion;
        int numPalabras = PALABRAS_POR_DEFECTO;
        do {
            leyendaInicio();
            eleccion = s.next();
            numPalabras = menu(s, eleccion, numPalabras);
        } while (!(eleccion.equals("4")));

    }

    private int menu(Scanner s, String opcion, int numPalabras) throws JugadorExcepcion {  
        int num = numPalabras;      
        OperacionesFicheros of = new OperacionesFicheros();
        switch (opcion) {
            case "1":
                mostrarOpcionesPartida();
                opcion = s.next();
                if (opcion.equals("1")) {
                    System.out.print("Jugador: ");
                    Jugador j1 = new Jugador(s.next());
                    if (j1 == encontrarJugador(j1)) {
                        System.out.println("El jugador que ha introducido no se encuentra en la lista.\n"
                                + "No se guardarán los datos de esta partida.");
                        iniciarPartida(j1, null, num, s);
                    }
                }else if(opcion.equals("2")){
                    System.out.println("\nJugador 1: ");
                    Jugador j1 = new Jugador(s.next());
                    System.out.println("\nJugador 2: ");
                    Jugador j2 = new Jugador(s.next());
                    Jugador jug1 = encontrarJugador(j1);
                    Jugador jug2 = encontrarJugador(j2);
                    iniciarPartida(jug1, jug2, num, s);
                }else if(opcion.equals("3")){
                    System.out.println("\nVolviendo al menu principal.\n");
                }
                break;            
            case "2": mostrarEstadisticas(s);
                    mostrarOpcionesPerfil(s);
                    break;            
            case "3":
                if (comprobarAdministrador(s)) {
                    num = ejecutarOpciones(s, opcion, num);
                }
                break;

            case "4":
                System.out.println("Saliendo...");
                of.guardarPartidas(this);
                of.guardarJugadores(this);
                break;
        }
        return num;
    }

    private void leyendaInicio() {
        System.out.println("\nHola! Bienvenido a Wordle.\n"
        + "Wordle es un juego de palabras en el que deberás adivinar la palabra oculta.\n\n"
        + "Selecciona una opción:\n\n"
        + "1. Jugar.\n"
        + "2. Consultar Estadísticas.\n"
        + "3. Configurar Opciones.\n"
        + "4. Salir.\n"        
        );
    }

    private void mostrarOpcionesPartida() {
        System.out.println("\nSeleccione modo de juego:\n\n"
        + "1. Modo entrenamiento.\n"
        + "2. 1 contra 1.\n"
        + "3. Volver al menu principal.\n"
        );        
    }    

    private void mostrarConfigurarOpciones() {
        System.out.println("\nSeleccione una opción:\n\n"
        + "1. Modificar número de palabras en las partidas.\n"
        + "2. Registrar jugador.\n"
        + "3. Eliminar jugador.\n"
        + "4. Generar archivo de ranking de jugadores.\n"
        + "5. Generar archivo de listado de jugadores.\n"
        + "6. Volver al menu principal.\n"
        ); 
    }

    private void mostrarOpcionesPerfil(Scanner s){
        System.out.println("\nSeleccione la accion que desee realizar.\n"
        + "1. Mostrar ranking jugadores.\n"
        + "2. Volver al menu principal.\n");
        int res = s.nextInt();
        System.out.println("\n");
        if (res == 1){
            ArrayList <Jugador> aux = getListaJugadores();
            aux.sort(new ComparadorJugador());
            int i = 0;
            while(i < aux.size()){
                System.out.println("" + (i+1) + "º " + aux.get(i).toString() + "\n");
            }
        }
        else if(res ==2){}
    }

    private boolean comprobarAdministrador(Scanner s) {
        System.out.print("Usuario: ");
        String aux = s.next();
        System.out.print("\nContrasena: ");
        String aux2 = s.next();

        boolean b1 = aux.equals(USUARIO);
        boolean b2 = aux2.equals(PASSWD);

        if (b1 && b2) {
            System.out.println("\nSe ha accedido correctamente.\n");
            return true;
        }

        else {
            System.out.println("\nUsuario o contrasena incorrectos.\n");
            return false;
        }
    }


    private int ejecutarOpciones(Scanner s, String opcion, int num) throws JugadorExcepcion {
        mostrarConfigurarOpciones();
        int numPalabras = num;
        opcion = s.next();
        if (opcion.equals("1")) {
            do {
                System.out.print("Inserte un numero entre 1 y 10.\n"
                        + "Número de palabras por partida: ");
                numPalabras = s.nextInt();                
                System.out.println();
            } while ((numPalabras < 1) || (numPalabras > 10));

        } else if (opcion.equals("2")) {
            System.out.print("\nIntroduzca el nombre: ");
            registrarJugador(new Jugador(s.next()));
            System.out.println();
        } else if (opcion.equals("3")) {
            String nombreJugador = null;
            do {
                System.out.println("\nPulse 0 si desea salir. 1 para continuar.\n");
                opcion = s.next();
                if (!(opcion.equals("0"))) {
                    System.out.print("\nIntroduzca el nombre: ");
                    nombreJugador = s.next();
                    if (existeJugador(nombreJugador)) {
                        System.out.println("\n\nSeguro que desea ELIMINAR a " + nombreJugador + "?");
                        System.out.println("1. Sí.\n"
                                + "2. No.\n");
                        opcion = s.next();
                        if (opcion.equals("1")) {
                            eliminarJugador(new Jugador(nombreJugador));
                            System.out.println("\nEl jugador " + nombreJugador + " ha sido ELIMINADO.\n");
                        }

                        System.out.println("Desea eliminar a otro jugador?\n"
                                + "1. Sí.\n"
                                + "2. No.\n");

                                opcion = s.next();
                    } else {
                        System.out.println("El jugador \'" + nombreJugador + "\' no existe.\n"
                                + "Inserte un nombre válido.\n\n");
                    }
                }
            } while ((opcion.equals("1")));
        } else if (opcion.equals("4")){
            rankingJugadores(getListaJugadores());
            System.out.println("Ranking generado correctamente.\n");
        } else if (opcion.equals("5")){
            rankingAlfabetico(getListaJugadores());
            System.out.println("Listado generado correctamente.\n");
        } else if (opcion.equals("6"))
                System.out.println("Volviendo al menu principal...\n");
        return numPalabras;
    }

}
