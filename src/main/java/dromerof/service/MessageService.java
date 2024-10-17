package dromerof.service;

import dromerof.model.Message;
import dromerof.model.User;
import dromerof.dao.MessageDAO;

import java.util.Scanner;

public class MessageService {
    static String messageTextMessage = "Escribe el mensaje, máx 280 caracteres";
    static String messageIdToBeDeletedMessage = "Indica el id del mensaje a borrar";

    public static void createMessage(User user, Scanner scanner) {
        System.out.println(messageTextMessage);
        String messageText = scanner.next();
        Message message = new Message(messageText, user.getUserId());
        MessageDAO.createMessageDB(message);
    }

    public static void listMessage() {
        MessageDAO.listMessagesDB();

    }

    public static void deleteMessage(User user, Scanner scanner) {
        System.out.println(messageIdToBeDeletedMessage);
        int messageId = scanner.nextInt();
        Message message = new Message(messageId, user.getUserId());
        MessageDAO.deleteMessageDB(message);
    }


}
