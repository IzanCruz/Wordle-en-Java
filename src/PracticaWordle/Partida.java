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
public class Partida {
    // Atributes
    private int numPalabras; // Numero de palabras que se juega
    private Jugador jugador1, jugador2, ganador;
    private int ganadosJug1, ganadosJug2;
    private PartidaPalabra[][] listaPPalabras;
    private final static int MAXPALABRAS = 10;
    private final static int MAX_NUM_JUGADORES = 2;
    private int puntosJug1, puntosJug2;
    private int cont1, cont2;
    private int nPistasJ1, nPistasJ2;

    // Constructores
    public Partida(Jugador j1, Jugador j2, int n) { // Modo multijugador
        this(j1, n);
        if (j2 != null)
            jugador2 = j2;
    }

    public Partida(Jugador j, int n) { // Modo entrenamiento
        listaPPalabras = new PartidaPalabra[MAX_NUM_JUGADORES][MAXPALABRAS];
        numPalabras = n;
       // turno = true;
        if (j != null)
            jugador1 = j;
    }

    // Getters
    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public int getGanadosJug1() {
        return ganadosJug1;
    }

    public int getGanadosJug2() {
        return ganadosJug2;
    }

    public PartidaPalabra[][] getListaPPalabras() {
        return listaPPalabras;
    }

    public int getPuntosJ1() {
        return puntosJug1;
    }

    public int getPuntosJ2() {
        return puntosJug2;
    }

    public int getCont1() {
        return cont1;
    }

    public int getCont2() {
        return cont2;
    }

    public int getNPistasJ1(){
        return nPistasJ1;
    }

    public int getNPistasJ2(){
        return nPistasJ2;
    }

    public void setNPistasJ1(int n){
        nPistasJ1 = n;
    }

    public void setNPistasJ2(int n){
        nPistasJ2 = n;
    }

    public void setNumPalabras(int n) {
        numPalabras = n;
    }

    public void setGanadosJug1() {
        ganadosJug1++;
    }

    public void setGanadosJug2() {
        ganadosJug2++;
    }

    public void setPuntosJ1(int n) {
        puntosJug1 = n;
    }

    public void setPuntosJ2(int n) {
        puntosJug2 = n;
    }

    public void setCont1(int n) {
        cont1 = n;
    }

    public void setCont2(int n) {
        cont2 = n;
    }

    public void setGanador(Jugador g) {
        ganador = g;
    }

    // Methods

    public void crearPartidasPalabra(String[] lista) {
        int numJugadores = MAX_NUM_JUGADORES;
        if (jugador2 == null) {
            numJugadores--;
        }
        for (int j = 0; j < numJugadores; j++) {
            for (int i = 0; i < getNumPalabras(); i++) {
                if (j == 0) {
                    getListaPPalabras()[j][i] = new PartidaPalabra(getJugador1(), new Palabra(lista[i]));
                } else {
                    getListaPPalabras()[j][i] = new PartidaPalabra(getJugador2(),
                            new Palabra(lista[i + getNumPalabras()]));
                }
            }
        }
    }

    public void jugarPartida() {
        int numJugadores = MAX_NUM_JUGADORES;
        if (jugador2 == null) {
            numJugadores--;
        }
        for (int j = 0; j < numJugadores; j++) {
            for (int i = 0; i < getNumPalabras(); i++) {
                System.out.println("J" + (j + 1));
                getListaPPalabras()[j][i].resolver();
            }
        }
        actualizarDatos();      
    }

    public void actualizarDatos() {
        // Acualizar datos de esta clase
        int i = 0; // Actualizar para jugador1
        for (int j = 0; j < getNumPalabras(); j++) {
            if (getListaPPalabras()[i][j].isGanada())
                setGanadosJug1();
            setPuntosJ1(getListaPPalabras()[i][j].getPuntos() + getPuntosJ1());
            setNPistasJ1(getNPistasJ1() + getListaPPalabras()[j][i].getPistas());
        }
        if (getJugador2() != null) {
            i = 1; // Actualizar para jugador2
            for (int j = 0; j < getNumPalabras(); j++) {
                if (getListaPPalabras()[i][j].isGanada())
                    setGanadosJug2();
                setPuntosJ2(getListaPPalabras()[i][j].getPuntos() + getPuntosJ2());
                setNPistasJ2(getNPistasJ2() + getListaPPalabras()[i][j].getPistas());
            }
            elegirGanador(); // Se elige el ganador de esta partida.
            actualizarJugadores(getJugador1(), getJugador2()); // Luego actualiza los datos en la clase Jugador
        }

    }

    private void actualizarJugadores(Jugador j1, Jugador j2) {
        // Actualizar los atributos de la clase Jugador.
        if (getGanador() == null) {
            getJugador1().incrementarEmpatadas();
            getJugador2().incrementarEmpatadas();
        } else {
            if (getGanador().equals(j1)) {
                getJugador1().incrementarGanadas();
                getJugador2().incrementarPerdidas();
            } else if (getGanador().equals(j2)) {
                getJugador2().incrementarGanadas();
                getJugador1().incrementarPerdidas();
            }
        }
        getJugador1().setPuntos(getJugador1().getPuntos() + getPuntosJ1());
        getJugador2().setPuntos(getJugador2().getPuntos() + getPuntosJ2());
    }


    private void elegirGanador() {
        if (getGanadosJug1() > getGanadosJug2())
            setGanador(getJugador1());
        else if (getGanadosJug2() > getGanadosJug1())
            setGanador(getJugador2());
        else
            setGanador(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Partida p = (Partida) o;
        return ((getJugador1().equals(p.getJugador1())) && (getJugador2().equals(p.getJugador2())) &&
                (getNumPalabras() == p.getNumPalabras()) && (getGanadosJug1() == p.getGanadosJug1()) &&
                (getGanadosJug2() == p.getGanadosJug2()) && (getListaPPalabras().equals(p.getListaPPalabras())));
    }

    public String toString() {
        String aux;
        aux = (" J1: " + getJugador1().getNombre() + "\nPistas utilizadas: " + getNPistasJ1() + "\nJ2: " + getJugador2().getNombre() + "\nPistas utilizadas: " + getNPistasJ2());
        if (getGanador() != null)
            aux += ("\nGanador: " + getGanador().getNombre() + "\n");
        else aux += "\nEmpate";
        return aux;
    }

}