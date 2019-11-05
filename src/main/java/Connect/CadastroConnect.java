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
public class CadastroConnect {
    
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
		
    public static boolean novo(Usuario usuario) {
	boolean res = false;
	String sql = "INSERT INTO Usuario (Nome, Email, Senha, Admin, SuperUser) VALUES (?,?,?,?,?);";
	try {
            PreparedStatement preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setBoolean(4, false);
            preparedStatement.setInt(5, 14);
			
            preparedStatement.executeUpdate();
            
            String sqlTelefone = "INSERT INTO Telefone (DDD, Numero, Tipo, idUsuario) VALUES (?,?,?,?);";
            
            PreparedStatement preparedStatement2 = getConn().prepareStatement(sqlTelefone);
            preparedStatement2.setInt(1, usuario.getTelefone().getDdd_01());
            preparedStatement2.setString(02, usuario.getTelefone().getNumero_01());
            preparedStatement2.setString(03, "Celular");
            preparedStatement2.setInt(04, 14);
            preparedStatement2.executeUpdate();
            
            PreparedStatement preparedStatement3 = getConn().prepareStatement(sqlTelefone);
            preparedStatement3.setInt(1, usuario.getTelefone().getDdd_02());
            preparedStatement3.setString(02, usuario.getTelefone().getNumero_02());
            preparedStatement3.setString(03, "Fixo");
            preparedStatement3.setInt(04, 14);
            preparedStatement3.executeUpdate();
            
            res = true;
	} catch (SQLException e) {
            e.printStackTrace();
	}
		
	return res;
    }
}
