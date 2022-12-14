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
    private final static int MAX = 5;

    // Constructors
    public PartidaPalabra(Jugador j, Palabra p) {
        letrasEncontradas = new boolean[MAX];
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
        Scanner s = new Scanner(System.in);
        String entrada = null;
        try{
            while (intento.getNumIntento() > 0 && !isGanada()) {//Mientras que el numero de intentos sea mayor que 0 o no se haya adivinado la palabra
                do {
                    entrada = s.next();   
                } while (entrada == null); //Mientas que la palabra introducida no se encuentre entre las palabras del fichero.
                System.out.println(comprobarPalabra(entrada));
            }
            if (intento.getNumIntento() == 0)
                mostrarPalabraOculta();
        }
        catch(Exception e){System.err.println("Se para aqui" + e);}
    }                   

    private String comprobarPalabra(String palabra) {
        String respuesta = ""; // Respuesta al usuario sobre el estado de la palabra.
        if (palabra.length() == 5) {
            Palabra aux = new Palabra(palabra);
            if (getPalabraOculta().equals(aux)) {
                respuesta = "Felicidades! Has adivinado la palabra: " + getPalabraOculta();
                darPuntos();
                setGanada(true);
                marcarGanada();
            } else {
                for (int i = 0; i <= 4; i++) {
                    if (getPalabraOculta().getPalabra()[i] == aux.getPalabra()[i]) { // En caso de que la letra sea correcta
                        letrasEncontradas[i] = true;
                        respuesta += aux.getPalabra()[i] + " ";
                    } else {
                        if (contieneLetra(aux.getPalabra()[i])) {
                            respuesta += "(" + aux.getPalabra()[i] + ") ";
                        } else
                            respuesta += "[] ";
                    }
                }
                intento.actualizarIntento();
                respuesta += "\nTe quedan " + getIntento().toString() + " intentos";
            }
        } else {
            respuesta = "No se ha introducido una palabra de 5 letras. Inserte una válida";
        }
        return respuesta;
    }


    
    private void mostrarPalabraOculta() {
        System.out.println("Vaya! Has alcanzado el número máximo de intentos.\nLa palabra oculta" +
        " era: " + getPalabraOculta().toString());
    }

    private void mostrarLeyenda() {
        System.out.println(""
        +"Para identificar que letras de la palabra que has introducido"
        +"se encuentran en la palabra oculta, usaremos el siguiente formato:\n\n"
        +"      *  [] -> LETRA INCORRECTA.\n"
        +"      *  (a) -> LA LETRA \"a\" PERTENECE A LA PALABRA PERO NO ESTA EN EL LUGAR CORRECTO.\n"
        +"      *  a -> LETRA CORRECTA.\n\n"   
        +"Por ejemplo, si la palabra oculta es \"tapas\" y se introduce \"patos\",\n"
        +"se mostrará el siguiente mensaje:\n\n"
        +"Palabra incorrecta. Quedan n intentos.\n\n"
        +"      (p) a (t) [] s"
        +"");
    }       

    private void marcarGanada() {
        for (int i = 0; i < MAX; i++) {
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
    public boolean equals(Object o) { //Son iguales dos partidas si tienen al mismo jugador y palabra.
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