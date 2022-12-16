/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaWordle;

/**
 *
 * @author santi
 */
public class CardinalidadLetras {
    
    private char letra;
    private int cardinalidad;

    public CardinalidadLetras(char c) {
        letra = c;
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
