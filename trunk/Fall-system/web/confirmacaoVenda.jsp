<%@page import="entidades.Parcela"%>
<%@page import="entidades.Venda"%>
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
                <img src="imagens/status04.png" alt="" width="350" height="60" border="0" />
            </div>

            <div id="mail">
                <hr></hr>
                <div id="space04"></div>
                <div id="font01">Pedido de Venda</div>
                <hr />

                <!-- START prestação -->
                <div class="form-prestacao">
                    <div id="space04"></div>

                    <div id="font05">Confirmação dos Dados</div>
                    <form name="venda" method="post" action="ServletVendaConfirmacao">
                        <%Venda venda = (Venda) session.getAttribute("venda");
                        %>
                        <h3>Informações do Cliente</h3>
                        <table id="listagem" class="fundotab" align="center" width="934" border="1" cellpadding="0" cellspacing="0" style="border-color: whitesmoke;">
                            <tr>
                                <th align="left" width="600">Nome/Razão Social</th>
                                <th align="left" width="200">CPF/CNPJ</th>
                            </tr>
                            <tr>
                                <% if (venda.getCliente().getClass().getSimpleName().equalsIgnoreCase("ClientePessoaFisica")) {
                                        ClientePessoaFisica cliente = (ClientePessoaFisica) venda.getCliente();
                                        out.print("<td width=\"600\">" + cliente.getNome());
                                    } else {
                                        ClientePessoaJuridica cliente = (ClientePessoaJuridica) venda.getCliente();
                                        out.print("<td width=\"600\">" + cliente.getRazaoSocial());
                                    }
                                    if (venda.getCliente().getClass().getSimpleName().equalsIgnoreCase("ClientePessoaFisica")) {
                                        ClientePessoaFisica cliente = (ClientePessoaFisica) venda.getCliente();
                                        out.print("<td width=\"100\">" + cliente.getCpf());
                                    } else {
                                        ClientePessoaJuridica cliente = (ClientePessoaJuridica) venda.getCliente();
                                        out.print("<td width=\"100\">" + cliente.getCnpj());
                                    }
                                %>
                            </tr>
                        </table>
                        <div id="space01"></div>
                        <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Alterar Cliente" name="operacao" /></div>

                        <hr />
                        <h3>Produtos da Venda</h3>
                        <table id="listagem" class="fundotab" align="center" width="934" border="1" cellpadding="0" cellspacing="0" style="border-color: whitesmoke;">

                            <tr>
                                <th width="100">Refêrencia</th>
                                <th align="left" width="450"> Nome Produto</th>
                                <th width="100"> Valor Unitário</th>
                                <th width="100"> Qtde. Estoque</th>
                                <th width="100"> Qtde. Venda</th>
                                <th width="100"> Preço total</th>
                            </tr>

                            <%
                                for (Produto produto : venda.getProdutos()) {%>
                            <tr>
                                <td width="100" > <%=produto.getIdentificacao()%></td>
                                <td align="left" width="450"> <%=produto.getNome()%></td>
                                <td width="100" align="right">R$ <%=produto.getPreco()%></td>
                                <td width="100" align="right"> <%=produto.getQuantidadeEstoque()%></td>
                                <td width="100" align="right"> <%= (Integer) session.getAttribute("quantidade" + produto.getId())%> </td>
                                <td width="100" align="right"> R$ <%= produto.getPreco() * (Integer) session.getAttribute("quantidade" + produto.getId())%></td>                                                                        
                            </tr> 
                            <%}
                            %>
                        </table>
                        <div id="space01"></div>
                        <%if (venda.isTemPrestacaoServico()) {%>
                        <img src="imagens/check.png" style=" padding-left: 15px; padding-right: 10px; width: 20px; height: 20px;">Prestação de Serviço
                            <%}%>
                            <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Alterar Produtos" name="operacao" /></div>
                            <hr />

                            <h3>Parcelas</h3>
                            <div class="bg_produto">
                                <table id="produtosVendaT">
                                    <tr>           
                                        <th align="left" width="300">Data de pagamento</th>
                                        <th align="left" width="200"> Valor</th>                                    
                                    </tr>
                                </table>
                            </div>
                            <%
                                List<Parcela> parcelas = (List<Parcela>) session.getAttribute("parcelas");
                                for (Parcela parcela : parcelas) {

                            %>
                            <div class="bg_produto">
                                <table id="produtosVenda">
                                    <tr>
                                        <td align="left" width="300" > <%= Util.dataParaString(parcela.getData())%></td>
                                        <td align="left" width="200"> R$ <%=parcela.getValor()%></td>

                                    </tr>  
                                </table>
                            </div>


                            <%}

                            %>
                            <% venda = (Venda) session.getAttribute("venda");%>
                            <div align="right" style="width: 920px; font-size: 25px;">Valor Total: R$ <%=venda.getValorTotal()%></div>

                            <div id="space01"></div>
                            <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Alterar Forma de Pagamento" name="operacao" /></div>        

                            <hr />
                            <div align="center" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Finalizar Venda" name="operacao"/></div>        
                            </div>

                            <div id="space01"></div>
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
