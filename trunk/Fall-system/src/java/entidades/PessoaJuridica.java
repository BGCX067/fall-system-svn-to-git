
package entidades;

import javax.persistence.Entity;


/**
 *
 * @author Alisson Renan
 */
@Entity
public abstract class PessoaJuridica extends Pessoa {
    private String razaoSocial;
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String razaoSocial, String cnpj, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(cep, estado, logradouro, numero, complemento, bairro, telefone, email);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
