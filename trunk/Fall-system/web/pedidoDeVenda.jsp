<%-- 
    Document   : administrador
    Created on : 26/05/2013, 14:11:49
    Author     : LAILLA
--%>

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
                <img src="imagens/status01.png" alt="" width="350" height="60" border="0" />
            </div>

            <div id="mail">
                <hr></hr>
                <div id="space04"></div>
                <div id="font01">Pedido de Venda</div>
                <hr />

                <!-- START prestação -->
                <div class="form-prestacao">
                    <div id="space04"></div>
                    <div id="font05">Adicionar Produtos</div>
                    <form name="venda" method="post" action="ServletVenda">
                        <div id="space04"></div>
                        <input class="redonda" id="pesquisaProdutos" type="text" name="pesquisaProdutos" value="Pesquisar Produtos..." size="50"/>
                        <input type="submit" id="buttonPesquisar"  value="Filtrar" name="operacao" />
                        <div id="space04"></div>
                        <div id="space04"></div>
                        <div id="font05">Produtos no Estoque</div>
                        <div class="bg_box1"></div>
                        <div class="bg_box3">
                            <div id="conteudo-box">
                                <div id="listaProdutos">
                                    <table id="listagemProdutos" class="fundotab" align="center" width="920" border="1" cellpadding="0" cellspacing="0" style="border-color: whitesmoke;">
                                        <tr>
                                            <th align="left" width="30">&nbsp;</th>
                                            <th width="100">Refêrencia</th>
                                            <th align="left" width="558">Descrição</th>
                                            <th width="100">Valor Unitário</th>
                                            <th width="100">Em estoque</th>
                                        </tr>

                                        <%
                                            List<Produto> resultBuscaProduto = (List<Produto>) session.getAttribute("produtosLista");
                                            List<Produto> produtosJaAdicionados = (List<Produto>) session.getAttribute("jaAdicionados");
                                            if (resultBuscaProduto != null) {
                                                for (Produto produto : resultBuscaProduto) {


                                                    if (util.Util.ProdutoNaoFoiAdicionado(produto, produtosJaAdicionados)) {
                                        %>
                                        <tr>

                                            <td width="30"> <input type="checkbox" name="<%=produto.getId()%>" /> </td>
                                            <td width="100" align="rigth"> <%=produto.getIdentificacao()%></td>
                                            <td width="600"> <%=produto.getNome()%> </td>
                                            <td width="100" align="rigth"> R$ <%=produto.getPreco()%> </td>                                    
                                            <td width="100" align="rigth"> <%= produto.getQuantidadeEstoque()%></td>
                                        </tr>

                                        <% }

                                                }
                                            }
                                        %>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="bg_box2"></div>
                        <div id="space04"></div>
                        <div align="right" style="width: 920px"><input type="submit" id="buttonInserir"  value="Adicionar Produtos" name="operacao" /></div>                       
                        <div id="space04"></div>
                        <%
                            int cont = 0;
                            List<Produto> produtosNaVenda = (List<Produto>) session.getAttribute("produtosNaVenda");
                            if (produtosNaVenda != null) {%>
                        <div id="font05">Produtos adicionados</div><div id="space04"></div>
                        <div class="bg_produto">
                            <table id="produtosVendaT">
                                <tr>           
                                    <th width="100">Refêrencia</th>
                                    <th align="left" width="450"> Nome Produto</th>
                                    <th width="100"> Valor Unitário</th>
                                    <th width="100"> Qtde. Estoque</th>
                                    <th width="100"> Qtde. Venda</th>
                                    <th width="100"> Preço total</th>
                                </tr>
                            </table>                            
                        </div>

                        <%
                            for (Produto produto : produtosNaVenda) {
                                cont++;
                        %>
                        <div class="bg_produto">
                            <table id="produtosVenda">
                                <tr>
                                    <td width="100" > <%=produto.getIdentificacao()%></td>
                                    <td align="left" width="450"> <%=produto.getNome()%></td>
                                    <td id="preco" width="100">R$ <%=produto.getPreco()%></td>
                                    <td width="100" > <%=produto.getQuantidadeEstoque()%></td>
                                    <td width="100"> <input type="text" onkeypress="changeText2()" name="qtdeVenda<%=produto.getId()%>" size="5" value="<%= (Integer) session.getAttribute("quantidade" + produto.getId())%>" /></td>
                                    <td id="mostra" width="100"> R$ <%= produto.getPreco() * (Integer) session.getAttribute("quantidade" + produto.getId())%></td>                                                                        
                                </tr>  
                                </table>

                        </div>
                                <%}
                                %>
                            
                        <!-- <div align="right"><input type="submit" id="buttonAtualizarProduto"  value="Atualizar Quantidade" name="operacao" /></div> > -->

                        <%}
                            if (cont > 0) {%>
                        <div id="space04"></div>
                        <input type="checkbox" name="prestacao" value="ON" />Tem Prestação de Serviço
                        <div id="space04"></div>
                        <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Continuar" name="operacao" /></div>
                        <%}
                        %>

                        <div id="space04"></div>
                    </form>

                </div>
                <!-- END pretação -->
            </div>
            <!-- END MAIL -->
        </div>
        <!-- START FOOTER -->
        <div class="footer-pe">
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
    <script type="text/javascript">
        function changeText2() {
            var userInput = document.getElementById('qtdeVenda').value;
            var quant = document.getElementById('preco').valeu;
            var res = userInput * quant;
            document.getElementById('mostra').innerHTML = res;
        }
    </script>
</html>

<%    } else {
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
%>
