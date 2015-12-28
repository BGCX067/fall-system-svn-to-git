/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.FormaPagamentoDao;
import daos.ParcelaDao;
import entidades.FormaPagamento;
import entidades.Parcela;
import entidades.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
@WebServlet(name = "ServletVendaPagamento", urlPatterns = {"/ServletVendaPagamento"})
public class ServletVendaPagamento extends HttpServlet {

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
            out.println("<title>Servlet ServletVendaPagamento</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletVendaPagamento at " + request.getContextPath() + "</h1>");
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
        if (operacao.equalsIgnoreCase("Escolher")){
            String formaPagamento = request.getParameter("formaPagamento");
            if (formaPagamento.equals("1")){
                Venda venda = (Venda) session.getAttribute("venda");
                venda.setDataVenda(new Date());
                Parcela parcela = new Parcela(new Date(), venda.getValorTotal());
                parcela.setId((long)Math.random()*5);
                new ParcelaDao<Parcela>(Parcela.class).insert(parcela);
                ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
                parcelas.add(new ParcelaDao<Parcela>(Parcela.class).get(parcela.getId()));                                
                session.removeAttribute("aPrazo");
                FormaPagamento formaPagamentos = new FormaPagamento(1, 1, (ArrayList<Parcela>) parcelas);
                formaPagamentos.setId((long)Math.random()*5);
                new FormaPagamentoDao<FormaPagamento>(FormaPagamento.class).insert(formaPagamentos);
                venda.setFormaPagamento(new FormaPagamentoDao<FormaPagamento>(FormaPagamento.class).get(formaPagamentos.getId()));
                session.setAttribute("venda", venda);
                session.setAttribute("parcelas", parcelas);
                request.setAttribute("definiuPagamento", "sim");   
                RequestDispatcher rd = request.getRequestDispatcher("pagamentoVenda.jsp");
                rd.forward(request, response);
            } else if (formaPagamento.equalsIgnoreCase("2")){
                session.removeAttribute("parcelas");
                session.setAttribute("aPrazo", "sim");
                RequestDispatcher rd = request.getRequestDispatcher("pagamentoVenda.jsp");
                rd.forward(request, response);
            }
        } else if(operacao.equalsIgnoreCase("Definir Forma a Prazo")){
            Venda venda = (Venda) session.getAttribute("venda");
            venda.setDataVenda(new Date());
            String pagamentoPrazo = request.getParameter("pagamentos");
            if (pagamentoPrazo.equalsIgnoreCase("1")){                
                List<Parcela> parcelas = util.Util.criaParcelasTipo1(venda.getValorTotal(), venda.getDataVenda());
                FormaPagamento formaPagamento = new FormaPagamento(1, 1, (ArrayList<Parcela>) parcelas);
                formaPagamento.setId( new Random().nextLong());
                new FormaPagamentoDao<FormaPagamento>(FormaPagamento.class).insert(formaPagamento);
                venda.setFormaPagamento(new FormaPagamentoDao<FormaPagamento>(FormaPagamento.class).get(formaPagamento.getId()));
                venda.setFormaPagamento(formaPagamento);
                session.setAttribute("venda", venda);
                session.setAttribute("parcelas", parcelas);
                request.setAttribute("definiuPagamentoPrazo", "sim");   
                RequestDispatcher rd = request.getRequestDispatcher("pagamentoVenda.jsp");
                rd.forward(request, response);
            } else if (pagamentoPrazo.equalsIgnoreCase("2")){
                List<Parcela> parcelas = util.Util.criaParcelasTipo2(venda.getValorTotal(), venda.getDataVenda());
                FormaPagamento formaPagamento = new FormaPagamento(1, 1, (ArrayList<Parcela>) parcelas);
                formaPagamento.setId( new Random(1000).nextLong());
                new FormaPagamentoDao<FormaPagamento>(FormaPagamento.class).insert(formaPagamento);
                venda.setFormaPagamento(new FormaPagamentoDao<FormaPagamento>(FormaPagamento.class).get(formaPagamento.getId()));
                venda.setFormaPagamento(formaPagamento);
                session.setAttribute("venda", venda);
                session.setAttribute("parcelas", parcelas);
                request.setAttribute("definiuPagamentoPrazo", "sim");   
                RequestDispatcher rd = request.getRequestDispatcher("pagamentoVenda.jsp");
                rd.forward(request, response);
            }
        } else if(operacao.equalsIgnoreCase("Continuar")){            
            RequestDispatcher rd = request.getRequestDispatcher("confirmacaoVenda.jsp");
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
