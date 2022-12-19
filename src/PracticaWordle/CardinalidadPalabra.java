/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaWordle;

/**
 *
 * @author santi
 */
public class CardinalidadPalabra {

    private CardinalidadLetras[] cl;
    private int cont;
    private static final int MAX_LETRAS = 5;

    public CardinalidadPalabra() {
        cl = new CardinalidadLetras[MAX_LETRAS];
    }    

    public void calcularCardinalidad(Palabra palabra) {        
        char [] p = palabra.getPalabra();
        while (cont < MAX_LETRAS) {            
            char c = p[cont];
            if (!existeLetra(c)) 
                cl[cont] = new CardinalidadLetras(c);           
            else 
                cl[obtenerPos(c)].aumentarCardinalidad();        
            cont++;                      
        }
    }

    public int obtenerPos(char c) {
        boolean encontrado = false;
        int i = 0;
        int pos = 0;
        while (i < cont && !encontrado) {
            encontrado = cl[i].getLetra() == c;
            if (!encontrado)
                i++;
                pos = i;
        }
        return pos;
    }

    public void disminuirCardinalidad(char c) {
        int pos = obtenerPos(c);
        cl[pos].disminuirCardinalidad();
    }

    private boolean existeLetra(char c) {
        boolean encontrado = false;
        int i = 0;
        CardinalidadLetras letra = new CardinalidadLetras(c);
        while (!encontrado && i <= cont) {
            encontrado = letra.equals(cl[i]);
            if (!encontrado)
                i++;
        }
        return encontrado;
    }

    public CardinalidadLetras[] getCardinalidadLetras(){
        return cl;
    }    

}
