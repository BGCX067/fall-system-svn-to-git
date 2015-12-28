<%@page import="entidades.ClientePessoaJuridica"%>
<%@page import="entidades.ClientePessoaFisica"%>
<%@page import="entidades.Pessoa"%>
<%@page import="entidades.Produto"%>
<%@page import="java.util.List"%>
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
        <title>Area Vendedor</title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="css/link.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
        <!--[if lt IE 8]>
             <script src ="http://ie7-js.googlecode.com/svn/version/2.1(beta2)/IE8.js"></script>
     <![endif]-->
        <!--
        texto input
        -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
        <script type="text/javascript">
            function trim(str) {
                return str.replace(/^\s+|\s+$/g, "");
            }

            jQuery.fn.limpa = function(settings) {
                var $this = jQuery(this);
                var msg = $this.val();

                $this.focus(function() {
                    if ($this.val() == msg)
                        $this.val('');
                });
                $this.blur(function() {
                    if ($this.val() == '')
                        $this.val(msg);
                });

                return $this;
            }
            $(document).ready(function() {
                $("input[name='pesquisaProdutos']").limpa();
            });
        </script>
    </head>
    <body>    
        <div class="content">
            <!-- START TOPO -->
            <div id="logo"><img src="imagens/logo.png" width="254" height="70" /></div>
            <div id="divclear"></div><div id="space01"></div>
            <div id="cont-bg">    <body>
                    <ul id="nav">
                        <li><a class="linkfooter" href="#">Venda</a></li>
                        <li><a class="linkfooter" href="#">Alterar Dados Pessoais</a></li>

                        <li><a class="linkfooter" href="ServletValidacao?acao=1">Sair</a></li>
                    </ul>                 
            </div>
            <!-- END TOPO -->
            <!-- SART mail -->
            <div id="space04"></div>            
            <div id="status">
                <img src="imagens/status02.png" alt="" width="350" height="60" border="0" />
            </div>

            <div id="mail">
                <hr></hr>
                <div id="space04"></div>
                <div id="font01">Pedido de Venda</div>
                <hr />

                <!-- START prestação -->
                <div class="form-prestacao">
                    <div id="space04"></div>
                    <form name="venda" method="post" action="ServletVendaCliente">
                        <%
                            String clienteConfirmado = (String) session.getAttribute("clienteAdiciona");
                            if (!clienteConfirmado.equalsIgnoreCase("sim")) {
                        %>
                        <div id="font05">Definir Cliente</div>

                        <div id="space04"></div>
                        <input class="redonda" id="pesquisaProdutos" type="text" name="pesquisaProdutos" value="Pesquisar Clientes..." size="50"/>
                        <input type="submit" id="buttonPesquisar"  value="Filtrar" name="operacao" />
                        <div id="space04"></div>
                        <div id="space04"></div>
                        <div id="font05">Clientes Cadastrados</div>
                        <div class="bg_box1"></div>
                        <div class="bg_box3">
                            <div id="conteudo-box">
                                <div id="listaProdutos">
                                    <table id="listagemProdutos" class="fundotab" align="center" width="934" border="1" cellpadding="0" cellspacing="0" style="border-color: whitesmoke;">
                                        <tr>
                                            <th align="left" width="30">&nbsp;</th>
                                            <th align="left" width="600">Nome/Razão Social</th>
                                            <th align="left" width="200">CPF/CNPJ</th>
                                        </tr>

                                        <% List<ClientePessoaFisica> clientesFisicos = (List<ClientePessoaFisica>) session.getAttribute("clientesFisicos");
                                            List<ClientePessoaJuridica> clientesJuridicos = (List<ClientePessoaJuridica>) session.getAttribute("clientesJuridicos");

                                            if (clientesFisicos != null && clientesJuridicos != null) {
                                                for (ClientePessoaFisica clienteFisico : clientesFisicos) {


                                        %>
                                        <tr>

                                            <td width="30"> <input type="checkbox" name="<%=clienteFisico.getId()%>" /> </td>
                                            <td width="600"> <%=clienteFisico.getNome()%> </td>
                                            <td width="100" align="rigth"><%=clienteFisico.getCpf()%> </td>                                    
                                            <%}

                                                for (ClientePessoaJuridica clienteJuridico : clientesJuridicos) {


                                            %>
                                            <tr>

                                                <td width="30"> <input type="checkbox" name="<%=clienteJuridico.getId()%>" /> </td>
                                                <td width="600"> <%=clienteJuridico.getRazaoSocial()%> </td>
                                                <td width="100" align="rigth"><%=clienteJuridico.getCnpj()%> </td>                                    
                                                <%}

                                                %>

                                            </tr>

                                            <%

                                                }

                                            %>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="bg_box2"></div>
                        <div id="space04"></div>
                        <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Definir Cliente" name="operacao" /></div>
                        <%}%>
                        <div id="space04"></div>
                        <%
                            boolean confirma = false;
                            String clienteJaConfirmado = (String) session.getAttribute("tipoCliente");
                            if (clienteJaConfirmado != null) {
                                confirma = true;
                                if (clienteJaConfirmado.equalsIgnoreCase("fisico")) {
                                    ClientePessoaFisica cliente = (ClientePessoaFisica) session.getAttribute("cliente");%>
                        <div id="font05">Cliente definido</div><div id="space04"></div>
                        <div class="bg_produto">
                            <table id="produtosVendaT">
                                <tr>           
                                    <th align="left" width="600"> Nome/Razão Social</th>
                                    <th align="left" colspan="2" width="400"> CPF/CNPJ</th>
                                </tr>
                            </table>

                        </div>

                        <div class="bg_produto">
                            <table id="produtosVenda">
                                <tr>                                    
                                    <td align="left" width="600"> <%=cliente.getNome()%></td>
                                    <td align="left" width="200"><%=cliente.getCpf()%></td>                                    
                                    <td width="100" align="right"><a class="linkexcluir" href="ServletVendaCliente?operacao=excluir"> Excluir </a></td>
                                </tr>
                                <%} else if (clienteJaConfirmado.equalsIgnoreCase("juridico")) {
                                    ClientePessoaJuridica cliente = (ClientePessoaJuridica) session.getAttribute("cliente");
                                %>
                                <div id="font05">Cliente definido</div><div id="space04"></div>
                                <div class="bg_produto">
                                    <table id="produtosVendaT">
                                        <tr>           
                                            <th align="left" width="600"> Nome/Razão Social</th>
                                            <th align="left" colspan="2" width="400">CPF/CNPJ</th>

                                        </tr>
                                    </table>

                                </div>

                                <div class="bg_produto">
                                    <table id="produtosVenda">
                                        <tr>                                    
                                            <td align="left" width="600"> <%=cliente.getRazaoSocial()%></td>
                                            <td align="left" width="200">R$ <%=cliente.getCnpj()%></td>                                    
                                            <td width="100" align="center"><a class="linkexcluir" href="ServletVendaCliente?operacao=excluir"> Excluir </a></td>
                                        </tr>
                                        <%}
                                        %>
                                    </table>
                                </div>

                                <%}
                                    if (confirma) {%>

                                <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Proximo" name="operacao" /></div>
                                <%}
                                %>
                                </form>


                        </div>
                        <!-- END pretação -->
                </div>
                <!-- END MAIL -->
            </div>
            <!-- START FOOTER -->
            <div id="space01"></div>
            <div class="footer">
                <div class="cont-footer">
                    <div class="box-reservado">&copy; TODOS OS DIREITOS RESERVADOS</div>
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
