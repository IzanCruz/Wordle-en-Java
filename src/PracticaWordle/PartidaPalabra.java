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
    //Atributes
    private Jugador jugador;    
    private boolean ganada;
    private Palabra palabraOculta;
    private Intento intento;
    private int puntos;
 

    //Constructors
    public PartidaPalabra(Jugador j, Palabra p) {
        if (j!= null) jugador = j;
        if (p != null) palabraOculta = p;        
    }
        
    //Methods           
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

    public int getPuntos(){
        return puntos;        
    }
    
    //Methods
    public void darPuntos() {
        puntos = intento.getNumIntento();
    }

    public void resolver() {
        int i=0;
        while (i < intento.getMaxIntentos()) {
            Scanner s = new Scanner(System.in);
            String entrada = s.next();
            System.out.println(comprobarPalabra(entrada));
        }
    }

    private String comprobarPalabra(String palabra) {
        String respuesta=""; //Respuesta al usuario sobre el estado de la palabra.

        if (palabra.length() == 5) {
            Palabra aux = new Palabra(palabra);

            if (getPalabraOculta().equals(aux)) {
                respuesta = "Felicidades! Has adivinado la palabra: " + getPalabraOculta();
                puntos = intento.getNumIntento();
                ganada = true;
            } else {
                for (int i = 0; i <= 4; i++) {
                    if (palabraOculta.getPalabra()[i] == aux.getPalabra()[i]) { //En caso de que la letra sea correcta
                        respuesta+= aux.getPalabra()[i] + " ";
                    } else {    
                        if (contieneLetra(aux.getPalabra()[i])) {
                            respuesta+= "(" + aux.getPalabra()[i] + ") ";
                        } else respuesta+= "[] ";                 
                    }
                }return respuesta;
            }

        } else {
            respuesta ="No se ha introducido una palabra de 5 letras. Inserte una válida";            
        }
        return "";
    } 

    private boolean contieneLetra(char c){
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && (i <= 4)) {
            encontrado = (c == getPalabraOculta().getPalabra()[i]);
            if (!encontrado) i++;
        }
        return encontrado;
    } 


}

/*
 *  [] -> letra incorrecta
 *  (a) -> la letra pertenece a la palabra pero no esta en el lugar correcto
 *  a -> letra correcta
 * 
 * palabra ocutta: patas
 * 
 *  tapas
 * 
 *  Incorrecto. Te quedan 4 intentos. 
 * 
 *  Letras incorrectas -> 
 * 
 *  (t) a (p) a s
 * 
 */