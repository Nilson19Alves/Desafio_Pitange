/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import br.com.desafio.desafio.Util.Usuario;
import br.com.desafio.desafio.benController.LoginController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nilson Alves
 */
public class LoginConnect {
    
    private static final String URL = "jdbc:hsqldb:file:C:\\JavaEE\\Banco_Dados\\Database";
    private static final String USER = "user";
    private static final String PASSWORD = "user";
    private static Connection conn = null;
	
    public static Connection getConn() {
	try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado..");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
            return conn;
	}
		
    public static Usuario verificar(String email, String senha) {
        Usuario usuario = new Usuario();
	boolean res = false;
	String sql = "SELECT * FROM usuario WHERE Email = ? AND Senha = ? AND Admin = true;";
	try {
            PreparedStatement preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
			
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
		
                usuario.setId(resultSet.getInt("ID"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuario.setAdmin(resultSet.getBoolean("Admin"));
                LoginController controller = new LoginController();
                controller.setUsuario(usuario);
                
                res = true;
            }
			
	} catch (SQLException e) {
            e.printStackTrace();
	}
		
	return usuario;
    }
}
