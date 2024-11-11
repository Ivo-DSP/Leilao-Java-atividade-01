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
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


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
      
            return true;   
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de conexão ao BD" + erro.getMessage());
            return false;
        }
      }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){  // pega todos as posiçoes disponíveis na lista
        String sql = "SELECT * FROM produtos";
        
        try { 
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();
            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutos.add(produto);
            }
            return listaProdutos;
                    
         } catch (Exception e) { 
            System.out.println("lista vazia"); 
            return null; 
         }
        
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
    
    public boolean vender(int id) {
        int status;
        
        try {
            st = conn.prepareStatement("UPDATE produtos SET status='Vendido' WHERE id = ?");
            st.setInt(1, id);
            status = st.executeUpdate();
            //JOptionPane.showMessageDialog(null, "resposta: " + status);

            if(status == 1){ // se encontrou o produto, faça
             return true;   
            } else {
             return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao conectar: " + ex.getMessage());
        }
        
        return false;  
    }
        
}

