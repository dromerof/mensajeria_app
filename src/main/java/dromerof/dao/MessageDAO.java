package dromerof.dao;

import dromerof.database.DbConnection;
import dromerof.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {

    static String cantCreateMessage = "\n no se pudo crear el mensaje \n";
    static String createdMessage = "\n mensaje creado \n";
    static String cantListMessages = "\n no se pudo listar los mensajes \n";
    static String deletedMessage = "\n mensaje eliminado \n";
    static String cantDeleteMessages = "\n no se pudo eliminar el mensaje \n";


    public static void createMessageDB(Message message) {
        DbConnection dbConnection = new DbConnection();
        try (Connection connection = dbConnection.get_connection()) {
            PreparedStatement ps=null;
            try {
                String query="insert into mensajes(id_usuario,mensaje,fecha) values (?,?,CURRENT_TIMESTAMP)";
                ps=connection.prepareStatement(query);
                ps.setInt(1, message.getUserId());
                ps.setString(2, message.getMessage());
                ps.executeUpdate();
                System.out.println(createdMessage);
            } catch (SQLException e) {
                System.out.println(cantCreateMessage);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void listMessageDB() {
        DbConnection dbConnection = new DbConnection();
        try(Connection connection = dbConnection.get_connection()){
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                String query="select m.id_mensaje,m.mensaje,m.fecha,u.nombre_completo from mensajes m join usuarios u on m.id_usuario=u.id_usuario";
                ps=connection.prepareStatement(query);
                rs=ps.executeQuery();
                while(rs.next()){
                    System.out.print("\n[ID: "+rs.getInt("id_mensaje")+" | ");
                    System.out.print("mensaje: -"+rs.getString("mensaje")+" | ");
                    System.out.print("fecha: -"+rs.getString("fecha")+" | ");
                    System.out.print("Autor: "+rs.getString("nombre_completo")+" ] ");
                }
            } catch (SQLException e) {
                System.out.println(cantListMessages);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }

    }

    public static void deleteMessageDB(Message message) {
        DbConnection db_connect = new DbConnection();
        try(Connection connection = db_connect.get_connection()){
            PreparedStatement ps=null;
            try {
                String query="delete from mensajes where id_mensaje = ? and id_usuario = ?";
                ps=connection.prepareStatement(query);
                ps.setInt(1, message.getMessageId());
                ps.setInt(2, message.getUserId());
                ps.executeUpdate();
                System.out.println(deletedMessage);
            } catch (SQLException e) {
                System.out.println(cantDeleteMessages);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }



}
