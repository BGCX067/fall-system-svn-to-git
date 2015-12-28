<%-- 
    Document   : index
    Created on : 25/05/2013, 17:07:19
    Author     : Alisson Renan
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>FALL SYSTEM</title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="css/link.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="content">
            <!-- START TOPO -->
            <div id="space02"></div>
            <div id="logo-home"><img src="imagens/logo.png" width="254" height="70" /></div>
            <div id="space01"></div>
            <!-- END TOPO -->

            <!-- START LOGIN -->
            <div class="login">
                <form id="form1" name="form1" method="post" action="ServletValidacao">
                    <div id="font01">Usuario:</div>
                    <label>
                        <input class="redonda" name="login" type="text" id="login" size="40" />
                    </label>
                    <div id="font01">Senha:</div>
                    <label>
                        <input class="redonda" name="senha" type="password" id="senha" size="40" />
                    </label>
                    <div class="erro" id="font01"><strong><% String invalido = (String) request.getAttribute("invalido");
                        if (invalido != null) {%>
                            <img src="imagens/erro_verde.png" width="25" height="25"/> 
                            Login inválido
                            <%}
                            %></strong></div>
                    <div id="botao-entrar">
                        <label>
                            <input type="submit" name="botao" id="button" value="Entrar" style="font-size:18px;" />
                        </label>
                    </div>
                </form>
            </div>
            <!-- END LOGIN -->
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