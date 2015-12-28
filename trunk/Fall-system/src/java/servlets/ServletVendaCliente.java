/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.ClientePessoaFisicaDao;
import daos.ClientePessoaJuridicaDao;
import daos.PessoaDao;
import daos.ProdutoDao;
import entidades.ClientePessoaFisica;
import entidades.ClientePessoaJuridica;
import entidades.Pessoa;
import entidades.Produto;
import entidades.Venda;
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

/**
 *
 * @author Alisson Renan
 */
@WebServlet(name = "ServletVendaCliente", urlPatterns = {"/ServletVendaCliente"})
public class ServletVendaCliente extends HttpServlet {

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
            out.println("<title>Servlet ServletVendaCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletVendaCliente at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String operacao = request.getParameter("operacao");
        if (operacao.equalsIgnoreCase("excluir")) {
            session.removeAttribute("cliente");
            session.removeAttribute("tipoCliente");
            session.setAttribute("clienteAdiciona", "nao");
            RequestDispatcher rd = request.getRequestDispatcher("vendaCliente.jsp");
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
        HttpSession session = request.getSession();
        String operacao = request.getParameter("operacao");
        if (operacao.equalsIgnoreCase("Filtrar")) {
            String pesquisa = request.getParameter("pesquisaProdutos");
            if (!pesquisa.equals("")) {
                List<ClientePessoaJuridica> clientesJuridicos = new ClientePessoaJuridicaDao().listByRazaoSocial(pesquisa);
                List<ClientePessoaFisica> clientesFisicos = new ClientePessoaFisicaDao().listByNome(pesquisa);

                session.setAttribute("clientesFisicos", clientesFisicos);
                session.setAttribute("clientesJuridicos", clientesJuridicos);
            }
            RequestDispatcher rd = request.getRequestDispatcher("vendaCliente.jsp");
            rd.forward(request, response);
        } else if (operacao.equalsIgnoreCase("Definir Cliente")) {
            List<ClientePessoaJuridica> clientesJuridicos = (List<ClientePessoaJuridica>) session.getAttribute("clientesJuridicos");
            List<ClientePessoaFisica> clientesFisicos = (List<ClientePessoaFisica>) session.getAttribute("clientesFisicos");
            for (ClientePessoaFisica clientePessoaFisica : clientesFisicos) {
                String checkbox = request.getParameter("" + clientePessoaFisica.getId());
                if (checkbox != null) {
                    ClientePessoaFisica clienteRetorno = new ClientePessoaFisica();
                    clienteRetorno = new ClientePessoaFisicaDao().get(clientePessoaFisica.getId());
                    session.setAttribute("cliente", clienteRetorno);
                    session.setAttribute("tipoCliente", "fisico");
                    session.setAttribute("clienteAdiciona", "sim");
                    RequestDispatcher rd = request.getRequestDispatcher("vendaCliente.jsp");
                    rd.forward(request, response);
                }
            }

            for (ClientePessoaJuridica clientePessoaJuridica : clientesJuridicos) {
                String checkbox = request.getParameter("" + clientePessoaJuridica.getId());
                if (checkbox.equalsIgnoreCase("on")) {
                    ClientePessoaJuridica clienteRetorno = new ClientePessoaJuridica();
                    clienteRetorno = new ClientePessoaJuridicaDao().get(clientePessoaJuridica.getId());
                    session.setAttribute("cliente", clienteRetorno);
                    session.setAttribute("clienteAdiciona", "sim");
                    session.setAttribute("tipoCliente", "juridico");
                    RequestDispatcher rd = request.getRequestDispatcher("vendaCliente.jsp");
                    rd.forward(request, response);
                }
            }

        } else if (operacao.equalsIgnoreCase("Proximo")) {
            String veioDaConfirmacao = (String) session.getAttribute("veioDaConfirmacao");
            if (veioDaConfirmacao != null) {
                RequestDispatcher rd = request.getRequestDispatcher("confirmacaoVenda.jsp");
                rd.forward(request, response);
            } else {
                Pessoa cliente = (Pessoa) session.getAttribute("cliente");
                Venda venda = (Venda) session.getAttribute("venda");
                venda.setCliente(cliente);
                session.setAttribute("venda", venda);
                session.setAttribute("pagamentoEscolhido", "nao");
                RequestDispatcher rd = request.getRequestDispatcher("pagamentoVenda.jsp");
                rd.forward(request, response);
            }
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
