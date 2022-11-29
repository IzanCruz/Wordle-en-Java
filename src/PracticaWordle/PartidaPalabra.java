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

    // Constructors
    public PartidaPalabra(Jugador j, Palabra p) {
        if (j != null)
            jugador = j;
        if (p != null)
            palabraOculta = p;
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
        while (intento.getNumIntento() > 0 && !isGanada()) {//Mientras que el numero de intentos sea mayor que 0
            String entrada = s.next();                      //o no se haya adivinado la palabra.
            System.out.println(comprobarPalabra(entrada));
            if (!isGanada()) //Solo se quita un intento en caso de que no se haya adivinado.
                intento.actualizarIntento();
        }
        s.close();
        if (intento.getNumIntento() == 0)
            mostrarPalabraOculta(); 
    }           

    private void mostrarPalabraOculta() {
        System.out.println("Vaya! Has alcanzado el número máximo de intentos.\nLa palabra oculta" +
        " era: " + getPalabraOculta());
    }

    private void mostrarLeyenda() {
        System.out.println("Para identificar que letras de la palabra que has introducido"
                + " se encuentran en la palabra oculta, usaremos el siguiente formato:/n" +
                "[] -> letra incorrecta\n(a) -> la letra \"a\" pertenece a la palabra pero no esta en lugar correcto"
                + "\na -> letra correcta.\n Por ejemplo, si la palabra oculta es \"patos\" y se introduce \"tapas\","
                + " se mostrará el siguiente mensaje:\n Palabra incorrecta. Quedan n intentos.\n (p) a (t) [] s");
    }

    private String comprobarPalabra(String palabra) {
        String respuesta = ""; // Respuesta al usuario sobre el estado de la palabra.
        if (palabra.length() == 5) {
            Palabra aux = new Palabra(palabra);
            if (getPalabraOculta().equals(aux)) {
                setPuntos(getPuntos() + intento.getNumIntento());
                setGanada(true);
                respuesta = "Felicidades! Has adivinado la palabra: " + getPalabraOculta();                
            } else {
                for (int i = 0; i <= 4; i++) {
                    if (getPalabraOculta().getPalabra()[i] == aux.getPalabra()[i]) { // En caso de que la letra sea
                                                                                     // correcta
                        respuesta += aux.getPalabra()[i] + " ";
                    } else {
                        if (contieneLetra(aux.getPalabra()[i])) {
                            respuesta += "(" + aux.getPalabra()[i] + ") ";
                        } else
                            respuesta += "[] ";
                    }
                }
            }
        } else {
            respuesta = "No se ha introducido una palabra de 5 letras. Inserte una válida";
        }
        return respuesta;
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

}