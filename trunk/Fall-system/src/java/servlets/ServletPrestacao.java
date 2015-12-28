/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.PrestacaoServicoDao;
import daos.VendaDao;
import entidades.PrestacaoServico;
import entidades.Tecnico;
import entidades.Usuario;
import entidades.Venda;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author LAILLA
 */
@WebServlet(name = "ServletPrestacao", urlPatterns = {"/ServletPrestacao"})
public class ServletPrestacao extends HttpServlet {

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
            out.println("<title>Servlet ServletPrestacao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletPrestacao at " + request.getContextPath() + "</h1>");
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
//        String operacao = request.getParameter("operacao");
//        if (operacao.equalsIgnoreCase("listaProdutos")) {
//            List<Venda> produtos = new VendaDao<Venda>(Venda.class).list();
//            session.setAttribute("vendasLista", produtos);
//            RequestDispatcher rd = request.getRequestDispatcher("prestacao.jsp");
//            rd.forward(request, response);
//        }
          String remover = request.getParameter("excluir");
          session.removeAttribute("prestacao");
          RequestDispatcher rd = request.getRequestDispatcher("prestacao.jsp");
          rd.forward(request, response);
        
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
            
        } else if(operacao.equalsIgnoreCase("Escolher Prestação")){
            int idRequest = Integer.parseInt(request.getParameter("prestacoes"));
            long id = (long) idRequest;
            session.setAttribute("prestacao", new PrestacaoServicoDao<PrestacaoServico>(PrestacaoServico.class).get(id));
            RequestDispatcher rd = request.getRequestDispatcher("prestacao.jsp");            
            rd.forward(request, response);
        }else if (operacao.equalsIgnoreCase("Adicionar Produtos")) {
            List<Venda> produtos = (List<Venda>) session.getAttribute("vendasLista");
            ArrayList<Venda> produtosRetorno = new ArrayList<Venda>();
            ArrayList<Venda> produtosJaAdicionados = new ArrayList<Venda>();
            ArrayList<Venda> verificadorJaAdicionados = (ArrayList<Venda>) session.getAttribute("jaAdicionados");
            if ((verificadorJaAdicionados != null)) { //se ja foram adicionados produtos 
                for (Venda produto : verificadorJaAdicionados) {
                    int quantidade = Integer.parseInt(request.getParameter("qtdeVenda" + produto.getId()));
                    session.setAttribute("quantidade" + produto.getId(), quantidade);
                }
                produtosJaAdicionados = verificadorJaAdicionados;
            }
            for (Venda produto : produtos) {
                String checkbox = request.getParameter("" + produto.getId());
                if (checkbox.equalsIgnoreCase("on")) {
                    produtosRetorno.add(new VendaDao<Venda>(Venda.class).get(produto.getId()));
                    session.setAttribute("quantidade" + produto.getId(), 1);
                    produtosJaAdicionados.add(produto);
                }
            }

            session.setAttribute("jaAdicionados", produtosJaAdicionados);
            session.setAttribute("produtosNaVenda", produtosRetorno);
            RequestDispatcher rd = request.getRequestDispatcher("prestacao.jsp");
            rd.forward(request, response);
        } else if(operacao.equalsIgnoreCase("Processar Tecnico")){
            String dataPrestacao = request.getParameter("dataPrestacao");
            String horarioInicio = request.getParameter("horarioInicioPrestacao");
            String especialidade = request.getParameter("especialidade");
            Tecnico tecnico = util.Util.processaTecnico(util.Util.stringToDate(dataPrestacao), util.Util.stringParaTempo(horarioInicio), especialidade);
            session.setAttribute("tecnicoEscolhido", tecnico);
            session.setAttribute("escolheu", "sim");
            session.setAttribute("dataPrestacao", dataPrestacao);
            session.setAttribute("horarioInicio", horarioInicio);
            RequestDispatcher rd = request.getRequestDispatcher("prestacao.jsp");
            rd.forward(request, response);
        } else if(operacao.equalsIgnoreCase("Agendar Prestação")){
            Date data = util.Util.stringToDate((String)session.getAttribute("dataPrestacao"));
            Date horario = util.Util.stringParaTempo((String)session.getAttribute("horarioInicio"));
            PrestacaoServico prestacao = (PrestacaoServico) session.getAttribute("prestacao");
            prestacao.setDataPrestacao(data);
            prestacao.setHorarioInicio(horario);
            prestacao.setTecnico((Tecnico)session.getAttribute("tecnicoEscolhido"));
            new PrestacaoServicoDao<PrestacaoServico>(PrestacaoServico.class).update(prestacao);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            session.invalidate();
            HttpSession novaSessao = request.getSession(true);
            novaSessao.setAttribute("usuario", usuario);
            request.setAttribute("prestacaoConcluida", "sim");
            RequestDispatcher rd = request.getRequestDispatcher("Administrador.jsp");
            rd.forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
