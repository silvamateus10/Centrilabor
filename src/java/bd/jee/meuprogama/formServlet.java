/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.jee.meuprogama;

import bd.jee.conexao.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.web.WebEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import bd.jee.conexao.Email;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author 7370265
 */
public class formServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet formServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet formServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        
   String id = "0"; //request.getParameter("id");
   String assunto = request.getParameter("assunto");
   String nome = request.getParameter("nome");
   String email = request.getParameter("email");
   String mensagem = request.getParameter("mensagem");


       int updateQuery = 0;

         // check if the text box is empty
         if(id!=null && assunto!=null && nome!=null && email!=null && mensagem!=null){
                         // check if the text box having only blank spaces
             if(id!="" && assunto!="" && nome!="" && email!="" && mensagem!="") {
                

        Connection conn = Conexao.getConnection();
       // Statement statement = conn.createStatement();
        PreparedStatement pstmt = null;
        boolean retorno = true;
        try {


             String queryString = "INSERT INTO formulario(id, assunto, email, nome, telefone, mensagem) VALUES (?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(queryString);

                           pstmt.setString(1, id);
                           pstmt.setString(2, assunto);
                           pstmt.setString(3, email);
                           pstmt.setString(4, nome);
                           pstmt.setString(5, email);
                           pstmt.setString(6, mensagem);
                          

            pstmt.executeUpdate();
             if (updateQuery != 0) {
                
                  
             }
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
         
        }
         }
                    
                  response.sendRedirect("contato.html");
        
        
//    Email email = new Email();
        
//    String mailServer = "smtp.gmail.com";   
//    String assunto = request.getParameter("assunto");   
//    String para =  "silvamateus246@gmail.com";   
//    String de = request.getParameter("email");   
//    String mensagem;  
//    mensagem = "Assunto..:" + request.getParameter("assunto") +
//         " "   + "E-mail..:" + request.getParameter("email")   
//         + "  " + "Telefone..:" 
//         + request.getParameter("fone")   
//         + "  " + request.getParameter("mensagem")+ 
//         "\n";  
//    
//        try {  
//            email.sendMail(mailServer, assunto, para, de, mensagem);
//        } catch (MessagingException ex) {
//            Logger.getLogger(formServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        response.sendRedirect("contato.html"); 


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
