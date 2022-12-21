/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaWordle;

import java.io.Serializable;

/**
 *
 * @author santi
 */
public class CardinalidadLetras implements Serializable{
    
    private char letra;
    private int cardinalidad;
    private static final int CARDINALIDAD_INICIAL = 1;

    public CardinalidadLetras(char c) {
        letra = c;
        cardinalidad = CARDINALIDAD_INICIAL;
    }

    public char getLetra() {
        return letra;
    }

    public int getCardinalidad() {
        return cardinalidad;
    }

    public void aumentarCardinalidad() {
        cardinalidad++;
    }

    public void disminuirCardinalidad() {
        cardinalidad--;
    }    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardinalidadLetras other = (CardinalidadLetras) obj;
        return getLetra() == other.getLetra();
    }
}
