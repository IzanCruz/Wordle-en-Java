/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class PartidaPalabra implements Serializable{
    // Atributes
    private Jugador jugador;
    private boolean ganada;
    private Palabra palabraOculta;
    private Intento intento;
    private int puntos;
    private boolean[] letrasEncontradas;
    private CardinalidadPalabra cp;
    private int pistas;
    private final static int MAX_LETRAS = 5;
    private final static int COSTE_PISTAPALABRA = 5;
    private final static int COSTE_PISTALETRA = 2;

    // Constructors
    public PartidaPalabra(Jugador j, Palabra p) {
        letrasEncontradas = new boolean[MAX_LETRAS];
        if (j != null)
            jugador = j;
        if (p != null)
            palabraOculta = p;
        intento = new Intento();
    }

    // Methods
    public Jugador getJugador() {
        return jugador;
    }

    public boolean isGanada() {
        return ganada;
    }

    public Palabra getPalabraOculta() {
        return palabraOculta;
    }

    public boolean[] getLetrasEncontradas() {
        return letrasEncontradas;
    }

    public Intento getIntento() {
        return intento;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPistas(){
        return pistas;
    }

    public void setPistas(int n){
        pistas = n;
    }

    public void setGanada(boolean p) {
        ganada = p;
    }

    public void setPuntos(int n) {
        puntos = n;
    }

    // Methods
    public void darPuntos() {
        setPuntos(getPuntos() + getIntento().getNumIntento());
    }

    public void resolver() {
        mostrarLeyenda();
        String entrada = null;
        Scanner s = new Scanner(System.in);
        try {
            while (intento.getNumIntento() > 0 && !isGanada()) {// Mientras que el numero de intentos sea mayor que 0 o
                                                                // no se haya adivinado la palabra
                do {
                    entrada = s.next();
                } while (entrada == null); // Mientas que la palabra introducida no se encuentre entre las palabras del
                                           // fichero.
                System.out.println(comprobarPalabra(entrada));
            }
            if (!isGanada()){
                if (intento.getNumIntento() == 0)
                    mostrarPalabraOculta();
            }
        } catch (Exception e) {
            System.err.println("Se para aqui" + e);
        }
    }

    public String comprobarPalabra(String palabra) {
        String respuesta = ""; // Respuesta al usuario sobre el estado de la palabra.
        cp = new CardinalidadPalabra();

        if (palabra.length() == MAX_LETRAS) {
            Palabra aux = new Palabra(palabra);// Guarda la palabra en formato "Palabra"
            cp.calcularCardinalidad(getPalabraOculta());// calcula la cardinalidad de las letras de la palabra oculta

            if (getPalabraOculta().equals(aux)) { // en caso de que se acierte todo, directamente se da la victoria
                respuesta = "Felicidades! Has adivinado la palabra: " + getPalabraOculta();
                darPuntos();
                setGanada(true);
                marcarGanada();
            } else {
                for (int i = 0; i < MAX_LETRAS; i++) {
                    if (getPalabraOculta().getPalabra()[i] == aux.getPalabra()[i]) { // En caso de que la letra sea
                                                                                     // correcta
                        getLetrasEncontradas()[i] = true;
                        cp.disminuirCardinalidad(aux.getPalabra()[i]);
                    }
                } // Hace los cálculos de la cardinalidad de cada letra.

                for (int i = 0; i < MAX_LETRAS; i++) { // Aqui procederíamos a imprimir la respuesta
                    int pos = cp.obtenerPos(aux.getPalabra()[i]);// Se obtiene la posicion de la letra introducida por
                                                                 // el usuario
                    if (pos >= 0) { // Si esto es false, tengo que poner por pantalla []
                        int cardLetra = cp.getCardinalidadLetras()[pos].getCardinalidad(); // Guarda la cardinalidad de
                                                                                           // la letra que va en la
                                                                                           // posición
                        if (!getLetrasEncontradas()[i]) {
                            if (cardLetra > 0) { // Si la letra es parcialmente correcta
                                respuesta += "(" + aux.getPalabra()[i] + ") ";
                                cp.disminuirCardinalidad(aux.getPalabra()[i]);
                            } else
                                respuesta += "[] "; // Si la letra es parcialmente correcta pero ya se ha repetido el
                                                    // numero máximo de veces (cardinalidad)
                        } else
                            respuesta += aux.getPalabra()[i] + " "; // Si la letra es totalmente correcta (lugar)
                    } else
                        respuesta += "[] ";
                }
                getIntento().actualizarIntento();
                respuesta += "\nTe quedan " + getIntento().toString() + " intentos";
            }
        }

        else {
            // Creamos los dos tipos de pista
            PistaLetra p1 = new PistaLetra();
            PistaPalabra p2 = new PistaPalabra();

            if (palabra.length() == 1) {// En caso de que se untroduzca solo un caracter, se comprueba si es un 1
                boolean b;
                if (palabra.equals("1")) {
                    b = p1.obtenerPista(getJugador(), getPalabraOculta(), getIntento(), getLetrasEncontradas());
                    if (b){
                        getJugador().setPuntos(getPuntos()-COSTE_PISTALETRA);
                        setPistas(getPistas()+1);
                    }
                }

                if (palabra.equals("2")) {
                    setGanada(p2.obtenerPista(getJugador(), getPalabraOculta(), getIntento(), getLetrasEncontradas()));
                    if (isGanada()){
                        getJugador().setPuntos(getPuntos()-COSTE_PISTAPALABRA);
                        setPistas(getPistas()+1);
                    }
                }

            } else {
                respuesta = "No se ha introducido una palabra de 5 letras. Inserte una válida";
            }
        }
        return respuesta;

    }

    private void mostrarPalabraOculta() {
        System.out.println("Vaya! Has alcanzado el número máximo de intentos.\nLa palabra oculta" +
                " era: " + getPalabraOculta().toString());
    }

    private void mostrarLeyenda() {
        System.out.println(""
                + "Para identificar que letras de la palabra que has introducido"
                + " se encuentran en la palabra oculta, usaremos el siguiente formato:\n\n"
                + "      *  [] -> LETRA INCORRECTA.\n"
                + "      *  (a) -> LA LETRA \"a\" PERTENECE A LA PALABRA PERO NO ESTA EN EL LUGAR CORRECTO.\n"
                + "      *  a -> LETRA CORRECTA.\n\n"
                + "Por ejemplo, si la palabra oculta es \"tapas\" y se introduce \"patos\",\n"
                + "se mostrará el siguiente mensaje:\n\n"
                + "      (p) a (t) [] s\n\n"
                + "Palabra incorrecta. Quedan n intentos.\n\n"
                + "Hay 2 tipos de pistas.\n"
                + "Si introduces \"1\" obtendrás una letra de la palabra y se te restarán 2 puntos.\n" 
                + "Si introduces \"2\" obtendras la resolución de la palabra y se te restarán 5 puntos.\n\n");
    }

    private void marcarGanada() {
        for (int i = 0; i < MAX_LETRAS; i++) {
            letrasEncontradas[i] = true;
        }
    }

    private boolean contieneLetra(char c) {
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && (i <= 4)) {
            encontrado = (c == getPalabraOculta().getPalabra()[i]);
            if (!encontrado)
                i++;
        }
        return encontrado;
    }

    @Override
    public boolean equals(Object o) { // Son iguales dos partidas si tienen al mismo jugador y palabra.
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        PartidaPalabra pp = (PartidaPalabra) o;
        return pp.getPalabraOculta().equals(getPalabraOculta()) &&
                pp.getJugador().equals(getJugador());
    }
}