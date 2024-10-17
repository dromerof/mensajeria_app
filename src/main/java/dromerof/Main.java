package dromerof;


import dromerof.model.User;
import dromerof.service.UserService;

import java.util.Scanner;

public class Main {
    static String mainMenuMessage = ("=================== \n"
            + "\n Mini red social \n"
            + "1. Registrarse \n"
            + "2. Iniciar sesión \n"
            + "3. salir \n");

    public static void main(String[] args) {
        //create an instance of Scanner class to read user input
        Scanner scanner = new Scanner(System.in);
        iuMenu(scanner);
        scanner.close();

    }
    public static void iuMenu(Scanner sc){

        int option = 0;

        //First Menu
        do{
            System.out.println(mainMenuMessage);
            //Read user input
            option = sc.nextInt();

            switch (option){
                case 1:
                    UserService.createUser(sc);
                    break;
                case 2:
                    User resultado = UserService.login(sc);
                    if(resultado.getUserId() > 0){
                        MenuSesion.menuSesion(resultado, sc);
                    }
                    break;
                default:
                    break;
            }
        }while(option != 3);
    }

}