
package entidades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.criteria.Fetch;

/**
 *
 * @author Alisson Renan
 */
@Entity
public class Empresa extends PessoaJuridica {
    //@OneToMany (cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
   private List<Usuario> funcionarios;
    @ManyToMany(mappedBy = "empresas", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
   private List<Fornecedor> fornecedores;
    
    @ManyToMany(mappedBy = "empresas", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
   private List<ClientePessoaFisica> clientesPessoaFisica;
    @ManyToMany(mappedBy = "empresas", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
   private List<ClientePessoaJuridica> clientesPessoaJuridica;

    public Empresa() {
        super();
    }

    public Empresa(ArrayList<Usuario> funcionarios, ArrayList<Fornecedor> fornecedores, ArrayList<ClientePessoaFisica> clientesPessoaFisica, ArrayList<ClientePessoaJuridica> clientesPessoaJuridica, String razaoSocial, String cnpj, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(razaoSocial, cnpj, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
        this.funcionarios = funcionarios;
        this.fornecedores = fornecedores;
        this.clientesPessoaFisica = clientesPessoaFisica;
        this.clientesPessoaJuridica = clientesPessoaJuridica;
    }

    public List<Usuario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Usuario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<ClientePessoaFisica> getClientesPessoaFisica() {
        return clientesPessoaFisica;
    }

    public void setClientesPessoaFisica(ArrayList<ClientePessoaFisica> clientesPessoaFisica) {
        this.clientesPessoaFisica = clientesPessoaFisica;
    }

    public List<ClientePessoaJuridica> getClientesPessoaJuridica() {
        return clientesPessoaJuridica;
    }

    public void setClientesPessoaJuridica(ArrayList<ClientePessoaJuridica> clientesPessoaJuridica) {
        this.clientesPessoaJuridica = clientesPessoaJuridica;
    }

    
    
    
    
    
}
