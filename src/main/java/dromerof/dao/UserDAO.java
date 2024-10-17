package dromerof.dao;

import dromerof.database.DbConnection;
import dromerof.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    static String cantCreateUser = "\n no se pudo crear el usuario \n";
    static String createdUser = "\n usuario creado, ahora puedes iniciar sesión \n";
    static String cantListUsers = "\n no se pudo listar los usuarios \n";
    static String updatedUser = "\n usuario actualizado \n";
    static String cantUpdateUser = "\n no se pudo actualizar el usuario \n";
    static String loginFailed = "login failed";
    static String dbAuthFailed = "\n no se pudo autenticar con el servidor \n";

    public static void createUserOnDB(User user){
        DbConnection dbConnection = new DbConnection();
        try (Connection connection = dbConnection.get_connection()) {
            PreparedStatement ps=null;
            try {
                String query="insert into usuarios(correo,clave,nombre_completo) values (?,?,?)";
                ps=connection.prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.executeUpdate();
                System.out.println(createdUser);
            } catch (SQLException e) {
                System.out.println(cantCreateUser);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
