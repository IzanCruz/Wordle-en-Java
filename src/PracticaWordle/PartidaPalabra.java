/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaWordle;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class PartidaPalabra {
    // Atributes
    private Jugador jugador;
    private boolean ganada;
    private Palabra palabraOculta;
    private Intento intento;
    private int puntos;
    private boolean[] letrasEncontradas;
    private final static int MAX_LETRAS = 5;
    private CardinalidadPalabra cp;

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
            if (intento.getNumIntento() == 0 && !isGanada())
                mostrarPalabraOculta();
        } catch (Exception e) {
            System.err.println("Se para aqui" + e);
        }
    }

    private String comprobarPalabra(String palabra) {
        /*String respuesta = ""; // Respuesta al usuario sobre el estado de la palabra.
        cp = new CardinalidadPalabra();
        if (palabra.length() == MAX_LETRAS) {
            Palabra aux = new Palabra(palabra);
            cp.calcularCardinalidad(getPalabraOculta()); // Se usara para saber cuantas apariciones de cada letra se han
                                                         // adivinado.
            if (getPalabraOculta().equals(aux)) {
                respuesta = "Felicidades! Has adivinado la palabra: " + getPalabraOculta();
                darPuntos();
                setGanada(true);
                marcarGanada();
            } */else {
                for (int i = 0; i < MAX_LETRAS; i++) {
                    int pos = cp.obtenerPos(aux.getPalabra()[i]);
                    if (getPalabraOculta().getPalabra()[i] == aux.getPalabra()[i]) { // En caso de que la letra sea
                                                                                     // correcta
                        letrasEncontradas[i] = true;
                        cp.disminuirCardinalidad(aux.getPalabra()[i]);
                        respuesta += aux.getPalabra()[i] + " ";
                    } else { // En caso de que la letra pertenezca a la palabra pero no este bien colocada.
                        if (contieneLetra(aux.getPalabra()[i])) {
                            // Si contiene letra y además, la cardinalidad es mayor que 0
                            if (cp.getCardinalidadLetras()[pos].getCardinalidad() > 0)
                                respuesta += "(" + aux.getPalabra()[i] + ") ";
                            else
                                respuesta += "[] ";
                        } else // En caso de que la letra no pertenezca a la palabra
                            respuesta += "[] ";
                    }
                    respuesta = " ";
                }
                getIntento().actualizarIntento();
                respuesta += "\nTe quedan " + getIntento().toString() + " intentos";
            }
        } /*else {
            PistaLetra p1 = new PistaLetra();
            PistaPalabra p2 = new PistaPalabra();
            if (palabra.length() == 1) {
                if (palabra.equals("1")) {
                    p1.obtenerPista(getJugador(), isGanada(), getPalabraOculta(), getPuntos(), getLetrasEncontradas());
                    getIntento().actualizarIntento();
                }
                if (palabra.equals("2")) {
                    p2.obtenerPista(getJugador(), isGanada(), getPalabraOculta(), getPuntos(), getLetrasEncontradas());
                    getIntento().setIntento(0);
                }
            } else {
                respuesta = "No se ha introducido una palabra de 5 letras. Inserte una válida";
            }*/
        }
        return respuesta;
    }

    public void comprobarPalabra(String palabra) {
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
            } 
            else {
                for (int i = 0; i < MAX_LETRAS; i++) {
                    int pos = cp.obtenerPos(aux.getPalabra()[i]);
                    if (getPalabraOculta().getPalabra()[i] == aux.getPalabra()[i]) { // En caso de que la letra sea
                                                                                     // correcta
                        letrasEncontradas[i] = true;
                        cp.disminuirCardinalidad(aux.getPalabra()[i]);
                    }
                } //Hace los cálculos de la cardinalidad de cada letra.

                for (int i = 0; i < MAX_LETRAS; i++) { //Aqui procederíamos a imprimir la respuesta
                    int pos = cp.obtenerPos(aux.getPalabra()[i]);//Se obtiene la posicion de la letra introducida por el usuario
                    int cardLetra = cp.getCardinalidadLetras()[pos].getCardinalidad();
                    if (!letrasEncontradas[i]){
                        if (cardLetra > 0){
                            respuesta += "(" + aux.getPalabra()[i] + ")";
                        }
                    }
                }
            }
        }

        else {
            //Creamos los dos tipos de pista
            PistaLetra p1 = new PistaLetra();
            PistaPalabra p2 = new PistaPalabra();
            
            if (palabra.length() == 1) {//En caso de que se untroduzca solo un caracter, se comprueba si es un 1
                
                if (palabra.equals("1")) {
                    p1.obtenerPista(getJugador(), isGanada(), getPalabraOculta(), getPuntos(), getLetrasEncontradas());
                    getIntento().actualizarIntento();
                }
                
                if (palabra.equals("2")) {
                    p2.obtenerPista(getJugador(), isGanada(), getPalabraOculta(), getPuntos(), getLetrasEncontradas());
                    getIntento().setIntento(0);
                }
            } else {
                respuesta = "No se ha introducido una palabra de 5 letras. Inserte una válida";
            }
        }

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
                + "Palabra incorrecta. Quedan n intentos."
                + "");
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