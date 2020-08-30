/*
 * Author: Kimler Corey
 */
package simulator;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    private int selected; //stores the menu selection
    private boolean showReferenceString;
    public String selectedReferenceStringBuffer;
    static final String VFRAMES = "01234567";
    static Random rnd = new Random();

    // does the whole menu logic (presentation, validation and returns reult)
    public int doMenu() {

        this.showMenu();
        int token;


        do {
            Scanner scanner = new Scanner(System.in);

            // non integers shouldn't be accespted
            while (!scanner.hasNextInt()) {
                System.out.print("\nOpciones válidas 0-3  ");
                scanner.nextLine();
            }

            token = scanner.nextInt();

            //other integers shouldn't work
            if (token > 6 || token < 0) {
                System.out.print("\nPresiona enter para continuar , solo se permiten opciones de 0-3");
            }

        } while (token > 6 || token < 0);

        this.selected = token;

        // return the valid selection to caller
        return this.selected;
    }

    //menu view
    public void showMenu() {

        String menu = "Menu:\n\n";

        // display the reference string if display is true
        if (this.showReferenceString) {
            menu += "[Current RS:" + this.selectedReferenceStringBuffer + "]\n\n";
        }

        menu += "0 – Exit\n";
        menu += "1 – Ingresar cadena de referencia\n";
        menu += "2 – Simular FIFO\n";
        menu += "3 – Simular LFU\n> ";

        System.out.print(menu);
    }

    // reads in a new reference string from the user
    public void readRS() {

        System.out.println("Ingresa caracteres de 0-7: ");
        Scanner input = new Scanner(System.in);
        //validates the input uses only digits 0-7
        String intext = input.next();
        if (intext.matches("^[0-7]+$")) {
            this.selectedReferenceStringBuffer = intext;
        } else {
            System.out.println("Por favor, introduzca sólo números entre el 0 y el 7, ya que esos son los marcos virtuales validos");
        }
        //this.selectedReferenceStringBuffer
    }

    // generates a new reference string for the user
    public void generateRS() {
        int numberofframes = 0;

        System.out.println("Ingresa la cadena de referencia");

        Scanner scan = new Scanner(System.in);
        try {
            numberofframes = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Has cometido un error.");
        }

        if (numberofframes == 0) {
            this.selectedReferenceStringBuffer = "30620373207423723";
        } else if (numberofframes > 0) {
            this.selectedReferenceStringBuffer = getRandomString(numberofframes);
        } else {
            System.out.println("Error");
        }

    }

    // attempts to toggle the display for reference string
    public void toggleDisplayRS() {
        System.out.println("Ver cadena: ");
        this.showReferenceString = !this.showReferenceString;
    }

    //blocking messages for user menu actions
    public void block(int errMessage) {

        if (errMessage == 1) {
            System.out.println("Primero deves crear una cadena de referencia");
        } else {
            System.out.println("Por favor, cree una cadena de referencia antes de correr el algoritmo");
        }
    }

    //generates a random string or returns the default string 
    public String getRandomString(int len) {

        StringBuilder gen = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            gen.append(VFRAMES.charAt(rnd.nextInt(VFRAMES.length())));
        }
        return gen.toString();
    }
}
