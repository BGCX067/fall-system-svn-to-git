/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import daos.ClientePessoaFisicaDao;
import daos.ClientePessoaJuridicaDao;
import daos.ProdutoDao;
import daos.VendaDao;
import entidades.ClientePessoaFisica;
import entidades.ClientePessoaJuridica;
import entidades.Produto;
import entidades.Usuario;
import entidades.Venda;
import entidades.Vendedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "ServletVenda", urlPatterns = {"/ServletVenda"})
public class ServletVenda extends HttpServlet {

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
            out.println("<title>Servlet ServletVenda</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletVenda at " + request.getContextPath() + "</h1>");
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
        if (operacao.equalsIgnoreCase("listaProdutos")) {
            List<Produto> produtos = new ProdutoDao<Produto>(Produto.class).list();
            session.setAttribute("produtosLista", produtos);
            RequestDispatcher rd = request.getRequestDispatcher("pedidoDeVenda.jsp");
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
            ArrayList<Produto> verificadorJaAdicionados = (ArrayList<Produto>) session.getAttribute("jaAdicionados");
            if ((verificadorJaAdicionados != null)) { //se ja foram adicionados produtos 
                for (Produto produto : verificadorJaAdicionados) {
                    int quantidade = Integer.parseInt(request.getParameter("qtdeVenda" + produto.getId()));
                    session.setAttribute("quantidade" + produto.getId(), quantidade);
                }
            }
            String pesquisa = request.getParameter("pesquisaProdutos");
            if (!pesquisa.equals("")) {
                List<Produto> produtos = new ProdutoDao<Produto>(Produto.class).listByNome(pesquisa);
                session.setAttribute("produtosLista", produtos);
            }
            RequestDispatcher rd = request.getRequestDispatcher("pedidoDeVenda.jsp");
            rd.forward(request, response);
        } else if (operacao.equalsIgnoreCase("Adicionar Produtos")) {
            List<Produto> produtos = (List<Produto>) session.getAttribute("produtosLista");
            ArrayList<Produto> produtosRetorno = new ArrayList<Produto>();
            ArrayList<Produto> produtosJaAdicionados = new ArrayList<Produto>();
            ArrayList<Produto> verificadorJaAdicionados = (ArrayList<Produto>) session.getAttribute("jaAdicionados");
            if ((verificadorJaAdicionados != null)) { //se ja foram adicionados produtos 
                for (Produto produto : verificadorJaAdicionados) {
                    int quantidade  = Integer.parseInt(request.getParameter("qtdeVenda" + produto.getId()));
                    session.setAttribute("quantidade" + produto.getId(), quantidade);
                }
                produtosJaAdicionados = verificadorJaAdicionados;
            }
            for (Produto produto : produtos) {
                String checkbox = request.getParameter("" + produto.getId());
                if (checkbox!= null) {
                    produtosRetorno.add(new ProdutoDao<Produto>(Produto.class).get(produto.getId()));
                    session.setAttribute("quantidade" + produto.getId(), 1);
                    produtosJaAdicionados.add(produto);
                }
            }

            session.setAttribute("jaAdicionados", produtosJaAdicionados);
            session.setAttribute("produtosNaVenda", produtosRetorno);
            RequestDispatcher rd = request.getRequestDispatcher("pedidoDeVenda.jsp");
            rd.forward(request, response);

        } else if (operacao.equalsIgnoreCase("Continuar")) {
            String veioDaConfirmacao = (String) session.getAttribute("veioDaConfirmacao");
            if (veioDaConfirmacao != null) {
                RequestDispatcher rd = request.getRequestDispatcher("confirmacaoVenda.jsp");
                rd.forward(request, response);
            } else {
                ArrayList<Produto> produtosAdicionados = (ArrayList<Produto>) session.getAttribute("jaAdicionados");
                double valorTotal = 0;
                Venda venda = new Venda();
                venda.setProdutos((ArrayList<Produto>) session.getAttribute("jaAdicionados"));
                venda.setVendedor((Usuario) session.getAttribute("usuario"));
                venda.setDataVenda(new Date());
                for (Produto produto : produtosAdicionados) {
                    String qtde = request.getParameter("qtdeVenda" + produto.getId() + "");
                    if (qtde != null) {
                        valorTotal += produto.getPreco() * (Integer.parseInt(qtde));
                    }
                }
                String prestacao = request.getParameter("prestacao");
                if (prestacao!=null) {
                    venda.setTemPrestacaoServico(true);
                } else {
                    venda.setTemPrestacaoServico(false);
                }
                venda.setValorTotal(valorTotal);
                session.setAttribute("venda", venda);
                List<ClientePessoaJuridica> clientesJuridicos = new ClientePessoaJuridicaDao().list();
                List<ClientePessoaFisica> clientesFisicos = new ClientePessoaFisicaDao().list();
                session.setAttribute("clientesFisicos", clientesFisicos);
                session.setAttribute("clientesJuridicos", clientesJuridicos);
                session.setAttribute("clienteAdiciona", "nao");
                RequestDispatcher rd = request.getRequestDispatcher("vendaCliente.jsp");
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
