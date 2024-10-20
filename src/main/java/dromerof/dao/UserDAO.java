package dromerof.dao;

import dromerof.database.DbConnection;
import dromerof.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    static String cantCreateUser = "\n no se pudo crear el usuario \n";
    static String createdUser = "\n usuario creado, ahora puedes iniciar sesión \n";
    static String cantListUsers = "\n no se pudo listar los usuarios \n";
    static String updatedUser = "\n usuario actualizado \n";
    static String cantUpdateUser = "\n no se pudo actualizar el usuario \n";
    static String loginFailed = "login failed";
    static String dbAuthFailed = "\n no se pudo autenticar con el servidor \n";

    public static void createUserOnDB(User user) {
        DbConnection dbConnection = new DbConnection();
        try (Connection connection = dbConnection.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "insert into usuarios(correo,clave,nombre_completo) values (?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.executeUpdate();
                System.out.println(createdUser);
            } catch (SQLException e) {
                System.out.println(cantCreateUser);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void listUsersOnDB() {
        DbConnection dbConnection = new DbConnection();
        try (Connection connection = dbConnection.get_connection();) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String query = "select * from usuarios";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.print("\n[ID: " + rs.getString("id_usuario") + " | ");
                    System.out.print("Correo: -" + rs.getString("correo") + " | ");
                    System.out.print("Nombre: " + rs.getString("nombre_completo") + " ] ");
                }
            } catch (SQLException e) {
                System.out.println(cantListUsers);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void editUserOnDB(User usuario) {
        DbConnection dbConnection = new DbConnection();
        try (Connection connection = dbConnection.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "update usuarios set correo = ?, clave = ?, nombre_completo = ? where id_usuario = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getPassword());
                ps.setString(3, usuario.getFullName());
                ps.setInt(4, usuario.getUserId());
                ps.executeUpdate();
                System.out.println(updatedUser);
            } catch (SQLException e) {
                System.out.println(cantUpdateUser);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static User loginDB(User usuario) {
        DbConnection dbConnection = new DbConnection();
        try (Connection connection = dbConnection.get_connection();) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String query = "select * from usuarios where correo=? and clave= ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getPassword());
                rs = ps.executeQuery();
                User login = new User();
                if (rs.next()) {
                    System.out.println("login correcto!");
                    login.setUserId(rs.getInt("id_usuario"));
                    login.setEmail(rs.getString("correo"));
                    login.setFullName(rs.getString("nombre_completo"));
                } else {
                    System.out.println(loginFailed);
                }
                return login;
            } catch (SQLException e) {
                System.out.println(dbAuthFailed);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
