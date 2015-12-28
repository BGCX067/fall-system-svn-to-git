/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import daos.ParcelaDao;
import daos.PrestacaoServicoDao;
import daos.TecnicoDao;
import entidades.Parcela;
import entidades.PrestacaoServico;
import entidades.Produto;
import entidades.Tecnico;
import entidades.Usuario;
import entidades.Venda;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Util {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public static String dataParaString(Date data) {
        return dateFormat.format(data);
    }

    public static Date stringToDate(String data) {
        try {
            return dateFormat.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String tempoParaString(Date data) {
        return timeFormat.format(data);
    }

    public static Date stringParaTempo(String tempo) {
        try {
            return timeFormat.parse(tempo);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Verifica se um texto é composto por números.
     *
     * @param text Texto a ser verificado.
     * @return True se texto é composto por números. False em caso contrário.
     */
    public static boolean isNumero(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se um texto é composto por números formato dinheiro.
     *
     * @param text Texto a ser verificado.
     * @return True se texto é composto por números formato dinheiro. False em
     * caso contrário.
     */
    public static boolean isDinheiro(String text) {
        text = text.replaceAll(",", ".").replaceAll(".", "");
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se um texto é composto por letras.
     *
     * @param text Texto a ser verificado.
     * @return True se texto é composto por letras. False em caso contrário.
     */
    public static boolean isLetra(String text) {
        text = text.trim().replaceAll(" ", "");
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se um texto é composto por números ou letras.
     *
     * @param text Texto a ser verificado.
     * @return True se texto é composto por números ou letras. False em caso
     * contrário.
     */
    public static boolean isLetraOuNumero(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetterOrDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove e cria a base de dados no MySQL.
     *
     * @param dataBase Nome da base de dados.
     * @param user Usuario do banco de dados.
     * @param password Senha do banco de dados.
     * @return True se base de dados foi criada. False em caso contrario.
     */
    public static boolean recreateDataBase(String dataBase, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE IF EXISTS " + dataBase);
            statement.execute("CREATE DATABASE " + dataBase);
            statement.close();
            connection.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Converte um array de bytes para um objeto ImageIcon.
     *
     * @param imageData Array de bytes com os dados da imagem.
     * @return Objeto ImagemIcon com a imagem.
     */
    public static ImageIcon byteArrayToImageIcon(byte[] imageData) {
        ObjectInputStream objectInputStream = null;
        try {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(imageData);
            objectInputStream = new ObjectInputStream(byteArray);
            return (ImageIcon) objectInputStream.readObject();

        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Converte um objeto ImageIcon para um array de bytes.
     *
     * @param icon Objeto ImagemIcon com a imagem.
     * @return Array de bytes com os dados da imagem.
     */
    public static byte[] imageIconToByteArray(ImageIcon icon) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArray);
            objectOutputStream.writeObject(icon);
            objectOutputStream.flush();
            return byteArray.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String montaComboboxEstado() {
        String aux = "<option>Selecione um Estado</option>\n"
                + "<option>Acre</option>\n"
                + "<option>Alagoas</option>\n"
                + "<option>Amapá</option>\n"
                + "<option>Amazonas</option>\n"
                + "<option>Bahia</option>\n"
                + "<option>Ceará</option>\n"
                + "<option>Distrito Federal</option>\n"
                + "<option>Espírito Santo</option>\n"
                + "<option>Goiás</option>\n"
                + "<option>Maranhão</option>\n"
                + "<option>Mato Grosso</option>\n"
                + "<option>Mato Grosso do Sul</option>\n"
                + "<option>Minas Gerais</option>\n"
                + "<option>Pará</option>\n"
                + "<option>Paraíba</option>\n"
                + "<option>Paraná</option>\n"
                + "<option>Pernambuco</option>\n"
                + "<option>Piauí</option>\n"
                + "<option>Rio de Janeiro</option>\n"
                + "<option>Rio Grande do Norte</option>\n"
                + "<option>Rio Grande do Sul</option>\n"
                + "<option>Rondônia</option>\n"
                + "<option>Roraima</option>\n"
                + "<option>Santa Catarina</option>\n"
                + "<option>São Paulo</option>\n"
                + "<option>Sergipe</option>\n"
                + "<option>Tocantins</option>";
        return aux;
    }

    public static String encriptaSenha(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        System.out.println(sen);
        return sen;
    }

    public static String geraSenhaAleatoria() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        String senhaAleatoria = myRandom.substring(0, 8);
        return senhaAleatoria;
    }

    /**
     * Valida se um CPF é válido.
     *
     * @param String CPF: numero a ser validado.
     * @return true se o o CPF informado é valido, false se não.
     */
    public static boolean isCPF(String CPF) {
        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                // converte no respectivo caractere numerico
                dig10 = (char) (r + 48);
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean temPrivilegioAdministrador(Usuario usuario) {
        if (usuario != null) {
            if (usuario.getClass().getSimpleName().equals("Administrador")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean ProdutoNaoFoiAdicionado(Produto produto, List<Produto> produtosJaAdicionados) {
        if (produtosJaAdicionados == null || produtosJaAdicionados.isEmpty()) {
            return true;
        } else {
            for (Produto p : produtosJaAdicionados) {
                if (p.getId() == produto.getId()) {
                    return false;
                }

            }
        }
        return true;
    }

    public static boolean VendaNaoFoiAdicionado(Venda venda, List<Venda> produtosJaAdicionados) {
        if (produtosJaAdicionados == null || produtosJaAdicionados.isEmpty()) {
            return true;
        } else {
            for (Venda p : produtosJaAdicionados) {
                if (p.getId() == venda.getId()) {
                    return false;
                }

            }
        }
        return true;
    }

    public static List<Parcela> criaParcelasTipo1(double valorTotal, Date dataVenda) {
        double valorParcela = valorTotal / 2;
        String dataString = dataParaString(dataVenda);
        String[] dataQuebrada = dataString.split("/");
        String mes = dataQuebrada[1];
        String mes2 = "" + ((Integer.parseInt(mes) + 1) % 12);
        String novaData = dataQuebrada[0] + "/" + mes2 + "/" + dataQuebrada[2];
        ArrayList<Parcela> retorno = new ArrayList<Parcela>();
        Parcela parcela1 = new Parcela(dataVenda, valorParcela);
        parcela1.setId((long) Math.random() * 5);
        new ParcelaDao<Parcela>(Parcela.class).insert(parcela1);
        Parcela parcela2 = new Parcela(stringToDate(novaData), valorParcela);
        parcela2.setId((long) Math.random() * 5);
        new ParcelaDao<Parcela>(Parcela.class).insert(parcela2);
        retorno.add(new ParcelaDao<Parcela>(Parcela.class).get(parcela1.getId()));
        retorno.add(new ParcelaDao<Parcela>(Parcela.class).get(parcela2.getId()));
        return retorno;
    }

    public static List<Parcela> criaParcelasTipo2(double valorTotal, Date dataVenda) {
        double valorParcela = valorTotal / 2;
        String dataString = dataParaString(dataVenda);
        String[] dataQuebrada = dataString.split("/");
        String mes = dataQuebrada[1];
        String mes1 = "" + ((Integer.parseInt(mes) + 1) % 12);
        String mes2 = "" + ((Integer.parseInt(mes) + 2) % 12);
        String novaData1 = dataQuebrada[0] + "/" + mes1 + "/" + dataQuebrada[2];
        String novaData2 = dataQuebrada[0] + "/" + mes2 + "/" + dataQuebrada[2];
        ArrayList<Parcela> retorno = new ArrayList<Parcela>();
        Parcela parcela1 = new Parcela(stringToDate(novaData1), valorParcela);
        parcela1.setId((long) Math.random() * 5);
        new ParcelaDao<Parcela>(Parcela.class).insert(parcela1);
        Parcela parcela2 = new Parcela(stringToDate(novaData2), valorParcela);
        parcela2.setId((long) Math.random() * 5);
        new ParcelaDao<Parcela>(Parcela.class).insert(parcela2);
        retorno.add(new ParcelaDao<Parcela>(Parcela.class).get(parcela1.getId()));
        retorno.add(new ParcelaDao<Parcela>(Parcela.class).get(parcela2.getId()));
        return retorno;
    }

    public static Tecnico processaTecnico(Date data, Date horario, String especialidade) {
        List<Tecnico> tecnicos = new TecnicoDao().list();
        List<PrestacaoServico> prestacoes = new PrestacaoServicoDao<PrestacaoServico>(PrestacaoServico.class).list();
        int pontuacao = Integer.MAX_VALUE;
        Tecnico tecnicoEscolhido = null;
        for (Tecnico tecnico : tecnicos) {
            int pontuacaoAtual = 0;
            for (PrestacaoServico prestacaoServico : prestacoes) {
                if (!(prestacaoServico.getDataPrestacao() == null)) {
                    if (prestacaoServico.getTecnico().getId() == tecnico.getId()) {
                        String data1 = dataParaString(data);
                        String data2 = dataParaString(prestacaoServico.getDataPrestacao());
                        if (data1.equals(data2)) {
                            pontuacaoAtual += 100;
                            String horario1 = tempoParaString(horario);
                            String horario2 = tempoParaString(prestacaoServico.getHorarioInicio());
                            if (horario1.equals(horario2)) {
                                pontuacaoAtual = Integer.MAX_VALUE;
                            }
                        }
                        boolean temEspecialidade = false;
                        for (String especialidades : tecnico.getEspecialidades()) {
                            if (especialidade.equalsIgnoreCase(especialidades)) {
                                temEspecialidade = true;
                                break;
                            }
                        }
                        if (!temEspecialidade) {
                            pontuacaoAtual += 100;
                        }

                    }
                }

            }
            if (pontuacaoAtual < pontuacao) {
                tecnicoEscolhido = tecnico;
            }
        }
        return tecnicoEscolhido;

    }

    
}
