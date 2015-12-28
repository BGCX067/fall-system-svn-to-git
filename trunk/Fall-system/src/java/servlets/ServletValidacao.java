/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.Dao;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Util;

/**
 *
 * @author Alisson Renan
 */
@WebServlet(name = "ServletValidacao", urlPatterns = {"/ServletValidacao"})
public class ServletValidacao extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletValidacao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletValidacao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final int LOGOUT = 1;
        int acao = Integer.parseInt(request.getParameter("acao"));
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        switch (acao){
            case LOGOUT:
                session.invalidate();
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
            default:
                rd = request.getRequestDispatcher("error404.jsp");
                rd.forward(request, response);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Obtencao de atributos */
        HttpSession session = request.getSession(true);
        String botaoLogin = request.getParameter("botao");
        /*Fim da Obtencao de atributos */

        /*Verificacao do login */
        if (botaoLogin.equalsIgnoreCase("entrar")) {
            String login = request.getParameter("login");
            String senha = Util.encriptaSenha(request.getParameter("senha"));
            Usuario usuario = validaUsuario(login, senha);
            RequestDispatcher rd = null;
            if (usuario != null) {

                if (usuario.getClass().getSimpleName().equalsIgnoreCase("Administrador")) {
                    session.setAttribute("usuario", usuario);
                    rd = request.getRequestDispatcher("Administrador.jsp");
                } else if (usuario.getClass().getSimpleName().equalsIgnoreCase("Tecnico")) {
                    session.setAttribute("usuario", usuario);
                    rd = request.getRequestDispatcher("Tecnico.jsp");
                } else if (usuario.getClass().getSimpleName().equalsIgnoreCase("Vendedor")) {
                    session.setAttribute("usuario", usuario);
                    rd = request.getRequestDispatcher("Vendedor.jsp");
                } else {
                    rd = request.getRequestDispatcher("error404.jsp");

                }
                rd.forward(request, response);
            } else {
                request.setAttribute("invalido", "Login inválido");
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }
    }

    /*Verificacao do login */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";


    }// </editor-fold>

    private Usuario validaUsuario(String login, String senha) {
        List<Usuario> usuarios = new Dao(Usuario.class).list();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equalsIgnoreCase(login)
                    && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }

        return null;
    }
}
