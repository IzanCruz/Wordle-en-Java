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
public class PracticaWordle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Wordle w = new Wordle();
        
        w.registrarJugador("Santiago");
        w.registrarJugador("David");

        w.getListaJugadores().get(0).setPuntos(10);
        w.getListaJugadores().get(1).setPuntos(10);

        w.iniciarPartida("David", "Santiago", 1);


        System.out.println(w.getListaPartidas().get(0).toString());
      /*   PartidaPalabra [][] aux = w.getListaPartidas().get(0).getListaPPalabras();
        for (int j = 0; j<=1; j++){    
            for(int i = 0; i<=w.getListaPartidas().get(0).getNumPalabras()-1; i++){
                System.out.println(aux[j][i].getPalabraOculta().toString());
            }
        }*/
    }
    
}