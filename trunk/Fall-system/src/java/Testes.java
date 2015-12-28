
import daos.AdministradorDao;
import daos.ClientePessoaFisicaDao;
import daos.ClientePessoaJuridicaDao;
import daos.EmpresaDao;
import daos.FormaPagamentoDao;
import daos.FornecedorDao;
import daos.ParcelaDao;
import daos.PrestacaoServicoDao;
import daos.ProdutoDao;
import daos.TecnicoDao;
import daos.VendaDao;
import daos.VendedorDao;
import entidades.Administrador;
import entidades.ClientePessoaFisica;
import entidades.ClientePessoaJuridica;
import entidades.Empresa;
import entidades.FormaPagamento;
import entidades.Fornecedor;
import entidades.Parcela;
import entidades.PrestacaoServico;
import entidades.Produto;
import entidades.Tecnico;
import entidades.Venda;
import entidades.Vendedor;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alisson Renan
 */
public class Testes {

    public static void main(String[] args) {
        Administrador administrador = insereRetornaAdministrador();
        Vendedor vendedor = insereRetornaVendedor();
        Tecnico tecnico = insereRetornaTecnico();
        Empresa empresa = insereRetornaEmpresa();
        Fornecedor fornecedor = insereRetornaFornedecor();
        Produto produto1 = insereRetornaProduto1(fornecedor);
        Produto produto2= insereRetornaProduto2(fornecedor);
        Produto produto3 = insereRetornaProduto3(fornecedor);
        Produto produto4 = insereRetornaProduto4(fornecedor);
        Produto produto5 = insereRetornaProduto5(fornecedor);
        ClientePessoaFisica cliente1 = insereRetornaCliente1();
        ClientePessoaFisica cliente2 = insereRetornaCliente2();
        ClientePessoaJuridica cliente3 = insereRetornaCliente3();
        FormaPagamento formaPagamento = insereRetornaFormaPagamento();
        Venda venda = insereRetornaVenda(vendedor, cliente1, produto1, formaPagamento);
        PrestacaoServico prestacaoServico = insereRetornaPrestacaoServico(tecnico, cliente1, produto5, venda);
    }

    private static Administrador insereRetornaAdministrador() {
        AdministradorDao daoAdministrador = new AdministradorDao();
        Administrador adm = new Administrador("01.111.111-0", util.Util.stringToDate("23/02/1982"),
                "Secretaria", 1200, "adm1", util.Util.encriptaSenha("123"), "Maria da Silva", "00.225.333-00",
                "8720000", "Parana", "Rua 1", "111", "Ap. 303", "Zona 6", "(44)9999-9999", "secretaria1@genyo.com.br");
        daoAdministrador.insert(adm);
        return adm;
    }

    private static Vendedor insereRetornaVendedor() {
        VendedorDao daoVendedor = new VendedorDao();
        Vendedor vendedor = new Vendedor("02.222.222-0", util.Util.stringToDate("29/05/1972"),
                "Representante de Vendas", 2150, "vendedor1", util.Util.encriptaSenha("123"), "Mário da Silva",
                "00.111.333-00", "8720000", "Parana", "Rua 2", "222", "Ap. 903", "Zona 1", "(44)9991-1999",
                "vendedor1@genyo.com.br"); 
        daoVendedor.insert(vendedor);
        return vendedor;
    }

    private static Tecnico insereRetornaTecnico() {
        TecnicoDao daoTecnico = new TecnicoDao();
        ArrayList<String> especialidades = new ArrayList<String>();
        especialidades.add("Cameras");
        especialidades.add("Eletrica");
        especialidades.add("Telefonia");
        Tecnico tecnico = new Tecnico(especialidades, "03.333.111-0", util.Util.stringToDate("13/12/1990"),
                "Técnico em telefonia", 2000, "tecnico1", util.Util.encriptaSenha("123"), "Carlos Souza",
                "00.333.343-00", "8720000", "Parana", "Rua A", "111", "", "Zona 7", "(44)9881-9119",
                "tecnico@genyo.com.br");
        daoTecnico.insert(tecnico);
        return tecnico;
    }

    private static Empresa insereRetornaEmpresa() {
        EmpresaDao daoEmpresa = new EmpresaDao();
        Empresa empresa = (new Empresa(null, null, null, null, "Genyo Teleinformatica",
                "0000000000", "87200-000", "Parana", "Av. Tiradentes", "1000", "", "Zona 02", "9999-9999", "contato@genyoteleinformatica.com.br"));
        daoEmpresa.insert(empresa);
        return empresa;
        
    }

    private static Produto insereRetornaProduto1(Fornecedor fornecedor) {
        ProdutoDao daoProduto = new ProdutoDao(Produto.class);
        Produto produto = new Produto("Camera 1", 179.90, fornecedor, 100, 52365, 1);
        daoProduto.insert(produto);
        return produto;
    }
    
