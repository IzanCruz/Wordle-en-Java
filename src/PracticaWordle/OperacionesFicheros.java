
package PracticaWordle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OperacionesFicheros {

    // Atributes
    private BufferedReader br;
    private static final int NUM_PALABRAS = 990;
    private static final String FICHEROPALABRAS = "PalabrasWordleDefinitivo.txt";

    // Constructors
    public OperacionesFicheros() {
    }

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

    public int getNLINEAS() {
        return NLINEAS;
    }

    public void guardarSistema(Wordle sistema) {
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
    }
}