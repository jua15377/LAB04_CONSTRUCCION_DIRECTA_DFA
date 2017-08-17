import java.util.Scanner;

public class Principal {
    public static void main(String args[]){
        //lee la cadena que contiene la exprexion regular
        Scanner teclado = new Scanner(System.in);
        Creador creador = new Creador();
        String entrada;

        //PEDIR REGEX AL USUARIO
        System.out.println("Ingrese la expresion regular del automata. Notose @ simboliza epsilon");
        entrada = teclado.nextLine();
        //crear arbolito
        creador.analizadorExpresion(entrada);
    }


}
