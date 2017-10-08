package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase lectora y parseadora de ficheros.
 *
 * @author 
 */
public class Parser {
    private Scanner atributos;
    private Scanner valores;

    public Parser() {
        try {
            atributos = new Scanner(new File("AtributosJuego.txt"));
        } catch (FileNotFoundException ex) {
        }
        try {
            valores = new Scanner(new File("Juego.txt"));
        } catch (FileNotFoundException ex) {
        }
    }

    public String[] lecturaAtributos() {
        return atributos.nextLine().split(",");
    }

    public String[] lecturaValores(){
        return valores.nextLine().split(",");
    }
    
    public boolean hayValores(){
        return valores.hasNext();
    }
}
