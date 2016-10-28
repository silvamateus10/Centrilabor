
package bd.jee.conexao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class Conexao {

    //Nao devem ser criadas instancias de Concexao fora desta classe.
    private Conexao(){
     PreparedStatement pstmt = null;
    }// método construtor

    static {
        try {
            //Carrega o driver do banco
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco nao encontrado.");
            System.exit( -1);
        }// mensagem de erro de caso não encontrar o driver de banco de dados
    }

    public static Connection getConnection() {
        Connection conn = null;
         
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/controleestoque",
                    "root",
                    ""
                    );
        }// credenciais para acessar o banco de dados
        catch (SQLException ex) {
            System.err.print("Erro ao obter conexao: " + ex.getMessage());
        }// mensagem de erro de caso não conseguir a conexão
        return conn;
    }// fim do método para estabelecer a conexão com Bando de Dados

}// fim da classe Conexão
