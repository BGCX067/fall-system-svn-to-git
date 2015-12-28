
package entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Alisson Renan
 */
@Entity
public class Fornecedor extends PessoaJuridica {
    @ManyToMany
    private List<Empresa> empresas;
    private String site;

    public Fornecedor() {
        super();
    }

    public Fornecedor(String razaoSocial, String cnpj, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email, String empresa) {
        super(razaoSocial, cnpj, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
        this.site = site;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    
    
    
}
