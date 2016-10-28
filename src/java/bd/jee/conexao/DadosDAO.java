/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.jee.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mateus
 */
public class DadosDAO {

    private static DadosDAO instance;
 
    static{
        instance = new DadosDAO();
    }
    
    public DadosDAO(){
    }
    
    public static DadosDAO getInstance(){
    return instance;
    }
    
    
    public void create(Dados a) throws SQLException{
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
       try{ 
        pstmt = conn.prepareStatement("INSERT INTO aparelho(idaparelho, nome, marca, descricao, precoDeCompra, precoDeVenda) VALUES(?,?,?,?,?,?)");
        
        pstmt.setInt(1, a.getIdaparelho());
        pstmt.setString(2, a.getNome());
        pstmt.setString(3, a.getMarca());
        pstmt.setString(4, a.getDescricao());
        pstmt.setDouble(5, (a.getPrecoDeCompra()));
        pstmt.setDouble(6, (a.getPrecoDeVenda()));
        
        pstmt.executeUpdate();
        conn.close();
       }catch(SQLException erro){
       erro.printStackTrace();
       }
    }
    
    
    public void update(Dados a) throws SQLException{
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
       try{ 
        pstmt = conn.prepareStatement("UPDATE aparelho SET "
                    + "idaparelho = ?, nome = ?, marca = ?, descricao = ?, precoDeCompra = ?, precoDeVenda = ?"
                     + "WHERE idaparelho = " + a.getIdaparelho());
        
        pstmt.setInt(1, a.getIdaparelho());
        pstmt.setString(2, a.getNome());
        pstmt.setString(3, a.getMarca());
        pstmt.setString(4, a.getDescricao());
        pstmt.setDouble(5, (a.getPrecoDeCompra()));
        pstmt.setDouble(6, (a.getPrecoDeVenda()));
        
        pstmt.executeUpdate();
        conn.close();
       }catch(SQLException erro){
       erro.printStackTrace();
       } 
    }
    
    public void delete(Dados a) throws SQLException{
        Connection conn = Conexao.getConnection();
        try{
           Statement stmt = conn.createStatement();
           stmt.executeUpdate("DELETE FROM aparelho WHERE idaparelho = " + a.getIdaparelho());
           conn.close();
        }catch(SQLException err){
        err.printStackTrace();
        }
    }
    public Dados read(Dados a) throws SQLException{
        Dados apAux = new Dados();
        Connection conn = Conexao.getConnection();
        
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM aparelho WHERE nome = " + a.getNome() + " ' " );
            if (rs.next()) {
                apAux = carregaAparelho(a, rs);
            } else {
                apAux = null;
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return apAux;
    }
    
       private Dados carregaAparelho(Dados a, ResultSet rs) throws SQLException {

        Dados apAux = new Dados();
        apAux.setIdaparelho((rs.getInt("idaparelho")));
        apAux.setNome(rs.getString("nome"));
        apAux.setMarca(rs.getString("marca"));
        apAux.setDescricao(rs.getString("descricao"));
        apAux.setPrecoDeCompra((rs.getDouble("precoDeCompra")));
        apAux.setPrecoDeVenda((rs.getDouble("precoDeVenda")));
        return apAux;

    }

}//fim da classe