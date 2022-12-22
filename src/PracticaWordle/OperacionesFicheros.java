
package PracticaWordle;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    private String obtenerFechaYHora(){
        LocalDateTime fechaAhora = LocalDateTime.now();
        String fecha = new String();
        
        fecha += "" + fechaAhora.getDayOfMonth() + ".";
        fecha += "" + fechaAhora.getMonth().getValue() + ".";
        fecha += "" + fechaAhora.getYear() + "-";
        fecha += "" + fechaAhora.getHour() + "-";
        fecha += "" + fechaAhora.getMinute();
        
        return fecha;
    }

    public void crearRanking(ArrayList <Jugador> n){
        Object[] lista = n.toArray();
        String nombreFich = "Ranking." + obtenerFechaYHora() + ".txt";
        File f = new File(nombreFich);

        try{
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            int i = 0;
            while(i < lista.length){
                bw.write(lista[i].toString());
                i++;
            }
            bw.close();
        }catch(Exception e){
            System.err.println("Error al generar el archivo" + e);
        }
        
    }    
        

    public void crearRankingAlfabetico(ArrayList <Jugador> n){
        Object[] lista = n.toArray();
        File f = new File("Listado." + obtenerFechaYHora() + ".txt");

        try{
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            int i = 0;
            while(i < lista.length){
                bw.write(lista[i].toString());
                i++;
            }
            bw.close();
        }catch(Exception e){
            System.err.println("Error al generar el archivo" + e);
        }
    }


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