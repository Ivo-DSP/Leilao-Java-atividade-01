
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
        Connection conn = null;
        PreparedStatement st;
        ResultSet rs;
    
    public Connection connectDB() throws ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","123456"); 
          //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?user=root&password=123456");
          //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix","root", "123456");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
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
         System.out.println("Erro ao conectar pt2: " + ex.getMessage());
         return ex.getErrorCode();
        }
     }  
}
