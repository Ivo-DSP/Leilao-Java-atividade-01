/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ProdutosDAO {
    
    PreparedStatement st;
    ResultSet rs;
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean connectDB() throws ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","123456"); 
          //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?user=root&password=123456");
          //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix","root", "123456");
            return true;   
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de conexão ao BD" + erro.getMessage());
            return false;
        }
      }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    public int salvar(ProdutosDTO produt){ 
        int status;
        try { 
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setString(1,null);     
            st.setString(2,produt.getNome());
            st.setInt(3,produt.getValor());
            st.setString(4,produt.getStatus());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
         System.out.println("Erro ao salvar no BD: " + ex.getMessage());
         return ex.getErrorCode();
        }
     }  
    
        
}

