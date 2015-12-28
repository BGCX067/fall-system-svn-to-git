/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.PrestacaoServicoDao;
import daos.VendaDao;
import entidades.PrestacaoServico;
import entidades.Usuario;
import entidades.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alisson Renan
 */
@WebServlet(name = "ServletVendaConfirmacao", urlPatterns = {"/ServletVendaConfirmacao"})
public class ServletVendaConfirmacao extends HttpServlet {

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
            out.println("<title>Servlet ServletVendaConfirmacao</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletVendaConfirmacao at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        String operacao = request.getParameter("operacao");
        if (operacao.equalsIgnoreCase("Alterar Cliente")){
            session.setAttribute("veioDaConfirmacao", "sim");
            RequestDispatcher rd = request.getRequestDispatcher("vendaCliente.jsp");
            rd.forward(request, response);
        } else if(operacao.equalsIgnoreCase("Alterar Produtos")){
            session.setAttribute("veioDaConfirmacao", "sim");
            RequestDispatcher rd = request.getRequestDispatcher("pedidoDeVenda.jsp");
            rd.forward(request, response);
        } else if(operacao.equalsIgnoreCase("Alterar Forma de Pagamento")){
            session.setAttribute("veioDaConfirmacao", "sim");
            RequestDispatcher rd = request.getRequestDispatcher("pedidoDeVenda.jsp");
            rd.forward(request, response);
        } else if(operacao.equalsIgnoreCase("Finalizar Venda")){
            VendaDao vendaDao = new VendaDao(Venda.class);
            vendaDao.insert((Venda)session.getAttribute("venda"));
            Venda venda = (Venda) session.getAttribute("venda");
            if(venda.isTemPrestacaoServico()){
                PrestacaoServico prestacaoServico = new PrestacaoServico();
                prestacaoServico.setVenda(venda);
                prestacaoServico.setDataPrestacao(null);
                prestacaoServico.setHorarioInicio(null);
                prestacaoServico.setHorarioTermino(null);
                new PrestacaoServicoDao<PrestacaoServico>(PrestacaoServico.class).insert(prestacaoServico);
            }
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            session.invalidate();
            session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            request.setAttribute("vendaConcluida", "sim");
            RequestDispatcher rd = request.getRequestDispatcher("Administrador.jsp");
            rd.forward(request, response);            
        }
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
