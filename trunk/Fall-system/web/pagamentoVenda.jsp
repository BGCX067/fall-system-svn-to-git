<%-- 
    Document   : administrador
    Created on : 26/05/2013, 14:11:49
    Author     : LAILLA
--%>

<%@page import="entidades.Venda"%>
<%@page import="entidades.Parcela"%>
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
                <img src="imagens/status03.png" alt="" width="350" height="60" border="0" />
            </div>

            <div id="mail">
                <hr></hr>
                <div id="space04"></div>
                <div id="font01">Pedido de Venda</div>
                <hr />

                <!-- START prestação -->
                <div class="form-prestacao">
                    <div id="space04"></div>
                    <div id="font05">Definir Forma de Pagamento</div>
                    <form name="venda" method="post" action="ServletVendaPagamento">
                        <div id="space04"></div>
                        <select class="redonda" name="formaPagamento">
                            <option value="1" >A Vista</option>
                            <option value="2" <% if (((String) session.getAttribute("aPrazo")) != null) {
                                    out.print("selected=\"selected\"");
                                }%>>A Prazo</option>
                        </select>
                        <input type="submit" id="buttonPesquisar"  value="Escolher" name="operacao" />
                        <div id="space04"></div>
                        <div id="space04"></div>

                        <%//if (cont > 0) {%>

                        <%//}
                            String aPrazo = (String) session.getAttribute("aPrazo");
                            if (aPrazo != null && aPrazo.equals("sim")) {
                        %>
                        <div id="font05">Escolher Forma de Pagamento a Prazo</div>
                        <div id="space04"></div>
                        <select class="redonda" name="pagamentos">
                            <option value="1">1 entrada + 1x, após 30 dias</option>
                            <option value="2">2x sem juros</option>
                        </select>
                        <div align="right" style="width: 924px;"><input type="submit" id="buttonInserir"  value="Definir Forma a Prazo" name="operacao" /></div>

                        <%}%>

                        <%
                            List<Parcela> parcelas = (List<Parcela>) session.getAttribute("parcelas");
                            if (parcelas != null) {%> 
                        <div id="font05">Parcelas</div>    
                        <div id="space04"></div>
                        <div id="space04"></div>                                               
                        <div class="bg_produto">
                            <table id="produtosVendaT">
                                <tr>           
                                    <th align="left" width="300">Data de pagamento</th>
                                    <th align="left" width="200"> Valor</th>                                    
                                </tr>
                            </table>
                        </div>


                        <%
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
                        <%Venda venda = (Venda) session.getAttribute("venda");%>
                        <h2 align="right" style="width: 920px;">Valor Total: R$ <%=venda.getValorTotal()%></h2>
                        <!-- <div align="right"><input type="submit" id="buttonAtualizarProduto"  value="Atualizar Quantidade" name="operacao" /></div> > -->
                        <div align="right" style="width: 924px;"><input type="submit" id="buttonInserir"  value="Continuar" name="operacao" /></div>

                        <%}%>
                </div>
                </form>
            </div>
            <!-- END pretação -->
        </div>
        <!-- END MAIL -->
        </div>
        <!-- START FOOTER -->
        <div id="space01"></div><div id="space01"></div>
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
