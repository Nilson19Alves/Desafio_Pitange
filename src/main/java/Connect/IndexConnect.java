/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import br.com.desafio.desafio.Util.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilson Alves
 */
public class IndexConnect {
    
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
    
    public static List<Usuario> listUser(int id) {
        List<Usuario> list = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE SuperUser = ?;";
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("ID"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setSenha(resultSet.getString("Senha"));
                
                list.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IndexConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static boolean remover(Usuario usuario) {
        boolean resultado = false;
        String sqlUser = "DELETE FROM Usuario WHERE ID = ?;"; 
        String sqlTelefone = "DELETE FROM Telefone WHERE idUsuario = ?;"; 
        
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(sqlUser);
            preparedStatement.setInt(1, usuario.getId());
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement2 = getConn().prepareStatement(sqlTelefone);
            preparedStatement2.setInt(1, usuario.getId());
            preparedStatement2.executeUpdate();
            
            resultado = true;
        } catch (SQLException ex) {
            Logger.getLogger(IndexConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
