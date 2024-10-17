package dromerof.model;

public class Message {
    int messageId;
    String message;
    String date;
    String fullName;

    public Message() {
    }

    public Message(int messageId, String message, String date, String fullName) {
        this.messageId = messageId;
        this.message = message;
        this.date = date;
        this.fullName = fullName;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
