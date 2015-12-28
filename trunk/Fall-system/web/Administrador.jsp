<%-- 
    Document   : administrador
    Created on : 26/05/2013, 14:11:49
    Author     : LAILLA
--%>

<%@page import="util.Util"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (Util.temPrivilegioAdministrador(usuario)) {
%>


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Area Administrador</title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="css/link.css" rel="stylesheet" type="text/css" />
        <!--[if lt IE 8]>
             <script src ="http://ie7-js.googlecode.com/svn/version/2.1(beta2)/IE8.js"></script>
     <![endif]-->
    </head>
    <body>    
        <div class="content">
            <!-- START TOPO -->
            <div id="logo"><img src="imagens/logo.png" width="254" height="70" /></div>
            <div id="divclear"></div><div id="space01"></div>
            <div id="cont-bg">    <body>
                    <ul id="nav">
                        <li><a class="linkfooter" href="ServletVenda?operacao=listaProdutos">Venda</a></li>
                        <li><a class="linkfooter" href="ServletPrestacao?operacao=listaProdutos">Prestação de Serviço</a></li>
                        <li><a class="linkfooter" href="#">Cliente</a>
                            <ul>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Novo Cadastro</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Alterar Cadastro</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Excluir Cadastro</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Relatorios</div></a>
                                    <ul>
                                        <li><a class="linkfooter" href="#"><div id="bg_submenu">Salarios Mensal</div></a><div id="space03"></div></li>	
                                    </ul>
                                </li>
                            </ul></li>
                        <li><a class="linkfooter" href="#">Funcionario</a>
                            <ul>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Novo Cadastro</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Alterar Cadastro</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Excluir Cadastro</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Relatorios</div></a>
                                    <ul>
                                        <li><a class="linkfooter" href="#"><div id="bg_submenu">Salarios Mensal</div></a><div id="space03"></div></li>	
                                    </ul>
                                </li>
                            </ul></li>
                        <li><a class="linkfooter" href="#">Estoque</a>
                            <ul>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Novo Produto</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Alterar Produto</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Excluir Produto</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Relatorios</div></a>
                                    <ul>
                                        <li><a class="linkfooter" href="#"><div id="bg_submenu">Salarios Mensal</div></a><div id="space03"></div></li>	
                                    </ul>
                                </li>
                            </ul></li>
                        <li><a class="linkfooter" href="#">Fornecedores</a>
                            <ul>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Novo Fornecedor</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Alterar Fornecedor</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Excluir Fornecedor</div></a><div id="space03"></div></li>
                                <li><a class="linkfooter" href="#"><div id="bg_submenu">Relatorios</div></a>
                                    <ul>
                                        <li><a class="linkfooter" href="#"><div id="bg_submenu">Salarios Mensal</div></a><div id="space03"></div></li>	
                                    </ul>
                                </li>
                            </ul></li>
                        <li><a class="linkfooter" href="ServletValidacao?acao=1">Sair</a></li>
                    </ul>                 
            </div>
            <!-- END TOPO -->

            <div id="mail">

                <% String venda = (String) request.getAttribute("vendaConcluida");
                    if (venda != null) {%>
                <div align="center" style="width: 934px;"><img src="imagens/money.png" align="center"  />
                    <br/>
                    <h1>Venda Concluída com Sucesso!</h1>
                    <%} else {
                        String prestacao = (String) request.getAttribute("prestacaoConcluida");
                        if (prestacao != null) {
                    %>
                    <div align="center" style="width: 934px;"><img src="imagens/money.png" align="center"  />
                        <br/>
                        <h1>Agendamento Concluído com Sucesso!</h1>
                        <%}
                            }
                        %>

                    </div>
                </div>


            </div>
            <!-- START FOOTER -->
            <div class="footer">
                <div class="cont-footer">
                    <div class="box-reservado">&copy; TODOS OS DIREITOS RESERVADOS.</div>
                    <div class="box-menu">
                        <div id="botao01"><a class="linkfooter" href="#">Sobre</a></div>
                        <div id="botao01"><a class="linkfooter" href="#">Contato</a></div>
                        <div id="divclear"></div>
                    </div>
                    <div id="divclear"></div>
                </div>
            </div>  
    </body>
</html>
<%    } else {
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
%>
