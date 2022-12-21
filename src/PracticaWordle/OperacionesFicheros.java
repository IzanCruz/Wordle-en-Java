
package PracticaWordle;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

public class OperacionesFicheros {

    // Atributes
    private BufferedReader br;
    private static final int NUM_PALABRAS = 990;
    private static final String FICHEROPALABRAS = "PalabrasWordleDefinitivo.txt";

    // Constructors
    public OperacionesFicheros() {
    }

    //Getters
    public int getNUM_PALABRAS() {
        return NUM_PALABRAS;
    }

    //Methods
    private void abrirLector() {
        try {
            br = new BufferedReader(new FileReader((FICHEROPALABRAS)));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado." + e.getMessage());
        }
    }

    public String[] obtenerPalabras() {
        String[] palabras = new String[NUM_PALABRAS];
        abrirLector();
        try {
            for (int i = 0; i < NUM_PALABRAS; i++) {
                palabras[i] = br.readLine();
            }
        } catch (IOException ex) {
            System.err.println("Error al leer el fichero.");
        }
        cerrarLector();
        return palabras;
    }

    private void cerrarLector() {
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Error: No se ha podido cerrar el lector. " + e.getMessage());
        }
    }


    /*public void guardarSistema(Wordle sistema) {
        try {
            LocalDateTime fechaAhora = LocalDateTime.now();
            File ficheroBinario = new File("/ficheros/binarios/partida" + fechaAhora.toString() + ".dat");
            ficheroBinario.createNewFile();
            FileOutputStream fos = new FileOutputStream(ficheroBinario);
            ObjectOutputStream escritorObjetos = new ObjectOutputStream(fos);
            escritorObjetos.writeObject(ficheroBinario);                              
        } catch(Exception e) {
            System.err.println("Error al escribir en el archivo.");
        }
    }*/

    public void leerPartidas(Wordle w){
        Partida p;
        try{
            FileInputStream archivo = new FileInputStream("Wordle.dat");
            ObjectInputStream leer = new ObjectInputStream(archivo);

            try{
                while(true){
                    p = (Partida) leer.readObject();
                    w.anadirPartida(p);
                }
            }catch(EOFException e){
                return;
            }
        }catch(Exception e){
            System.err.println("Error al leer el archivo" + e);;
        }
    }

    public void leerJugadores(Wordle w){
        Jugador p;
        try{
            FileInputStream archivo = new FileInputStream("Jugadores.bin");
            ObjectInputStream leer = new ObjectInputStream(archivo);

            try{
                while(true){
                    p = (Jugador) leer.readObject();
                    w.registrarJugador(p);
                }
            }catch(EOFException e){
                return;
            }
        }catch(Exception e){
            System.err.println("Error al leer el archivo" + e);;
        }
    }

    public void guardarPartidas(Wordle w){
        try{
            FileOutputStream archivo = new FileOutputStream("Wordle.dat");
            ObjectOutputStream escribir = new ObjectOutputStream(archivo);

            Object[] aux = w.getListaPartidas().toArray();
            int i = 0;
            while(i < aux.length){
                escribir.writeObject(aux[i]);
                i++;
            }
            escribir.close();
        }catch(Exception e){
            System.err.println("Error al guardar las partidas" + e);
        }
    }

    public void guardarJugadores(Wordle w){
        try{
            FileOutputStream archivo = new FileOutputStream("Jugadores.bin");
            ObjectOutputStream escribir = new ObjectOutputStream(archivo);

            Object[] aux = w.getListaJugadores().toArray();
            int i = 0;
            while(i < aux.length){
                escribir.writeObject(aux[i]);
                i++;
            }
            escribir.close();
        }catch(Exception e){
            System.err.println("Error al guardar las partidas" + e);
        }
    }
}