<%-- 
    Document   : administrador
    Created on : 26/05/2013, 14:11:49
    Author     : LAILLA
--%>

<%@page import="java.util.Date"%>
<%@page import="entidades.Tecnico"%>
<%@page import="entidades.ClientePessoaJuridica"%>
<%@page import="entidades.ClientePessoaFisica"%>
<%@page import="daos.VendaDao"%>
<%@page import="daos.PrestacaoServicoDao"%>
<%@page import="entidades.PrestacaoServico"%>
<%@page import="entidades.Venda"%>
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
        <title>Area Tecnico</title>
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
                        <li><a class="linkfooter" href="#">Prestação de Serviço</a></li>
                        <li><a class="linkfooter" href="#">Alterar Dados Pessoais</a></li>

                        <li><a class="linkfooter" href="ServletValidacao?acao=1">Sair</a></li>
                    </ul>                 
            </div>
            <!-- END TOPO -->
            <!-- SART mail -->
            <div id="space04"></div>            
            <div id="status">

            </div>

            <div id="mail">
                <hr></hr>
                <div id="space04"></div>
                <div id="font01">Agendamento de Prestação de Serviço</div>
                <hr />

                <!-- START prestação -->
                <div class="form-prestacao">
                    <div id="space04"></div>
                    <form name="venda" method="post" action="ServletPrestacao">
                        <%
                            String escolheu = (String) session.getAttribute("escolheu");
                            PrestacaoServico prestacaoEscolhida = (PrestacaoServico) session.getAttribute("prestacao");
                            if (prestacaoEscolhida == null) {
                        %>
                        <div id="font05">Filtrar prestação por id</div>
                        <div id="space04"></div>
                        <input class="redonda" id="pesquisaProdutos" type="text" name="pesquisaProdutos" value="Pesquisar Prestações..." size="50"/>
                        <input type="submit" id="buttonPesquisar"  value="Filtrar" name="operacao" />
                        <div id="space04"></div>
                        <div id="space04"></div>
                        <div id="font05">Lista de Prestações ainda não Agendadas</div>
                        <div class="bg_box1"></div>
                        <div class="bg_box3">
                            <div id="conteudo-box">
                                <div id="listaProdutos">
                                    <table id="listagemProdutos" class="fundotab" align="center" width="920" border="1" cellpadding="0" cellspacing="0" style="border-color: whitesmoke;">
                                        <tr>
                                            <th align="left" width="30">&nbsp;</th>
                                            <th align="left" width="10">Identificação</th>
                                            <th align="left" width="100">Data da Venda</th>
                                            <th align="left" width="100">Cliente</th>
                                        </tr>

                                        <%
                                            List<PrestacaoServico> prestacoes = new PrestacaoServicoDao<PrestacaoServico>(PrestacaoServico.class).list();
                                            for (PrestacaoServico prestacao : prestacoes) {
                                                if ((prestacao.getDataPrestacao() == null)) {
                                        %>
                                        <tr>

                                            <td width="30"> <input type="radio" name="prestacoes" value="<%=prestacao.getId()%>" /> </td>                                            
                                            <td width="10"> <%=prestacao.getId()%></td>
                                            <td width="100"> <%= Util.dataParaString(prestacao.getVenda().getDataVenda())%></td>
                                            <%
                                                if (prestacao.getVenda().getCliente().getClass().getSimpleName().equalsIgnoreCase("ClientePessoaFisica")) {
                                                    ClientePessoaFisica cliente = (ClientePessoaFisica) prestacao.getVenda().getCliente();%>
                                            <td width="100"> <%= cliente.getNome()%></td>
                                            <%} else {
                                                ClientePessoaJuridica cliente = (ClientePessoaJuridica) prestacao.getVenda().getCliente();%>
                                            <td width="100"> <%= cliente.getRazaoSocial()%></td>                                            

                                            <%}
                                            %>
                                        </tr>

                                        <% }

                                            }

                                        %>
                                    </table>
                                </div>                                                       

                            </div>
                        </div>
                        <div id="space04"></div>
                        <div align="right" style="width: 920px"><input type="submit" id="buttonInserir"  value="Escolher Prestação" name="operacao" /></div>
                        <div id="space04"></div>
                        <div id="space04"></div>
                        <%} else if (escolheu == null) {

                        %>
                        <div id="font05">Prestação Escolhida</div>
                        <div id="space04"></div>
                        <div class="bg_produto">                            
                            <table id="produtosVendaT">
                                <tr>           
                                    <th align="left" width="50">Id</th>
                                    <th  colspan="2" align="left" width="800"> Cliente</th>                                    
                                </tr>                                
                            </table>                            
                        </div>
                        <div class="bg_produto">                            
                            <table id="produtosVendaT">
                                <tr>           
                                    <td width="50"><%=prestacaoEscolhida.getId()%></td>
                                    <%
                                        if (prestacaoEscolhida.getVenda().getCliente().getClass().getSimpleName().equalsIgnoreCase("ClientePessoaFisica")) {
                                            ClientePessoaFisica cliente = (ClientePessoaFisica) prestacaoEscolhida.getVenda().getCliente();%>
                                    <td  width="700"> <%= cliente.getNome()%></td>
                                    <%} else {
                                        ClientePessoaJuridica cliente = (ClientePessoaJuridica) prestacaoEscolhida.getVenda().getCliente();%>
                                    <td width="700"> <%= cliente.getRazaoSocial()%></td>                                            


                                    <%}
                                    %>
                                    <td align="right" width="100"> <a href="ServletPrestacao?excluir="<%=prestacaoEscolhida.getId()%>">Remover</a></td>                                    
                                </tr>                                
                            </table>                            
                        </div>
                        <div id="space04"></div>

                        <div id="font05"> Definir Data, Horário e Técnico</div>
                        <div id="space04"></div>
                        <table>
                            <tr>
                                <th align="left"><label> Data (DD/MM/AAAA) </label></th> 
                                <td><input class="redonda" type="text" name="dataPrestacao" value="" size="15" /> <br/></td>
                            </tr>
                            <tr>  
                                <th align="left"><label> Horário de Início (HH:MM) </label></th> 
                                <td><input class="redonda" type="text" name="horarioInicioPrestacao" value="" size="15" /> <br/></td>
                            </tr>
                            <tr align="left">        
                                <th><label> Especialidade do Técnico</label></th>
                                <td>
                                    <select name="especialidade" class="redonda">
                                        <option value="Cameras"> Cameras </option>
                                        <option value="Eletrica"> Parte Elétrica</option>
                                        <option value="Telefonia"> Telefonia </option>
                                        <option value="Hardware"> Hardware</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div align="right" style="width: 920px"><input type="submit" id="buttonInserir"  value="Processar Tecnico" name="operacao" /></div>
                        <%} else {
                            Tecnico tecnicoEscolhido = (Tecnico) session.getAttribute("tecnicoEscolhido");
                            if (tecnicoEscolhido == null) {%>
                        <h2> Não há técnicos disponíveis para esse horário e local </h2>  
                        <%} else {
                        %>
                        <div id="font05">Prestação Escolhida</div>
                        <div id="space04"></div>
                        <div class="bg_produto">                            
                            <table id="produtosVendaT">
                                <tr>           
                                    <th align="left" width="50">Id</th>
                                    <th  colspan="2" align="left" width="800"> Cliente</th>                                    
                                </tr>                                
                            </table>                            
                        </div>
                        <div class="bg_produto">                            
                            <table id="produtosVendaT">
                                <tr>           
                                    <td width="50"><%=prestacaoEscolhida.getId()%></td>
                                    <%
                                        if (prestacaoEscolhida.getVenda().getCliente().getClass().getSimpleName().equalsIgnoreCase("ClientePessoaFisica")) {
                                            ClientePessoaFisica cliente = (ClientePessoaFisica) prestacaoEscolhida.getVenda().getCliente();%>
                                    <td  width="700"> <%= cliente.getNome()%></td>
                                    <%} else {
                                        ClientePessoaJuridica cliente = (ClientePessoaJuridica) prestacaoEscolhida.getVenda().getCliente();%>
                                    <td width="700"> <%= cliente.getRazaoSocial()%></td>                                            


                                    <%}
                                    %>
                                    <td align="right" width="100"> <a href="ServletPrestacao?excluir="<%=prestacaoEscolhida.getId()%>">Remover</a></td>                                    
                                </tr>                                
                            </table>                            
                        </div>
                        <div id="space04"></div>
                        <div id="font05">Dados do agendamento</div>   
                        <table id="listagem" class="fundotab" align="left" width="500" border="1" cellpadding="0" cellspacing="0" style="border-color: whitesmoke;">                       
                            <tr><th align="left" width="200"> <label> Data: </th><td><%=(String) session.getAttribute("dataPrestacao")%> </label></br></td></tr>
                            <tr>  <th align="left"width="200"> <label> Horário de Início: </th><td><%=(String) session.getAttribute("horarioInicio")%> </label></td> </tr>
                            <tr> <th align="left"width="200"><label>Tecnico responsável:</th><td> <%=((Tecnico) session.getAttribute("tecnicoEscolhido")).getNome()%></label></td></tr>
                        </table>
                        <div id="space04"></div>
                        <div id="space04"></div>
                        <div align="center">
                        <div align="right" style="width: 920px;"><input type="submit" id="buttonInserir"  value="Agendar Prestação" name="operacao" /></div>                       
                        </div>
                        <div id="space04"></div>
                        <%}%>
                        <%}%>
                    </form>

                </div>
                <!-- END pretação -->
            </div>
            <!-- END MAIL -->
        </div>
        <!-- START FOOTER -->
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