    private static Produto insereRetornaProduto2(Fornecedor fornecedor) {
        ProdutoDao daoProduto = new ProdutoDao(Produto.class);
        Produto produto = new Produto("Camera 2", 100, fornecedor, 200, 102365, 1);
        daoProduto.insert(produto);
        return produto;
    }
    
    private static Produto insereRetornaProduto3(Fornecedor fornecedor) {
        ProdutoDao daoProduto = new ProdutoDao(Produto.class);
        Produto produto = new Produto("Camera 3", 89.50, fornecedor, 150, 111165, 1);
        daoProduto.insert(produto);
        return produto;
    }
    
    private static Produto insereRetornaProduto4(Fornecedor fornecedor) {
        ProdutoDao daoProduto = new ProdutoDao(Produto.class);
        Produto produto = new Produto("Camera 4", 210, fornecedor, 50, 232323, 1);
        daoProduto.insert(produto);
        return produto;
    }
    
    private static Produto insereRetornaProduto5(Fornecedor fornecedor) {
        ProdutoDao daoProduto = new ProdutoDao(Produto.class);
        Produto produto = new Produto("Camera 5", 120, fornecedor, 85, 22214, 1);
        daoProduto.insert(produto);
        return produto;
    }

    private static Fornecedor insereRetornaFornedecor() {
        FornecedorDao daoFornecedor = new FornecedorDao();
        Fornecedor fornecedor = new Fornecedor("Cam Seguranca LTDA.", "0000000000/000",
                "8720000", "Parana", "Rua J", "1001", "", "Jd. Ramalheira","(44)88210000",
                "contato@camseg.com.br", "http://camseguranca.net");        
        daoFornecedor.insert(fornecedor);
        return fornecedor;
    }

    private static ClientePessoaFisica insereRetornaCliente1() {
        ClientePessoaFisicaDao daoCliente = new ClientePessoaFisicaDao();
        ClientePessoaFisica cliente = new ClientePessoaFisica("Luis da Silva",
                "00.000.000-00", "8720000", "Parana", "Rua 10", "621", "", "Centro",
                "(44) 9888-2222", "luis.silva.20@gmail.com");
        daoCliente.insert(cliente);
        return cliente;
    }

    private static Venda insereRetornaVenda(Vendedor vendedor, ClientePessoaFisica cliente,
            Produto produto, FormaPagamento formaPagamento) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        produtos.add(produto);
        VendaDao daoVenda = new VendaDao(Venda.class);
        Venda venda = new Venda(util.Util.stringToDate("27/05/2013"), vendedor, cliente,
                produtos, true, 179.90, formaPagamento);
        daoVenda.insert(venda);
        return venda;
    }

    private static FormaPagamento insereRetornaFormaPagamento() {
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        ParcelaDao parcelaDao = new ParcelaDao(Parcela.class);
        parcelaDao.insert(new Parcela(util.Util.stringToDate("28/05/2013"), 179.90));
        parcelas.add((Parcela) parcelaDao.list().get(0));
        FormaPagamentoDao daoFormaPagamento = new FormaPagamentoDao(FormaPagamento.class);
        FormaPagamento formaPagamento = new FormaPagamento(1, 1, parcelas);
        daoFormaPagamento.insert(formaPagamento);
        return formaPagamento;
    }

    private static PrestacaoServico insereRetornaPrestacaoServico(Tecnico tecnico, ClientePessoaFisica cliente, Produto produto, Venda venda) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        produtos.add(produto);
        PrestacaoServicoDao daoPrestacaoServico = new PrestacaoServicoDao(PrestacaoServico.class);
        PrestacaoServico prestacaoServico = new PrestacaoServico(util.Util.stringToDate("01/06/2013"),
                util.Util.stringParaTempo("15:00:00"), util.Util.stringParaTempo("18:00:00"), tecnico, cliente, produtos, venda);
        daoPrestacaoServico.insert(prestacaoServico);
        return prestacaoServico;
    }

    private static ClientePessoaFisica insereRetornaCliente2() {
        ClientePessoaFisicaDao daoCliente = new ClientePessoaFisicaDao();
        ClientePessoaFisica cliente = new ClientePessoaFisica("Maria das Graças",
                "00.000.000-00", "8720000", "Parana", "Rua 10", "621", "", "Centro",
                "(44) 9910-2222", "mariazinha@gmail.com");
        daoCliente.insert(cliente);
        return cliente;
    }
    
    private static ClientePessoaJuridica insereRetornaCliente3() {
        ClientePessoaJuridicaDao daoCliente = new ClientePessoaJuridicaDao();
        ClientePessoaJuridica cliente = new ClientePessoaJuridica("Empresa 1 SA Ltda.",
                "000000000/000", "8720000", "Parana", "Rua 20", "621", "", "Centro",
                "(44) 9910-2222", "empresa1@gmail.com");
        daoCliente.insert(cliente);
        return cliente;
    }
    
}
