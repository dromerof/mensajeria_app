package dromerof.ui;

import dromerof.model.User;
import dromerof.service.MessageService;
import dromerof.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMenu {
    public static void uiMenu(User user, Scanner scanner) {
        int option = 0;
        StringBuilder menuMessage = new StringBuilder();
        menuMessage.append("=================== \n")
                .append("\n red social, hola ").append(user.getFullName()).append("\n")
                .append("1. escribir mensaje \n")
                .append("2. leer mensajes \n")
                .append("3. eliminar mensaje \n")
                .append("4. editar perfil \n")
                .append("5. ver usuarios \n")
                .append("6. cerrar sesión \n");

        String finalMessage = menuMessage.toString();
        do {
            System.out.println(menuMessage);
            // Read user input
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        MessageService.createMessage(user, scanner);
                        break;
                    case 2:
                        MessageService.listMessage();
                        break;
                    case 3:
                        MessageService.deleteMessage(user, scanner);
                        break;
                    case 4:
                        UserService.editUser(user, scanner);
                        break;
                    case 5:
                        UserService.listUsers();
                        break;
                    case 6:
                        System.out.println("Cerrando sesión...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
        } while (option != 6);

    }
}
